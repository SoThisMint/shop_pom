package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.qf.entity.Mail;
import com.qf.entity.User;
import com.qf.service.IUserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/17 17:50
 * @description：${description}
 * @modified By：
 * @version: $version$
 * 登录失败：0
 * 用户未激活：-1
 * 登录成功：1
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Reference
    private IUserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/toLogin")
    public String toLogin(String returnUrl, Model model) {
        model.addAttribute("returnUrl", returnUrl);
        return "login";
    }

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model, HttpServletResponse response, String returnUrl) {
        User user = userService.login(username, password);

        if (user == null) {
            //登录失败
            model.addAttribute("state", 0);
            return "login";
        } else if (user.getStatus() == 0) {
            //未激活
            model.addAttribute("state", -1);
            String mail = user.getEmail();
            int index = mail.indexOf("@");
            String tomail = "http://mail." + mail.substring(index + 1);
            model.addAttribute("tomail", tomail);
            return "login";
        }
        System.out.println(returnUrl);
        //设置默认的跳转url（首页）
        if (returnUrl == null || returnUrl.equals("")) {
            returnUrl = "http://localhost:8081/";
        }

        //用户信息放到redis中
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token, user);
        redisTemplate.expire(token, 5, TimeUnit.DAYS);

        //同时将uuid（token）写回到cookie中
        Cookie cookie = new Cookie("login_token", token);
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setPath("/");
        response.addCookie(cookie);
        model.addAttribute("state", 1);
        return "redirect:" + returnUrl;
    }

    @RequestMapping("/register")
    public String register(User user, Model model) {
//        System.out.println(user);
        int res = userService.register(user);
        if (res <= 0) {
            model.addAttribute("error", 0);
            return "register";
        }

        //注册成功
        //发送激活邮件
        Mail mail = new Mail();
        mail.setTo(user.getEmail());
        mail.setTitle("EagleJump官方激活邮件");
        String uuid = UUID.randomUUID().toString();
        //将uuid写入redis
        redisTemplate.opsForValue().set("email_token_" + user.getUsername(), uuid);
        redisTemplate.expire("email_token", 5, TimeUnit.MINUTES);

        String url = "http://localhost:8084/user/jihuo?username=" + user.getUsername() + "&token=" + uuid;
        mail.setContent("EagleJump激活请点击：<a href='" + url + "'>" + url + "</a>");
        mail.setCreatetime(new Date());

        rabbitTemplate.convertAndSend("email_queue", mail);
        return "login";
    }

    @RequestMapping("/login_token")
    @ResponseBody
    public String login_token(@CookieValue(name = "login_token", required = false) String loginToken) {
        System.out.println("获得浏览器发送过来的请求：" + loginToken);
        User user = null;
        if (loginToken != null) {
            user = (User) redisTemplate.opsForValue().get(loginToken);
        }
        if (user != null) {
            Object userOb = JSON.toJSON(user);
            return "login_token(" + userOb + ")";
        }

        return "login_token(0)";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse response, @CookieValue(name = "login_token", required = false) String loginToken) {
        Cookie cookie = new Cookie("login_token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        //清空redis
        redisTemplate.delete(loginToken);
        return "login";
    }

    @RequestMapping("/jihuo")
    public String jihuo(String username, String token) {
        //验证token是否有效
        String redisToken = (String) redisTemplate.opsForValue().get("email_token_" + username);
        //验证token
        if (redisToken == null || !redisToken.equals(token)) {
            //认证失败
            return "jihuoError";
        }
        //认证成功，进行激活
        userService.jihuoUser(username);
        //激活完成跳转到登录页
        return "redirect:/user/toLogin";
    }

}

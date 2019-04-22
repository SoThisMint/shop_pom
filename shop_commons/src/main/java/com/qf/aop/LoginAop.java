package com.qf.aop;

import com.qf.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.URLEncoder;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/18 19:55
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Aspect
public class LoginAop {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 环绕增强，判断当前的目标方法是否作用于登录环境
     */
    @Around("@annotation(IsLogin)")
    public Object isLogin(ProceedingJoinPoint joinPoint) {

        try {
            //判断是否登录？
            //1.获得请求中cookie的login_token
            //1)获得request
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            //2)通过request获得cookie
            String loginToken = null;
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login_token")) {
                    //登录的凭证
                    loginToken = cookie.getValue();
                    break;
                }
            }
            //3)判断凭证是否为空
            User user = null;
            if (loginToken != null) {
                //说明有凭证，但不一定代表登录，通过凭证去查询redis
                //2.查询redis
                System.out.println(loginToken);
                user = (User) redisTemplate.opsForValue().get(loginToken);
            }
            //3.判断是否登录
            if (user == null) {
                //未登录
                //4.如果未登录-判断IsLogin注解上的mustLogin是否为true
                //1）获得@IsLogin
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                Method method = signature.getMethod();
                IsLogin isLogin = method.getAnnotation(IsLogin.class);
                //2)通过注解获得方法返回
                boolean flag = isLogin.mustLogin();
                //3)判断是否强制跳转到登录页面
                if (flag) {
                    //获得当前的路径
                    String returnUrl = request.getRequestURL().toString();
                    //获得当前的请求参数
                    String params = request.getQueryString(); //拿到请求?后面的参数字符串
//                    request.getParameterMap() 获得post请求体中的参数
                    returnUrl = returnUrl + "?" + params;
                    returnUrl = URLEncoder.encode(returnUrl, "utf-8");
                    //跳转到登录页面
                    return "redirect:http://localhost:8084/user/toLogin?returnUrl=" + returnUrl;
                }

//                //用户未登录并且强制登录(mustlogin)为false的情况(只会把cartToken放进本地cookie中？)
//                Object[] params = joinPoint.getArgs();
//                //执行目标方法（addCart）
//                Object result = joinPoint.proceed(params);
//                for (int i = 0; i < params.length; i++) {
//                    if (params[i] != null && params[i].getClass() == User.class) {
//                        System.out.println(params[i]);
//                        break;
//                    }
//                }
//                return result;
            }

            /*用户已登录的情况*/

            //4.将user对象设置到目标方法的形参中
            //1)获得目标方法的原来的参数列表
            Object[] params = joinPoint.getArgs();
            //2)从实际参数数组中找到一个类型为User的参数
            for (int i = 0; i < params.length; i++) {
                if (params[i] != null && params[i].getClass() == User.class) {
                    params[i] = user;
                    break;
                }
            }
            //执行目标方法
            Object result = joinPoint.proceed(params); //--addCart方法
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}

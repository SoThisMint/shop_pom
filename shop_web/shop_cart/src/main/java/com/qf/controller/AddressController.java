package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.aop.IsLogin;
import com.qf.entity.Address;
import com.qf.entity.ShopCart;
import com.qf.entity.User;
import com.qf.service.IAddressService;
import com.qf.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/19 20:24
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    @Reference
    private ICartService cartService;

    @Reference
    private IAddressService addressService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/address")
    @IsLogin(mustLogin = true)
    public String address(User user, HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        String cartToken = null;
        for (Cookie cookie : cookies) {
            if ("cart_token".equals(cookie.getName())) {
                cartToken = cookie.getValue();
                break;
            }
        }
        if (cartToken == null) {
            return "cartlist";
        }
        List<ShopCart> carts = cartService.queryCartsByUid(cartToken, user);
        List<Address> addressList = addressService.getAddressList(user);
        model.addAttribute("carts", carts);
        model.addAttribute("addressList", addressList);
//        System.out.println(addressList);
        return "address";
    }


    @RequestMapping("/add")
    @ResponseBody
    @IsLogin(mustLogin = true)
    public int add(Address address, User user) {
//        System.out.println(user.getId());
        return addressService.insertAddress(address, user.getId());
    }
}

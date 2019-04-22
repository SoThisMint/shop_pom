package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.aop.IsLogin;
import com.qf.entity.Address;
import com.qf.entity.Orders;
import com.qf.entity.User;
import com.qf.service.IAddressService;
import com.qf.service.IOrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/20 16:26
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/order")
public class OrdersController {

    @Reference
    private IOrdersService ordersService;

    @Reference
    private IAddressService addressService;

    /**
     * 下单，默认下单整个购物车
     * @param user
     * @param total_price
     * @param addr_id
     * @param model
     * @return
     */
    @RequestMapping("/order/{total_price}/{addr_id}")
    @IsLogin(mustLogin = true)
    public String order(User user, @PathVariable("total_price") double total_price, @PathVariable("addr_id") int addr_id, Model model) {
//        System.out.println(total_price+"=总价格和地址id="+addr_id);

        Orders order = new Orders();
        Address address = addressService.getAddressById(addr_id);
        order.setAddress(address.getAddress());
        order.setCode(address.getCode());
        order.setPhone(address.getPhone());
        order.setAllprice(BigDecimal.valueOf(total_price));
        order.setCreatetime(new Date());
        order.setUid(user.getId());
        String oid = UUID.randomUUID().toString();
        order.setOid(oid);
        ordersService.add(order,user);
        //订单前端展示数据
        List<Orders> ordersList = ordersService.getOrdersList(user.getId());
        model.addAttribute("ordersList", ordersList);
        return "orders";
    }

}

package com.qf.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.dao.CartMapper;
import com.qf.dao.OrderdetailMapper;
import com.qf.dao.OrdersMapper;
import com.qf.entity.Orderdetail;
import com.qf.entity.Orders;
import com.qf.entity.ShopCart;
import com.qf.entity.User;
import com.qf.service.ICartService;
import com.qf.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/20 17:09
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private OrdersMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private OrderdetailMapper orderdetailMapper;

    @Reference
    private ICartService cartService;

    @Override
    public List<Orders> getOrdersList(int uid) {
        return orderMapper.getOrdersListByUid(uid);
    }

    @Override
    @Transactional
    public int add(Orders orders, User user) {
//        System.out.println("订单的信息：==="+orders);
        //插入到订单数据库
        orderMapper.insert(orders);
        //查询购物车列表(cartToken和user二选一，这里选user)
        List<ShopCart> cartList = cartService.queryCartsByUid(null, user);
        System.out.println(cartList);
        for (ShopCart cart : cartList) {
            System.out.println(cart);
            Orderdetail orderdetail = new Orderdetail(
                    0,
                    cart.getGid(),
                    orders.getOid(),
                    cart.getGoods().getGname(),
                    cart.getGoods().getGimage(),
                    cart.getGoods().getGprice(),
                    cart.getGnumber()
            );
            orderdetailMapper.insert(orderdetail);
        }

        //清空购物车(清除数据库里的该用户所有的购物车)
        QueryWrapper q1 = new QueryWrapper();
        q1.eq("uid", orders.getUid());
        cartMapper.delete(q1);
        return 1;
    }

    @Override
    public User getUserByOid(String oid) {
        return null;
    }

    @Override
    public Orders getOrdersByOid(String oid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("oid", oid);
        return orderMapper.selectOne(queryWrapper);
    }

    @Override
    public int updateOrders(Orders orders) {
        return orderMapper.updateById(orders);
    }
}

package com.qf.shop_service_goods;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.dao.GoodsMapper;
import com.qf.dao.OrdersMapper;
import com.qf.entity.Orders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopServiceGoodsApplicationTests {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    public void contextLoads() {
        Orders order1 = ordersMapper.selectById(1);
        System.out.println(order1);
//        Order order = new Order(1, "123", 10, "dd", "11111111111", "212121", null, new Date(), 0);
//        System.out.println(order);
    }

    @Test
    public void test01() {
        Orders order1 = ordersMapper.selectById(1);
        System.out.println(order1);
    }

    @Test
    public void testGetOrdersListByUid() {
        List<Orders> list = ordersMapper.getOrdersListByUid(10);
        System.out.println(list);
    }

    @Test
    public void testGetOrdersByOid(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("oid","96c50296-ef75-409b-a245-77506cc78fc2");
        Orders orders = ordersMapper.selectOne(queryWrapper);
        System.out.println(orders);
    }


}

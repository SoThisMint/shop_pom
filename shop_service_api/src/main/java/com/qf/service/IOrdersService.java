package com.qf.service;

import com.qf.entity.Orders;
import com.qf.entity.User;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/20 16:58
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface IOrdersService {

    List<Orders> getOrdersList(int uid);

    int add(Orders orders, User user);

    User getUserByOid(String oid);

    Orders getOrdersByOid(String oid);

    int updateOrders(Orders orders);
}

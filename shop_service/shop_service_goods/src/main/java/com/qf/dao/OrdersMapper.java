package com.qf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.Orders;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/20 17:09
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface OrdersMapper extends BaseMapper<Orders> {

    List<Orders> getOrdersListByUid(int uid);
}

package com.qf.service;

import com.qf.entity.Goods;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/9 10:38
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface IGoodsService {

    List<Goods> queryAll();

    int insert(Goods goods);
}

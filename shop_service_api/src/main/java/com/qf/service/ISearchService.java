package com.qf.service;

import com.qf.entity.Goods;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/11 18:52
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface ISearchService {

    /**
     * 根据关键字，搜索商品列表
     * @param keyword
     * @return
     */
    List<Goods> searchGoods(String keyword);

    /**
     * 将商品信息同步到索引库中
     * @param goods
     * @return
     */
    int insertGoods(Goods goods);
}

package com.qf.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dao.GoodsMapper;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import com.qf.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/9 10:41
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Reference
    private ISearchService searchService;

    @Override
    public List<Goods> queryAll() {
        return goodsMapper.selectList(null);
    }

    @Override
    public int insert(Goods goods) {

        //添加商品
        int result = goodsMapper.insert(goods);

        //通过dubbo调用搜索服务，同步索引库
        searchService.insertGoods(goods);
        return result;
    }

    @Override
    public Goods selectById(int id) {
        return goodsMapper.selectById(id);
    }
}

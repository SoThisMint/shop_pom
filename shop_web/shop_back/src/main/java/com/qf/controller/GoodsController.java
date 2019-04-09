package com.qf.controller;

import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/9 10:25
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @RequestMapping("/list")
    public String goodsList(){
        List<Goods> goods = goodsService.queryAll();
        return "goodsList";
    }
}

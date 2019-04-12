package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/12 10:57
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Reference
    private IGoodsService goodsService;

    @Autowired
    private Configuration configuration;

    /**
     * 生成静态页面
     * @return
     */
    @RequestMapping("/createHtml")
    public String createHtml(int gid){
        //通过商品id获得商品详细信息
        Goods goods = goodsService.selectById(gid);

        try {
            Template template = configuration.getTemplate("goodsItem.ftl");
            Map<String,Object> map = new HashMap<>();
            map.put("goods",goods);
            //生成静态页面
            String path = this.getClass().getResource("/static/page/").getPath()+goods.getId()+".html";
            template.process(map,new FileWriter(path));
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

        //通过商品id获得商品详细信息
        return null;
    }
}

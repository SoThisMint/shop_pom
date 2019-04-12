package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.service.ISearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/11 18:28
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Reference
    private ISearchService searchService;

    @RequestMapping("/searchByKeyword")
    public String searchByKeyword(String keyword, Model model) {
        List<Goods> goods = searchService.searchGoods(keyword);
        model.addAttribute("goods", goods);

        return "searchlist";
    }
}

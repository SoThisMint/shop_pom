package com.qf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/9 9:52
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/toPage")
public class PageController {

    @RequestMapping("/{toPage}")
    public String toPage(@PathVariable("toPage") String toPage){
        return toPage;
    }
}

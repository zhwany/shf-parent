package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author mabo
 * @Date 2022/7/19 18:38
 */
@Controller
@RequestMapping
public class IndexController {
    private final static String PAGE_INDEX = "frame/index";
    private final static String PAGE_MAIN = "frame/main";

    /**
     * 跳转到框架首页
     * @return 框架首页的逻辑视图
     */
    @RequestMapping
    public String index(ModelMap model) {
        return PAGE_INDEX;
    }

    /**
     * 跳转到框架主页
     * @return 框架主页的逻辑视图
     */
    @RequestMapping("/main")
    public String main() {
        return PAGE_MAIN;
    }
}

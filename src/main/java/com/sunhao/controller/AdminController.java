package com.sunhao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 项目名称：sunhaocms
 * 类 名 称：AdminController
 * 类 描 述：TODO
 * 创建时间：2019/11/12 7:26 下午
 * 创 建 人：sunhao
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @RequestMapping("index")
    public String index(){
        return "admin/index";
    }

    @RequestMapping("articles")
    public String articles(){
        return "admin/articles/list";
    }
    @RequestMapping("users")
    public String users(@RequestParam(defaultValue = "")String name){
        return "admin/users/list";
    }


}

package com.sunhao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目名称：sunhaocms
 * 类 名 称：UserCOntroller
 * 类 描 述：TODO
 * 创建时间：2019/11/12 11:30 上午
 * 创 建 人：sunhao
 */
@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public  String ConTest(HttpServletRequest request){

        request.setAttribute("info","hello");
        return "user/test";
    }

}
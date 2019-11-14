package com.sunhao.controller;

import com.sunhao.entity.Article;
import com.sunhao.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：ArticleController
 * 类 描 述：TODO
 * 创建时间：2019/11/14 7:19 下午
 * 创 建 人：sunhao
 */
@RequestMapping("article")
@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("detail")
    public String getArticleByid(HttpServletRequest request,Integer id){
        Article article = articleService.getArticleByid(id);
        request.setAttribute("article",article);
        return "article/detail";
    }

}

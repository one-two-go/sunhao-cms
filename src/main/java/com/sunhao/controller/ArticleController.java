package com.sunhao.controller;

import com.sunhao.common.CmsAssert;
import com.sunhao.common.MsgResult;
import com.sunhao.entity.Article;
import com.sunhao.entity.Category;
import com.sunhao.service.ArticleService;
import com.sunhao.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Autowired
    CategoryService categoryService;

    @RequestMapping("detail")
    public String getArticleByid(HttpServletRequest request,Integer id){
        Article article = articleService.getArticleByid(id);
        CmsAssert.AssertTrueHtml(article!=null, "文章不存在");
        request.setAttribute("article",article);
        return "article/detail";
    }

    //按频道获取类别
    @RequestMapping("getCategoryByChannel")
    @ResponseBody
    public MsgResult getCategoryByChannel(int chnId){
     List<Category> categories = categoryService.getCateByChnId(chnId);
        return new MsgResult(1,"",categories);
    }



}

package com.sunhao.controller;

import com.github.pagehelper.PageInfo;
import com.sunhao.entity.Article;
import com.sunhao.entity.Category;
import com.sunhao.entity.Channel;
import com.sunhao.service.ArticleService;
import com.sunhao.service.CategoryService;
import com.sunhao.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：indexController
 * 类 描 述：TODO
 * 创建时间：2019/11/14 12:08 下午
 * 创 建 人：sunhao
 */

@Controller
public class indexController {

    @Autowired
    ChannelService channelService;

    @Autowired
    ArticleService articeService;
    @Autowired
    CategoryService categoryService;


    /**
     *
     * @param request
     * @param chnId  频道id
     * @param page  文章页码
     * @return
     */

    @RequestMapping( "channel")
    public String channels(HttpServletRequest request,
                        @RequestParam(defaultValue = "0") int chnId,
                        @RequestParam(defaultValue = "0") int categoryId,
                        @RequestParam(defaultValue = "1") Integer page) {

        // 回传参数数值
        request.setAttribute("chnId", chnId);
        request.setAttribute("categoryId", categoryId);
        /**
         * 获取频道
         */
        List<Channel> channels = channelService.getChannelList();
        request.setAttribute("channels", channels);
        /**
         * 获取分类
         */
        List<Category> categories = categoryService.getCateByChnId(chnId);
        request.setAttribute("categories",categories);
        /**
         *  获取分类下的文章
         */

        PageInfo<Article> articles = articeService.listBycateId(chnId,categoryId,page);
        request.setAttribute("articles",articles);

        return "channelindex";
    }




    @RequestMapping(value = {"/", "index"})
    public String index(HttpServletRequest request,
                        @RequestParam(defaultValue = "1") Integer page) {
        /**
         *
         * @param request
         * @param chnId  频道id
         * @param page  文章页码
         * @return
         */
        /**
         * 获取频道
         */
        List<Channel> channels = channelService.getChannelList();
        request.setAttribute("channels", channels);
        /**
         * 获取热门文章列表
         */
        PageInfo<Article> hotList = articeService.getHotList(page);
        request.setAttribute("hotList", hotList);
        /**
         * 获取最新文章
         */
        List<Article> newArticle = articeService.getnewArticle(5);
        request.setAttribute("newArticle", newArticle);
        return "index";
    }
}

package com.sunhao.controller;

import com.github.pagehelper.PageInfo;
import com.sunhao.entity.Article;
import com.sunhao.entity.Channel;
import com.sunhao.service.ArticeService;
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
    ArticeService articeService;

    @RequestMapping(value = {"/","index"})
    public String index(HttpServletRequest request,
                        @RequestParam(defaultValue = "1") Integer page){
        /**
         * 获取频道
         */
     List<Channel> channelList = channelService.getChannelList();
     request.setAttribute("channelList",channelList);
        /**
         * 获取热门文章列表
         */

        PageInfo<Article> hotList= articeService.getHotList(page);
        request.setAttribute("hotList",hotList);


        /**
         * 获取最新文章
         */
        List<Article> newArticle = articeService.getnewArticle(5);

        request.setAttribute("newArticle",newArticle);
    return "index";
    }
}

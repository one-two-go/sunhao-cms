package com.sunhao.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sunhao.dao.ElasticDao;
import com.sunhao.entity.Article;
import com.sunhao.entity.Category;
import com.sunhao.entity.Channel;
import com.sunhao.entity.Link;
import com.sunhao.service.ArticleService;
import com.sunhao.service.CategoryService;
import com.sunhao.service.ChannelService;
import com.sunhao.service.LinkService;
import com.sunhao.utils.HLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    LinkService linkService;
    @Autowired
    ElasticDao elasticDao;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;


//    页面搜索
    @GetMapping("/index")
    public String index(HttpServletRequest request,String key,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3") Integer pageSize){
        long start = System.currentTimeMillis();
        AggregatedPage<?> selectObjects = HLUtils.selectObjects(elasticsearchTemplate, Article.class, 1, 5, new String[]{"title"}, "id", key);
        List list = selectObjects.getContent();
        PageInfo pageInfo = new PageInfo<>(list);

        //当前页
        pageInfo.setPageNum(page);
        //每页显示多少条
        pageInfo.setPageSize(pageSize);
        //总条数
        pageInfo.setTotal(selectObjects.getTotalElements());
        //总页数
        int total = (int) selectObjects.getTotalElements();
        int pages = total%pageSize==0?total/pageSize:total/pageSize+1;
        pageInfo.setPages(pages);
        if(page==pages){
            page = pages;
        }
        //上一页 和 下一页
        pageInfo.setNextPage(page+1);
        pageInfo.setPrePage(page-1);
        request.setAttribute("hotList",pageInfo);
        request.setAttribute("key",key);
        long end = System.currentTimeMillis();
        System.out.println("本次查询共耗时"+(end-start));
        return "index";
    }

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
    public String index(HttpServletRequest request, HttpServletResponse response,
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
        //获取最新图片信息
        List<Article> imgArticles = articeService.getImgArticles(10);

        //获取友情链接
        PageInfo<Link> pageInfo = linkService.getList(1);
        List<Link> linkList = pageInfo.getList();
        request.setAttribute("linkList",linkList);

        return "index";
    }
}

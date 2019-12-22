package com.sunhao.controller;

import com.github.pagehelper.PageInfo;
import com.sunhao.common.ConstantClass;
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
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
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

    /**
     * 完成es的搜索
//     */
//    List<Article> list = elasticDao.findByTitle(key);
//      for(Article article :list){
//        System.err.println(article);
//    }
//    PageInfo<Article> pageInfo = new PageInfo<>(list);
//        request.setAttribute("hotList",pageInfo);
//        return "index";
    @GetMapping("index")
    public String Search(HttpServletRequest request,String key,@RequestParam(defaultValue = "1")Integer page ){
        if(page==0){
            page=1;
        }
        AggregatedPage<?> selectObjects = HLUtils.selectObjects(elasticsearchTemplate, Article.class, page,
                ConstantClass.PAGE_SIZE, new String[]{"title"}, "id", key);
        List<Article> list = (List<Article>) selectObjects.getContent();
        PageInfo<Article> pageInfo = new PageInfo<>(list);
        //当前页
        pageInfo.setPageNum(page);
        //每页显示多少条
        pageInfo.setPageSize(ConstantClass.PAGE_SIZE);
        //总条数
        pageInfo.setTotal(selectObjects.getTotalElements());
        //总页数
        int total = (int) selectObjects.getTotalElements();
        int pageSize = ConstantClass.PAGE_SIZE;
        int pages = total%page==0?total/pageSize:total/pageSize+1;
        pageInfo.setPages(pages);
        if (page == pages){
            page = pages;
        }
        pageInfo.setPrePage(page-1);
        pageInfo.setNextPage(page+1);
        request.setAttribute("hotList",pageInfo);
        request.setAttribute("key",key);
        return "index";
    }
//@GetMapping("index")
//public String search(Model model, String key, @RequestParam(defaultValue = "1") int page) {
    // 注入es的仓库
//    if (page == 0) {
//        page = 1;
//    }
//
//    // 根据标题来搜索
//    // List<Article> list = articleResp.findByTitle(key);
//    AggregatedPage<?> selectObjects = HLUtils.selectObjects(elasticsearchTemplate, Article.class, page,
//            ConstantClass.PAGE_SIZE, new String[] { "title" }, "id", key);
////    List<Article> list = (List<Article>) selectObjects.getContent();
//    List<Article> list = (List<Article>) selectObjects.getContent();
//    PageInfo<Article> pageInfo = new PageInfo<>(list);
//    pageInfo.setPageNum(page);// 当前页
//    pageInfo.setPageSize(ConstantClass.PAGE_SIZE);// 每页显示多少条
//    pageInfo.setTotal(selectObjects.getTotalElements());// 总条数
//    int totalRecord = (int) selectObjects.getTotalElements();
//    int pages = totalRecord % ConstantClass.PAGE_SIZE == 0 ? totalRecord / ConstantClass.PAGE_SIZE
//            : totalRecord / ConstantClass.PAGE_SIZE + 1;
//    pageInfo.setPages(pages);
//
//    if (page == pages) {
//        page = pages;
//    }
//    pageInfo.setPrePage(page - 1);
//    pageInfo.setNextPage(page + 1);
//    model.addAttribute("hotList", pageInfo);
//
//    model.addAttribute("key", key);
//    return "index";
//}


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

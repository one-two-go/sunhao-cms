package com.sunhao.controller;

import com.google.gson.Gson;
import com.sunhao.common.CmsAssert;
import com.sunhao.common.MsgResult;
import com.sunhao.dao.ArticeMapper;
import com.sunhao.entity.Article;
import com.sunhao.entity.Category;
import com.sunhao.entity.Image;
import com.sunhao.entity.TypeEnum;
import com.sunhao.service.ArticleService;
import com.sunhao.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ThreadPoolTaskExecutor executor;
    @Autowired
    KafkaTemplate kafkaTemplate;

    @RequestMapping("showdetail")
    public String getArticleByid(HttpServletRequest request, Integer id) {
        Article article = articleService.getArticleByid(id);
// ##################################AAAAAAAAAA===========================
//        //获取用户IP
//        String ip = request.getRemoteAddr();
//        String key = "Hits_"+id+"_"+ip;
////        查询Redis里有没有该key
//        String redisDate = (String) redisTemplate.opsForValue().get(key);
//        if (redisDate==null){
////            则使用Spring线程池异步执行数据库加1操作
//            executor.execute(new Runnable() {
//                @Override
//                public void run() {
////                    异步执行数据库加1操作
//                    article.setHits(article.getHits()+1);
//                    articleService.updateHits(article);
//                    redisTemplate.opsForValue().set(key,"",5, TimeUnit.MINUTES);
//					System.err.println("点击量已经加1了"+"=="+key);
//                }
//            });
//        }

        /**
         *         当用户浏览文章时，往Kafka发送文章ID，在消费端获取文章ID，再执行数据库加1操作
         */kafkaTemplate.send("articles","hits="+id);

        CmsAssert.AssertTrueHtml(article != null, "文章不存在");
        request.setAttribute("article", article);
        if (article.getArticleType() == TypeEnum.HTML)
            return "article/detail";
        else {
            Gson gson = new Gson();
            // 文章内容转换成集合对象
            List<Image> imgs = gson.fromJson(article.getContent(), List.class);
            article.setImgList(imgs);
            System.out.println(" article is " + article);
            return "article/detailimg";
        }
    }

    //按频道获取类别
    @RequestMapping("getCategoryByChannel")
    @ResponseBody
    public MsgResult getCategoryByChannel(int chnId) {
        List<Category> categories = categoryService.getCateByChnId(chnId);
        return new MsgResult(1, "", categories);
    }


}

package service;

import com.github.pagehelper.PageInfo;
import com.sunhao.entity.Article;
import com.sunhao.service.ArticleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：ArticleTest
 * 类 描 述：TODO
 * 创建时间：2019/11/14 1:33 下午
 * 创 建 人：sunhao
 */
public class ArticleTest extends BaseTest {

    @Autowired
    ArticleService ArticeService;

    @Test
    public void test(){

        PageInfo<Article> info =  ArticeService.getHotList(1);
        List<Article> list = info.getList();
        list.forEach(article -> {
            System.out.println("热门文章是 ："+ article);
        });
    }
}

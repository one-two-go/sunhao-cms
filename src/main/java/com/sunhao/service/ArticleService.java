package com.sunhao.service;

import com.github.pagehelper.PageInfo;
import com.sunhao.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：ArticeService
 * 类 描 述：TODO
 * 创建时间：2019/11/14 12:12 下午
 * 创 建 人：sunhao
 */

public interface ArticleService {

    PageInfo<Article> getPageList(int page, int status);

    PageInfo<Article> getHotList(Integer page);

    List<Article> getnewArticle(int i);

    Article getArticleByid(Integer id);


    PageInfo<Article> listBycateId(int chnId, int categoryId, Integer page);

    PageInfo<Article> listByUser(int page, Integer UserId);

    Article checkExist(Integer id);

    int delete(Integer id);

    int add(Article article);

    Article getDetailById(Integer id);

    int apply(int id, int status);

    int setHot(int id, int status);
}

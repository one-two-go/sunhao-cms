package com.sunhao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunhao.common.ConstantClass;
import com.sunhao.dao.ArticeMapper;
import com.sunhao.entity.Article;
import com.sunhao.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：ArticeServiceImpl
 * 类 描 述：TODO
 * 创建时间：2019/11/14 1:02 下午
 * 创 建 人：sunhao
 */
@Service
public class ArticeServiceImpl implements ArticleService {

    @Autowired
    ArticeMapper articeMapper;

    @Override
    public PageInfo<Article> getHotList(Integer page) {
        PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
        return new PageInfo<Article>(articeMapper.getHotList());
    }

    @Override
    public List<Article> getnewArticle(int i) {
        return articeMapper.getnewArticle(i);
    }

    @Override
    public Article getArticleByid(Integer id) {
        return articeMapper.getArticleByid(id);
    }

    @Override
    public PageInfo<Article> listBycateId(int chnId, int categoryId, Integer page) {
        PageHelper.startPage(page,ConstantClass.PAGE_SIZE);
        return new PageInfo<Article>(articeMapper.listBycateId(chnId,categoryId,page));
    }

    @Override
    public PageInfo<Article> listByUser(int page, Integer UserId) {
        PageHelper.startPage(page,ConstantClass.PAGE_SIZE);

        return new PageInfo<Article>(articeMapper.listByUser(UserId));
    }

    @Override
    public Article checkExist(Integer id) {
        return articeMapper.checkExist(id);
    }

    @Override
    public int delete(Integer id) {
        return articeMapper.delete(id);
    }

}

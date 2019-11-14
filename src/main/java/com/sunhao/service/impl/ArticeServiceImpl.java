package com.sunhao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunhao.common.ConstantClass;
import com.sunhao.dao.ArticeMapper;
import com.sunhao.entity.Article;
import com.sunhao.service.ArticeService;
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
public class ArticeServiceImpl implements ArticeService {

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
}

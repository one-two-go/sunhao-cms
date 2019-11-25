package com.sunhao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunhao.dao.LinkMapper;
import com.sunhao.entity.Link;
import com.sunhao.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：sunhaocms
 * 类 名 称：LinkServiceImpl
 * 类 描 述：TODO
 * 创建时间：2019/11/24 8:51 下午
 * 创 建 人：sunhao
 */

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    LinkMapper linkMapper;


    @Override
    public PageInfo<Link> getList(Integer page) {
        PageHelper.startPage(page,5);

        return new PageInfo<Link>(linkMapper.getList());
    }

    @Override
    public int add(Link link) {
        return linkMapper.add(link);
    }

    @Override
    public int delLink(int id) {
        return linkMapper.delLink(id);
    }

    @Override
    public int update(Link link) {

        return linkMapper.update(link);
    }

    @Override
    public Link getListById(int id) {
        return linkMapper.getListById(id);
    }
}

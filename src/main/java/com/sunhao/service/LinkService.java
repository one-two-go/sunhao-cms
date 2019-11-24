package com.sunhao.service;

import com.github.pagehelper.PageInfo;
import com.sunhao.entity.Link;

/**
 * 项目名称：sunhaocms
 * 类 名 称：LinkService
 * 类 描 述：TODO
 * 创建时间：2019/11/24 8:51 下午
 * 创 建 人：sunhao
 */
public interface LinkService {
    PageInfo<Link> getList(Integer page);

    int add(Link link);
}

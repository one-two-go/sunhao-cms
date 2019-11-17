package com.sunhao.service;

import com.sunhao.entity.Category;

import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：CategoryService
 * 类 描 述：TODO
 * 创建时间：2019/11/15 2:12 下午
 * 创 建 人：sunhao
 */
public interface CategoryService {


    List<Category> getCateByChnId(int chnId);
}

package com.sunhao.dao;

import com.sunhao.entity.Category;

import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：CateGroyMapper
 * 类 描 述：TODO
 * 创建时间：2019/11/14 1:30 下午
 * 创 建 人：sunhao
 */
public interface CategoryMapper {
    List<Category> getCateByChnId(int chnId);

}

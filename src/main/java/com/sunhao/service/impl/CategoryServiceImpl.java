package com.sunhao.service.impl;

import com.sunhao.dao.CategoryMapper;
import com.sunhao.entity.Category;
import com.sunhao.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：CategoryServiceImpl
 * 类 描 述：TODO
 * 创建时间：2019/11/15 2:14 下午
 * 创 建 人：sunhao
 */
@Service
public class CategoryServiceImpl  implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List<Category> getCateByChnId(int chnId) {
        return categoryMapper.getCateByChnId(chnId);
    }

}

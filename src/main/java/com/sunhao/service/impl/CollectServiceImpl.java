package com.sunhao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunhao.dao.CollectMapper;
import com.sunhao.entity.Collect;
import com.sunhao.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：sunhaocms
 * 类 名 称：CollectServiceImpl
 * 类 描 述：TODO
 * 创建时间：2019/11/25 4:08 下午
 * 创 建 人：sunhao
 */
@Service
public class CollectServiceImpl  implements CollectService {

    @Autowired
    CollectMapper collectMapper;
    @Override
    public int addcollect(Collect collect) {
        return collectMapper.addcollect(collect);
    }

    //获取列表
    @Override
    public PageInfo<Collect> getList(int page) {
        PageHelper.startPage(page,5);
        return new PageInfo<Collect>(collectMapper.getList());
    }

    //删除选中的收藏
    @Override
    public int delCollect(int id) {
        return collectMapper.delCollect(id);
    }

    @Override
    public Collect getListById(int id) {
        return collectMapper.getListById(id);
    }

    @Override
    public int updateCollect(Collect collect) {
        return collectMapper.updateCollect(collect);
    }
}

package com.sunhao.service;

import com.github.pagehelper.PageInfo;
import com.sunhao.entity.Collect;

/**
 * 项目名称：sunhaocms
 * 类 名 称：CollectService
 * 类 描 述：TODO
 * 创建时间：2019/11/25 4:06 下午
 * 创 建 人：sunhao
 */
public interface CollectService {

    //展示文章中 添加文章到收藏夹
    int addcollect(Collect collect);

    // 查看收藏夹的文章
    PageInfo<Collect> getList(int page);

    int delCollect(int id);

    Collect getListById(int id);

    int updateCollect( Collect collect);

}

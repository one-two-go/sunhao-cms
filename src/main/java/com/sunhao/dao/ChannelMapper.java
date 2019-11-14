package com.sunhao.dao;

import com.sunhao.entity.Channel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：ChannelMapper
 * 类 描 述：TODO
 * 创建时间：2019/11/14 12:22 下午
 * 创 建 人：sunhao
 */
public interface ChannelMapper {

    @Select("select * from cms_channel ORDER BY id")
    List<Channel> getChannelList();
}

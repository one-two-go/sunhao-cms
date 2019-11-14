package com.sunhao.service.impl;

import com.sunhao.dao.ChannelMapper;
import com.sunhao.entity.Channel;
import com.sunhao.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：ChannelServiceImpl
 * 类 描 述：TODO
 * 创建时间：2019/11/14 12:20 下午
 * 创 建 人：sunhao
 */

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    ChannelMapper channelMapper;

    @Override
    public List<Channel> getChannelList() {
        return channelMapper.getChannelList();
    }
}

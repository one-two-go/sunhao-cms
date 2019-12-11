package com.sunhao.kafka;

import com.alibaba.fastjson.JSON;
import com.sunhao.dao.ArticeMapper;
import com.sunhao.entity.Article;
import com.sunhao.utils.UserUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

/**
 * 项目名称：sunhaocms
 * 类 名 称：MsgListener1
 * 类 描 述：文章监听
 * 创建时间：2019/12/11 11:24 上午
 * 创 建 人：sunhao
 */
public class ArticleListener implements MessageListener<String,String> {


    @Autowired
    ArticeMapper articeMapper;

    @Override
    public void onMessage(ConsumerRecord<String, String> data) {
        String jsonString = data.value();
        Article article = JSON.parseObject(jsonString, Article.class);
        System.out.println(article);
            articeMapper.add(article);
    }
}

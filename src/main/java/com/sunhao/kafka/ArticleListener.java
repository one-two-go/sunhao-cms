package com.sunhao.kafka;

import com.alibaba.fastjson.JSON;
import com.sunhao.dao.ArticeMapper;
import com.sunhao.entity.Article;
import com.sunhao.utils.UserUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListener;

/**
 * 项目名称：sunhaocms
 * 类 名 称：MsgListener1
 * 类 描 述：文章监听
 * 创建时间：2019/12/11 11:24 上午
 * 创 建 人：sunhao
 */
public class ArticleListener implements MessageListener<String, String> {


    @Autowired
    ArticeMapper articeMapper;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public void onMessage(ConsumerRecord<String, String> data) {
        String jsonString = data.value();
        if (jsonString.startsWith("del")) {
            System.err.println(jsonString);
            redisTemplate.delete("hot_articles");
        } else if (jsonString.startsWith("update")){
            System.err.println(jsonString);
            redisTemplate.delete("hot_articles");
        } else if (jsonString.startsWith("add")){
            System.err.println(jsonString);
            redisTemplate.delete("hot_articles");
        } else {
            Article article = JSON.parseObject(jsonString, Article.class);
            System.err.println(article);
            articeMapper.add(article);
        }
    }
}

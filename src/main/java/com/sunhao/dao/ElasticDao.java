package com.sunhao.dao;

import com.sunhao.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：ElasticDao
 * 类 描 述：TODO
 * 创建时间：2019/12/17 2:06 下午
 * 创 建 人：sunhao
 */
//可以实现简单的crud
public interface ElasticDao extends ElasticsearchRepository<Article,Integer> {
//    List<Article> findByContent(String key);

    List<Article> findByTitle(String key);

}

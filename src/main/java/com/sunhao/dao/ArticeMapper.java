package com.sunhao.dao;

import com.sunhao.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：ArticeMapper
 * 类 描 述：TODO
 * 创建时间：2019/11/14 1:09 下午
 * 创 建 人：sunhao
 */
public interface ArticeMapper {

    List<Article> getHotList();

    List<Article> getnewArticle(int i);

    Article getArticleByid(Integer id);

    List<Article> listBycateId(@Param("chnId") int chnId, @Param("categoryId") int categoryId, @Param("page") Integer page);

    List<Article> listByUser(Integer userId);

    @Select("SELECT * FROM cms_article where id = #{id}")
    @ResultType(Article.class)
    Article checkExist(Integer id);

    @Update("update cms_article set deleted =1 where id=#{id}")
    int delete(Integer id);
}

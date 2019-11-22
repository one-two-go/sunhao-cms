package com.sunhao.dao;

import com.sunhao.entity.Article;
import org.apache.ibatis.annotations.*;

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

    @Insert("INSERT INTO cms_article("
            + " title,content,picture,channel_id,category_id,"
            + " user_id,hits,hot,status,deleted,"
            + " created,updated,commentCnt,articleType) "
            + " values(#{title},#{content},#{picture},#{channelId},#{categoryId}," +
            "#{userId},#{hits},#{hot},#{status},#{deleted}," +
            "now(),now(),#{commentCnt},#{articleType})")
    int add(Article article);

    List<Article> getPageList(int status);

    /**
     * 获取文章详情 不考虑状态
     * @param id
     * @return
     */
    Article getDetailById(Integer id);

    @Update("UPDATE cms_article SET status = #{status} WHERE id = #{id}")
    int apply(@Param("id") int id, @Param("status")int status);

    @Update("UPDATE cms_article SET hot=#{status} WHERE id = #{id}")
    int setHot(@Param("id") int id, @Param("status") int status);

    @Update("UPDATE cms_article SET title=#{title},content=#{content},"
            + "picture=#{picture},channel_id=#{channelId},"
            + "category_id=#{categoryId},status=0,updated=now() WHERE id=#{id}")
    int update(Article article);

    @Insert("REPLACE cms_favorite(user_id,article_id,created) values(#{userId},#{articleId},now())")
    int favorite(@Param("userId") Integer userId,@Param("articleId") Integer articleId);
}

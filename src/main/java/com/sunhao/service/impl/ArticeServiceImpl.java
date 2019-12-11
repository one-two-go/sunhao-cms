package com.sunhao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunhao.common.ConstantClass;
import com.sunhao.dao.ArticeMapper;
import com.sunhao.entity.Article;
import com.sunhao.entity.Comment;
import com.sunhao.entity.User;
import com.sunhao.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：ArticeServiceImpl
 * 类 描 述：TODO
 * 创建时间：2019/11/14 1:02 下午
 * 创 建 人：sunhao
 */
@Service
public class ArticeServiceImpl implements ArticleService {

    @Autowired
    ArticeMapper articeMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public PageInfo<Article> getPageList(int page, int status) {
        PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
        return new PageInfo<Article>(articeMapper.getPageList(status));
    }

    @Override
    //获取热门
    public PageInfo<Article> getHotList(Integer page) {
        //判断redis中是否有值
        List hotArticles = redisTemplate.opsForList().range("hot_articles", 0, -1);
        //有的话，查询
        if(hotArticles!=null&&hotArticles.size()>0){
            System.out.println("从redis获取热门文章！！！");
            return new PageInfo<Article>(hotArticles);
        }
//        没有的话，从mysql查询，然后保存到redis中
        List<Article> hotList = articeMapper.getHotList();
        //添加到redis中
        redisTemplate.opsForList().leftPushAll("hot_articles",hotList.toArray());

//        PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
        return new PageInfo<Article>(hotList);
    }

    @Override
    public List<Article> getnewArticle(int i) {
        return articeMapper.getnewArticle(i);
    }

    @Override
    public Article getArticleByid(Integer id) {
        return articeMapper.getArticleByid(id);
    }

    @Override
    public PageInfo<Article> listBycateId(int chnId, int categoryId, Integer page) {
        PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
        return new PageInfo<Article>(articeMapper.listBycateId(chnId, categoryId, page));
    }

    @Override
    public PageInfo<Article> listByUser(int page, Integer UserId) {
        PageHelper.startPage(page, ConstantClass.PAGE_SIZE);

        return new PageInfo<Article>(articeMapper.listByUser(UserId));
    }

    @Override
    public Article checkExist(Integer id) {
        return articeMapper.checkExist(id);
    }

    @Override
    public int delete(Integer id) {
        return articeMapper.delete(id);
    }

    @Override
    public int add(Article article) {
        return articeMapper.add(article);
    }

    /**
     * 获取文章详情 不考虑状态，只判断是否删除
     *
     * @param id
     * @return
     */
    @Override
    public Article getDetailById(Integer id) {
        return articeMapper.getDetailById(id);
    }

    @Override
    public int apply(int id, int status) {
        return articeMapper.apply(id, status);
    }

    @Override
    public int setHot(int id, int status) {
        return articeMapper.setHot(id, status);
    }

    @Override
    public int update(Article article) {
        return articeMapper.update(article);
    }

    @Override
    public int favorite(Integer UserId, Integer articleId) {
        return articeMapper.favorite(UserId, articleId);
    }

    /**
     * 获取十篇文章信息
     *
     * @param num
     */
    @Override
    public List<Article> getImgArticles(int num) {
        return articeMapper.getImgArticles(num);
    }


    /**
     * 发表文章评论
     *
     * @param UserId
     * @param articleId
     * @param content
     * @return
     */
    @Override
    public int addComment(Integer UserId, Integer articleId, String content) {

        int result = articeMapper.addComment(UserId, articleId, content);

        if (result > 0) {
            //文章评论加1
            articeMapper.addCommentCount(articleId);
        } else {
            return 0;
        }

        return result;
    }

    /**
     * 获取文章评论的列表
     *
     * @param page
     * @param articleId
     * @return
     */
    @Override
    public PageInfo<Comment> getCommentList(int page, Integer articleId) {
        PageHelper.startPage(page, 10);

        return new PageInfo<Comment>(articeMapper.getCommentList(articleId));
    }

    @Override
    public PageInfo<Comment> getComList(int page) {
        PageHelper.startPage(page, 10);

        return new PageInfo<Comment>(articeMapper.getComList());
    }

}

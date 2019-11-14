package com.sunhao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 项目名称：sunhaocms
 * 类 名 称：Article
 * 类 描 述：TODO
 * 创建时间：2019/11/14 12:45 下午
 * 创 建 人：sunhao
 */
public class Article implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 6320126833474686896L;

    private Integer id               ;
    // 文章标题
    private String title            ;
    // 文章内容
    private String content          ;
    //标题图片的url 地址
    private String picture          ;
    // 频道
    private Integer channelId       ;
    private Channel channel       ;

    private String categoryId      ;
    // 文章的分类
    private Category category;


    private Integer userId          ;
    private User user          ;

    // 点击数量
    private int  hits             ;
    // 是否为热门文章 1 是  0 否
    private int hot              ;
    // 0 待审核  1 审核通过  2 审核未通过
    private int status           ;
    //是否被删除
    private int deleted          ;

    //发表时间
    private String created          ;

    //最后修改时间
    private Date updated          ;

    // 评论的数量
    private int commentCnt       ;
    //文章类型
    private int articleType      ;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public int getCommentCnt() {
        return commentCnt;
    }

    public void setCommentCnt(int commentCnt) {
        this.commentCnt = commentCnt;
    }

    public int getArticleType() {
        return articleType;
    }

    public void setArticleType(int articleType) {
        this.articleType = articleType;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", picture='" + picture + '\'' +
                ", channelId=" + channelId +
                ", channel=" + channel +
                ", categoryId='" + categoryId + '\'' +
                ", category=" + category +
                ", userId=" + userId +
                ", user=" + user +
                ", hits=" + hits +
                ", hot=" + hot +
                ", status=" + status +
                ", deleted=" + deleted +
                ", created='" + created + '\'' +
                ", updated=" + updated +
                ", commentCnt=" + commentCnt +
                ", articleType=" + articleType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return hits == article.hits &&
                hot == article.hot &&
                status == article.status &&
                deleted == article.deleted &&
                commentCnt == article.commentCnt &&
                articleType == article.articleType &&
                Objects.equals(id, article.id) &&
                Objects.equals(title, article.title) &&
                Objects.equals(content, article.content) &&
                Objects.equals(picture, article.picture) &&
                Objects.equals(channelId, article.channelId) &&
                Objects.equals(channel, article.channel) &&
                Objects.equals(categoryId, article.categoryId) &&
                Objects.equals(category, article.category) &&
                Objects.equals(userId, article.userId) &&
                Objects.equals(user, article.user) &&
                Objects.equals(created, article.created) &&
                Objects.equals(updated, article.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, picture, channelId, channel, categoryId, category, userId, user, hits, hot, status, deleted, created, updated, commentCnt, articleType);
    }
}

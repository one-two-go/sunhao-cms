package com.sunhao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 项目名称：sunhaocms
 * 类 名 称：Comment
 * 类 描 述：TODO
 * 创建时间：2019/11/24 7:38 下午
 * 创 建 人：sunhao
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = -1194490081967120360L;

    private Integer articleId;
    private Integer userId;
    private String content;
    private Date created;
    private Integer id;

    @Override
    public String toString() {
        return "Comment{" +
                "articleId=" + articleId +
                ", userId=" + userId +
                ", content=" + content +
                ", created=" + created +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(articleId, comment.articleId) &&
                Objects.equals(userId, comment.userId) &&
                Objects.equals(content, comment.content) &&
                Objects.equals(created, comment.created) &&
                Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, userId, content, created, id);
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

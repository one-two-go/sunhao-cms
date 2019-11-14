package com.sunhao.entity;

import java.util.Objects;

/**
 * 项目名称：sunhaocms
 * 类 名 称：Category
 * 类 描 述：TODO
 * 创建时间：2019/11/14 12:57 下午
 * 创 建 人：sunhao
 */
public class Category {

    private Integer id;
    private String name;
    private String channelId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(channelId, category.channelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, channelId);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", channelId='" + channelId + '\'' +
                '}';
    }
}

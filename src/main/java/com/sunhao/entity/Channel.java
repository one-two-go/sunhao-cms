package com.sunhao.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 项目名称：sunhaocms
 * 类 名 称：Channel
 * 类 描 述：TODO
 * 创建时间：2019/11/14 12:14 下午
 * 创 建 人：sunhao
 */
//channel 频道
public class Channel  implements Serializable {


    private static final long serialVersionUID = -5353757795253167130L;
    private  Integer id;
    private String name;
    //描述
    private String description;
    //图标
    private String icon;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Channel)) return false;
        Channel channel = (Channel) o;
        return Objects.equals(id, channel.id) &&
                Objects.equals(name, channel.name) &&
                Objects.equals(description, channel.description) &&
                Objects.equals(icon, channel.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, icon);
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}

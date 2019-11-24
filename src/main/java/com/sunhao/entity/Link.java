package com.sunhao.entity;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目名称：sunhaocms
 * 类 名 称：Link
 * 类 描 述：TODO
 * 创建时间：2019/11/24 8:49 下午
 * 创 建 人：sunhao
 */
public class Link implements Serializable {
    private static final long serialVersionUID = 4816368368587130309L;

    private int id;

    @Length(max = 255, min = 5, message = "长度超出范围")
    //@URL
    private String url;

    @Length(max = 10, min = 2)
    private String name;

    private Date created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

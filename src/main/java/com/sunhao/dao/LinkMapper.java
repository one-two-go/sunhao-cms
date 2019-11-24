package com.sunhao.dao;

import com.sunhao.entity.Link;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：LinkMapper
 * 类 描 述：TODO
 * 创建时间：2019/11/24 8:52 下午
 * 创 建 人：sunhao
 */
public interface LinkMapper {

    @Select("SELECT * FROM cms_link ORDER BY created DESC")
    List<Link> getList();

    @Insert("INSERT INTO cms_link(url,name,created)VALUES(#{url},#{name},now())")
    int add(Link link);
}

package com.sunhao.dao;

import com.sunhao.entity.Link;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Delete("DELETE FROM cms_link WHERE id = #{id}")
    int delLink(int id);

    @Update("UPDATE cms_link SET name = #{ name} , url = #{url} WHERE id = #{id}")
    int update(Link link);

    @Select("SELECT * FROM cms_link WHERE id = #{id}")
    Link getListById(int id);
}

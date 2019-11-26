package com.sunhao.dao;

import com.sunhao.entity.Collect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：CollectMapper
 * 类 描 述：TODO
 * 创建时间：2019/11/25 4:09 下午
 * 创 建 人：sunhao
 */
public interface CollectMapper {

    @Insert("INSERT INTO cms_collect (userId,url,name,created) "
            + " VALUES(#{userId},#{url},#{name},now())")
    int addcollect(Collect collect);

    @Select("SELECT * FROM cms_collect")
    List<Collect> getList();

    @Delete("DELETE FROM cms_collect WHERE id = #{value}")
    int delCollect(int id);

    @Select("SELECT * FROM cms_collect WHERE id = #{value}")
    Collect getListById(int id);

    @Update("UPDATE cms_collect SET url=#{url},name=#{name},created=now() WHERE id=#{id}")
    int updateCollect(Collect collect);
}

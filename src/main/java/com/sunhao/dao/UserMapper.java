package com.sunhao.dao;

import com.sunhao.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：UserMapper
 * 类 描 述：TODO
 * 创建时间：2019/11/13 11:30 上午
 * 创 建 人：sunhao
 */
public interface UserMapper {

    List<User> getUserList(String name);

    @Select("SELECT * FROM cms_user WHERE id = #{value}")
    User findUserById(Integer userId);

    @Update("UPDATE cms_user SET locked = #{status} where id = #{userId}")
    int updateStatus(@Param("userId") Integer userId,
                     @Param("status") int status);

}

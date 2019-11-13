package com.sunhao.service;

import com.github.pagehelper.PageInfo;
import com.sunhao.entity.User;

/**
 * 项目名称：sunhaocms
 * 类 名 称：UserService
 * 类 描 述：TODO
 * 创建时间：2019/11/13 11:17 上午
 * 创 建 人：sunhao
 */
public interface UserService {

    /**
     * 获取用户信息
     * @param name
     * @param page
     * @return
     */
    PageInfo<User> getUserList(String name, Integer page);

    /**
     * 根据id判断用户是否存在
     * @param userId
     * @return
     */
    User findUserById(Integer userId);

    /**
     * 修改解禁状态
     * @param userId
     * @param status
     * @return
     */
    int updateStatus(Integer userId, int status);
}

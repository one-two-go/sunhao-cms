package com.sunhao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunhao.common.ConstantClass;
import com.sunhao.dao.UserMapper;
import com.sunhao.entity.User;
import com.sunhao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：sunhaocms
 * 类 名 称：UserServiceImpl
 * 类 描 述：TODO
 * 创建时间：2019/11/13 11:29 上午
 * 创 建 人：sunhao
 */

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public PageInfo<User> getUserList(String name, Integer page) {
        PageHelper.startPage(page, ConstantClass.PAGE_SIZE);

        return new PageInfo<User>(userMapper.getUserList(name));
    }

    @Override
    public User findUserById(Integer userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public int updateStatus(Integer userId, int status) {
        return userMapper.updateStatus(userId,status);
    }


}

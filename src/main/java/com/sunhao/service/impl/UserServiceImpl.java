package com.sunhao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunhao.common.CmsAssert;
import com.sunhao.common.ConstantClass;
import com.sunhao.common.Md5;
import com.sunhao.dao.UserMapper;
import com.sunhao.entity.User;
import com.sunhao.service.UserService;
import com.sunhao.utils.CmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.AssertTrue;

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

    @Override
    public int register(User user) {

        //判断用户是否存在
        User exitUser  =findUserByName(user.getUsername());
        CmsAssert.AssertTrue(exitUser==null,"用户名已经存在");
        //加盐
        String passwordSalt = Md5.password(user.getPassword(),user.getUsername().substring(0,2));
        user.setPassword(passwordSalt);

        return userMapper.add(user);
    }
    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public User login(User user) {
       //看数据库里面有这个name 没
        User loginUser = findUserByName(user.getUsername());
        if(loginUser==null){
            return null;
        }
        //计算加盐 加密的后的password
        String pwdSaltMd5 = Md5.password(user.getPassword(),user.getUsername().substring(0,2));
        //判断输入的密码和数据库对应的密码是否一致
        if (pwdSaltMd5.equals(loginUser.getPassword())){
            return loginUser;
        }else {
            //登陆失败
            return null;
        }
    }


}

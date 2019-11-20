package com.sunhao.common;

import java.io.Serializable;

/**
 * 项目名称：sunhaocms
 * 类 名 称：ConstantClass
 * 类 描 述：用于保存CMS系统的常量
 * 创建时间：2019/11/13 1:05 下午
 * 创 建 人：sunhao
 */
public class ConstantClass  {

    //普通用户
    public static final int USER_ROLE_GENERAL=0;

    //管理员
    public  static final int USER_ROLE_ADMIN=1;

    public static final String USER_KEY="SESSION_USER_KEY";

    /**
     * 分页页数
     */
    public static final  int PAGE_SIZE=3;

}

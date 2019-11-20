package com.sunhao.common;

import com.sunhao.utils.DateUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 * 项目名称：sunhaocms
 * 类 名 称：Md5
 * 类 描 述：加密的工具类
 * 创建时间：2019/11/18 8:12 下午
 * 创 建 人：sunhao
 */
public class Md5 {
    public  static  String  password(String password,String salt){

        return DigestUtils.md5Hex(password+"::::"+salt);
    }
}

package com.sunhao.common;

import javax.validation.constraints.AssertTrue;

/**
 * 项目名称：sunhaocms
 * 类 名 称：CmsAssert
 * 类 描 述：TODO
 * 创建时间：2019/11/18 8:05 下午
 * 创 建 人：sunhao
 */
public class CmsAssert  {
    public static void AssertTrue(boolean express, String msg){
        if(!express){
            throw new CmcException(msg);
        }

    }

}

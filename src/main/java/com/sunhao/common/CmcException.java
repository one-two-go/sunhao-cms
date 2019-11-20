package com.sunhao.common;

/**
 * 项目名称：sunhaocms
 * 类 名 称：CmcException
 * 类 描 述：TODO
 * 创建时间：2019/11/18 8:08 下午
 * 创 建 人：sunhao
 */
public class CmcException extends  RuntimeException{
    private static final long serialVersionUID = 2241624433172312130L;

    public CmcException(String msg){
        super(msg);
    }

}

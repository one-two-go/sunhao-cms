package com.sunhao.common;

import java.io.Serializable;

/**
 * 项目名称：sunhaocms
 * 类 名 称：MsgResult
 * 类 描 述：用于前后端交互的协议
 * 创建时间：2019/11/13 2:39 下午
 * 创 建 人：sunhao
 */
public class MsgResult implements Serializable {

    private static final long serialVersionUID = 1352419161934287162L;

    int result;
    String errorMsg;
    Object data;

    public MsgResult(int result, String errorMsg, Object data) {
        this.result = result;
        this.errorMsg = errorMsg;
        this.data = data;
    }
    public MsgResult(){

    }


    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

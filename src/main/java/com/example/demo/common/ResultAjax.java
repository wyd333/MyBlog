package com.example.demo.common;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:前后端交互的统一对象
 * User: 12569
 * Date: 2023-08-01
 * Time: 12:54
 */
@Data
public class ResultAjax {
    private int code;   //状态码
    private String msg;
    private Object data;
    public static ResultAjax succ(Object data) {
        ResultAjax result = new ResultAjax();
        result.setCode(200);
        result.setData(data);
        result.setMsg("");
        return result;
    }

    public static ResultAjax succ(int code, String msg, Object data) {
        ResultAjax result = new ResultAjax();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    public static ResultAjax fail(int code, String msg) {
        ResultAjax result = new ResultAjax();
        result.setCode(code);
        result.setData(null);
        result.setMsg(msg);
        return result;
    }
    public static ResultAjax fail(int code, String msg, Object data) {
        ResultAjax result = new ResultAjax();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

}

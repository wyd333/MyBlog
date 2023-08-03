package com.example.demo.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 * Description: 统一异常处理, 开发阶段为了方便查看错误信息建议不使用
 * User: 12569
 * Date: 2023-08-03
 * Time: 10:09
 */

//@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public ResultAjax doException(Exception e){
        return ResultAjax.fail(-1,e.getMessage());
    }
}

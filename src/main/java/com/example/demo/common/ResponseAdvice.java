package com.example.demo.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created with IntelliJ IDEA.
 * Description:保底统一返回值处理
 * User: 12569
 * Date: 2023-08-03
 * Time: 9:55
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof ResultAjax) {
            return body;
        }

        if(body instanceof String) {
            ResultAjax resultAjax = ResultAjax.succ(body);
            try {
                return objectMapper.writeValueAsString(resultAjax);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ResultAjax.succ(body);
    }
}

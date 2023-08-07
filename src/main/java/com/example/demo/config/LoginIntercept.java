package com.example.demo.config;

import com.example.demo.common.AppVar;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户拦截器
 * User: 12569
 * Date: 2023-08-07
 * Time: 9:34
 */

public class LoginIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session!=null && session.getAttribute(AppVar.SESSION_USERINFO_KEY)!=null){
            // 用户已经登录
            return true;
        }

        response.sendRedirect("/login.html");
        return false;   //return false则不会执行Controller
    }
}

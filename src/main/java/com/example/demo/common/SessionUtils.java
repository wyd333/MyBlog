package com.example.demo.common;

import com.example.demo.model.Userinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description: session工具类
 * User: 12569
 * Date: 2023-08-03
 * Time: 12:07
 */
public class SessionUtils {

    /**
     * 得到登录的用户
     * @param request
     * @return
     */
    public static Userinfo getUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session!=null &&
                session.getAttribute(AppVar.SESSION_USERINFO_KEY)!=null) {
            // 登录状态
            return (Userinfo) session.getAttribute(AppVar.SESSION_USERINFO_KEY);
        }
        return null;
    }
}

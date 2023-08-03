package com.example.demo.controller;

import com.example.demo.common.AppVar;
import com.example.demo.common.ResultAjax;
import com.example.demo.model.Userinfo;
import com.example.demo.model.vo.UserinfoVO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 12569
 * Date: 2023-08-01
 * Time: 11:54
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 注册功能
     * @param userinfo
     * @return
     */
    @RequestMapping("/reg")
    // 如果用参数接收，参数必须和前端data中的k相等
    // 如果用对象接收，前端data中传入的k必须与接收对象的属性名相等
    public ResultAjax reg(Userinfo userinfo) {
        //1-校验参数
        if(userinfo==null
                || !StringUtils.hasLength(userinfo.getUsername())
                || !StringUtils.hasLength(userinfo.getPassword())){
            // 参数异常
            return ResultAjax.fail(-1,"非法参数");
        }
        //2-请求 service 进行添加操作
        int result = userService.reg(userinfo);

        //3-将执行的结果返回前端
        return ResultAjax.succ(result);
    }

    /**
     * 登录功能
     * @param userinfoVO
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public ResultAjax login(UserinfoVO userinfoVO, HttpServletRequest request){
        // 1-参数校验
        if(userinfoVO == null || !StringUtils.hasLength(userinfoVO.getUsername())){
            // 非法登录
            return ResultAjax.fail(-1, "非法参数！");
        }

        // 2-根据用户名查找对象
        Userinfo userinfo = userService.getUserByName(userinfoVO.getUsername());
        if(userinfo==null || userinfo.getId()==0){
            // 不存在此用户
            return ResultAjax.fail(-2,"用户名或密码错误！");
        }

        // 3-将对象中的密码和用户输入的密码进行比较
        if(!userinfo.getPassword().equals(userinfoVO.getPassword())) {
            // 密码错误
            return ResultAjax.fail(-2,"用户名或密码错误！");
        }

        // 4-比较成功后，将对象存入session
        HttpSession session = request.getSession();
        session.setAttribute(AppVar.SESSION_USERINFO_KEY, userinfo);

        // 5-将结果返回给用户
        return ResultAjax.succ(1);  //前端通过比较data是否为1判断是否登录成功
    }


    @RequestMapping("/logout")
    public ResultAjax logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute(AppVar.SESSION_USERINFO_KEY) != null) {
            session.removeAttribute(AppVar.SESSION_USERINFO_KEY);
        }
        return ResultAjax.succ(1);
    }

}

package com.example.demo.controller;

import com.example.demo.common.ResultAjax;
import com.example.demo.model.Userinfo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

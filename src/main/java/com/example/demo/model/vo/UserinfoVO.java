package com.example.demo.model.vo;

import com.example.demo.model.Userinfo;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description: Userinfo扩展类
 * User: 12569
 * Date: 2023-08-03
 * Time: 10:39
 */

@Data
public class UserinfoVO extends Userinfo {
    private String checkCode;   //验证码
}

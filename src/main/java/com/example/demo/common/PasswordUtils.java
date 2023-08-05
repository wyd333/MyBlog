package com.example.demo.common;

import  org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description: 密码工具类
 * User: 12569
 * Date: 2023-08-05
 * Time: 22:26
 */
public class PasswordUtils {
    /**
     * 加盐算法加密
     * @param password
     * @return
     */
    public static String encrypt(String password){
        // 1-盐值
        String salt = UUID.randomUUID().toString().replace("-","");
        // 2-将盐值+密码进行 md5，得到最终密码
        String finalPassword = DigestUtils.md5DigestAsHex((salt+password).getBytes(StandardCharsets.UTF_8));
        // 3-返回 盐值+$+最终密码
        return salt+"$"+finalPassword;
    }

    /**
     * 加盐验证
     * @param password：待验证密码
     * @param dbPassword：数据库中的密码=盐值+$+最终密码
     * @return
     */

    public static boolean decrypt(String password, String dbPassword){
        if(!StringUtils.hasLength(password) || !StringUtils.hasLength(dbPassword) || dbPassword.length() != 65){
            // 合法性校验
            return false;
        }
        // 1-获取盐值
        String[] items =  dbPassword.split("\\$");  //$符是正则表达式的特殊字符，需要转义
        if(items.length != 2)   return false;
        // 盐值
        String salt = items[0];
        // 最终正确的密码
        String dbFinalPassword = items[1];

        // 2-加密待验证的密码
        String finalPassword = DigestUtils.md5DigestAsHex((salt+password).getBytes(StandardCharsets.UTF_8));
        // 3-对比
        return dbFinalPassword.equals(finalPassword);
    }
}

package com.example.demo.service;

import com.example.demo.dao.ArticleMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.Userinfo;
import com.example.demo.model.vo.UserinfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 12569
 * Date: 2023-08-01
 * Time: 11:58
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public int reg(Userinfo userinfo){
        return userMapper.reg(userinfo);
    }


    public Userinfo getUserByName(String username){
        return userMapper.getUserByName(username);
    }

    public UserinfoVO getUserById(int uid) {
        return userMapper.getUserById(uid);
    }
}

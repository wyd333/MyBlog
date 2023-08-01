package com.example.demo.dao;

import com.example.demo.model.Userinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 12569
 * Date: 2023-08-01
 * Time: 11:59
 */
public interface UserMapper {
    @Insert("insert into userinfo(username,password) values(#{username},#{password})")
    int reg(Userinfo userinfo);
}

package com.example.demo.dao;

import com.example.demo.model.Articleinfo;
import com.example.demo.model.Userinfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 12569
 * Date: 2023-08-01
 * Time: 12:01
 */
public interface ArticleMapper {
    @Select("select * from articleinfo where uid=#{uid} order by id desc")
    List<Articleinfo> getListByUid(@Param("uid")int uid);

    @Delete("delete from articleinfo where id=#{aid} and uid=#{uid}")
    int del(@Param("aid") Integer aid, Integer uid);
}

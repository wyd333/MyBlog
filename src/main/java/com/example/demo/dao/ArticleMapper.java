package com.example.demo.dao;

import com.example.demo.model.Articleinfo;
import com.example.demo.model.Userinfo;
import org.apache.ibatis.annotations.*;

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

    @Insert("insert into articleinfo(title,content,uid) values(#{title},#{content},#{uid})")
    int add(Articleinfo art);

    @Select("select * from articleinfo where id=#{aid} and uid=#{uid}")
    Articleinfo getDetailByIdandUid(@Param("aid") int aid,
                                    @Param("uid") int uid);

    @Update("update articleinfo set title=#{title},content=#{content} " +
            "where id=#{id} and uid=#{uid}")
    int update(Articleinfo articleinfo);

    @Select("select * from articleinfo where id=#{aid}")
    Articleinfo getDetailById(@Param("aid") int aid);

    @Select("select count(*) from articleinfo where uid=#{uid}")
    int getArtCountByUid(@Param("uid") int uid);

    @Update("update articleinfo set rcount=rcount+1 where id=#{aid}")
    int incrementRCount(@Param("aid") int aid);

    @Select("select * from articleinfo order by id desc limit #{psize} offset #{offset}")
    List<Articleinfo> getListByPage(@Param("psize") int psize, @Param("offset") int offset);

    @Select("select count(*) from articleinfo")
    int getArtCount();
}

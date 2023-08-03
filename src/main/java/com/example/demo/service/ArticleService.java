package com.example.demo.service;

import com.example.demo.dao.ArticleMapper;
import com.example.demo.model.Articleinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 12569
 * Date: 2023-08-01
 * Time: 11:59
 */

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    public List<Articleinfo> getListByUid(int uid){
        return articleMapper.getListByUid(uid);
    }

    public int del(Integer aid, Integer uid){
        return articleMapper.del(aid, uid);
    }
}

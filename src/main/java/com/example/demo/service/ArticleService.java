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

    public int add(Articleinfo art){
        return articleMapper.add(art);
    }

    public Articleinfo getDetailByIdandUid(int aid, int uid){
        return articleMapper.getDetailByIdandUid(aid, uid);
    }

    public int update(Articleinfo articleinfo) {
        return articleMapper.update(articleinfo);
    }
    public Articleinfo getDetailById(int aid) {
        return articleMapper.getDetailById(aid);
    }
    public int getArtCountByUid(int uid) {
        return articleMapper.getArtCountByUid(uid);
    }
    public int incrementRCount(int aid) {
        return articleMapper.incrementRCount(aid);
    }
    public List<Articleinfo> getListByPage(int psize, int offset){
        return articleMapper.getListByPage(psize, offset);
    }

    public int getArtCount(){
        return articleMapper.getArtCount();
    }
}


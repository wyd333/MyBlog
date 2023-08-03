package com.example.demo.controller;

import com.example.demo.common.ResultAjax;
import com.example.demo.common.SessionUtils;
import com.example.demo.model.Articleinfo;
import com.example.demo.model.Userinfo;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 12569
 * Date: 2023-08-01
 * Time: 11:57
 */

@RestController
@RequestMapping("/art")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    private static final int DESC_LENGTH = 120; //文章简介的长度

    /**
     * 得到当前登录用户的文章列表
     * @param request
     * @return
     */
    @RequestMapping("/mylist")
    public ResultAjax myList(HttpServletRequest request){
        // 1-从session中得到当前登录用户，校验参数
        Userinfo userinfo = SessionUtils.getUser(request);
        if(userinfo == null){
            return ResultAjax.fail(-1, "请先登录！");
        }
        // 2-根据用户id查询此用户发表的所有文章
        List<Articleinfo> articleinfoList = articleService.getListByUid(userinfo.getId());
            // 处理 articleinfoList，让文章正文变成简介
        if(articleinfoList != null && articleinfoList.size() != 0) {
            // 并行处理 articleinfoList 集合
            articleinfoList.stream().parallel().forEach((art)->{
                if(art.getContent().length() > DESC_LENGTH) {
                    art.setContent(art.getContent().substring(0,DESC_LENGTH));
                }
            });
        }
        // 3-返回给前端
        return ResultAjax.succ(articleinfoList);
    }

    /**
     * 删除
     * @param aid
     * @return
     */
    @RequestMapping("/del")
    public ResultAjax del(Integer aid, HttpServletRequest request){
        // 1-参数校验
        if(aid == null || aid <= 0) {
            return ResultAjax.fail(-1, "参数有误！");
        }
        // 2-得到当前登录的用户
        Userinfo userinfo = SessionUtils.getUser(request);
        if(userinfo == null){
            return ResultAjax.fail(-2,"请先登录！！");
        }

        // 3-判断文章的归属人+删除where aid=id and uid=uid
        int ret = articleService.del(aid, userinfo.getId());

        // 4-将结果返回给前端
        return ResultAjax.succ(ret);
    }
}

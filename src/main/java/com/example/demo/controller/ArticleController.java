package com.example.demo.controller;

import com.example.demo.common.ResultAjax;
import com.example.demo.common.SessionUtils;
import com.example.demo.model.Articleinfo;
import com.example.demo.model.Userinfo;
import com.example.demo.model.vo.UserinfoVO;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

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

    @RequestMapping("/add")
    public ResultAjax add(Articleinfo articleinfo,HttpServletRequest request) {
        // 1-校验参数
        if(articleinfo == null || !StringUtils.hasLength(articleinfo.getTitle())||
        !StringUtils.hasLength(articleinfo.getContent())){
            return ResultAjax.fail(-1, "非法参数！");
        }

        // 2-组装数据
        // 获得当前用户
        Userinfo userinfo = SessionUtils.getUser(request);
        if(userinfo==null) {
            return ResultAjax.fail(-2,"请先登录！");
        }
        articleinfo.setUid(userinfo.getId());

        // 3-数据入库
        int result = articleService.add(articleinfo);

        // 4-将结果返回给前端
        return ResultAjax.succ(result);
    }

    /**
     * 查询自己的文章详情
     * @param aid
     * @return
     */
    @RequestMapping("/update_init")
    public ResultAjax updateInit(Integer aid, HttpServletRequest request){
        // 1-参数校验
        if(aid == 0 || aid <= 0) {
            return ResultAjax.fail(-1,"非法参数！");
        }

        // 2-获取当前登录用户的id
        Userinfo userinfo = SessionUtils.getUser(request);
        if(userinfo == null) {
            return ResultAjax.fail(-2,"请先登录！");
        }

        // 2-查询文章
        Articleinfo articleinfo = articleService.getDetailByIdandUid(aid,userinfo.getId());
        if(articleinfo == null) {
            return ResultAjax.fail(-2,"查询失败！");
        }

        // 3-将结果返回给前端
        return ResultAjax.succ(articleinfo);
    }

    @RequestMapping("/update")
    public ResultAjax update(Articleinfo articleinfo,HttpServletRequest request) {
        // 1-参数校验
        if(articleinfo == null
                || !StringUtils.hasLength(articleinfo.getTitle())
                || !StringUtils.hasLength(articleinfo.getContent())
                || articleinfo.getId() == 0){
            return ResultAjax.fail(-1,"非法参数！");
        }

        // 2-获取当前登录用户的id
        Userinfo userinfo = SessionUtils.getUser(request);
        if(userinfo == null) {
            return ResultAjax.fail(-2,"请先登录！");
        }
        articleinfo.setUid(userinfo.getId());

        // 3-修改数据库中的文章信息，并验证归属人
        int ret = articleService.update(articleinfo);

        // 4-将结果返回给前端
        return ResultAjax.succ(ret);
    }

    @RequestMapping("/detail")
    public ResultAjax detail(Integer aid) throws ExecutionException, InterruptedException {
        // 1-参数校验
        if(aid == null || aid <= 0) {
            return ResultAjax.fail(-1,"非法参数！");
        }

        // 2-查询文章详情
        Articleinfo articleinfo = articleService.getDetailById(aid);
        if(articleinfo == null || articleinfo.getId() <= 0) {
            return ResultAjax.fail(-1,"非法参数！");
        }

        // 3-根据uid查询用户详情
        FutureTask<UserinfoVO> userTask = new FutureTask<>(()->{
            // todo: 调用 service
            return null;
        });
        taskExecutor.submit(userTask);

        // 4-根据uid查询用户发表的总文章数
        FutureTask<Integer> artCountTask = new FutureTask<>(()->{
            // todo: 调用 service
            return 0;
        });
        taskExecutor.submit(artCountTask);
        // 5-组装数据
        UserinfoVO userinfoVO = userTask.get(); // 等待任务（线程池）执行完成
        int artCount = artCountTask.get();  // 等待任务（线程池）执行完成
        userinfoVO.setArtCount(artCount);
        HashMap<String, Object> result = new HashMap<>();
        result.put("user",userinfoVO);
        result.put("art",articleinfo);
        // 6-返回结果给前端
        return ResultAjax.succ(result);
    }

}

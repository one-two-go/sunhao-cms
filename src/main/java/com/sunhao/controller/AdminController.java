package com.sunhao.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sunhao.common.CmsAssert;
import com.sunhao.common.MsgResult;
import com.sunhao.entity.Article;
import com.sunhao.entity.User;
import com.sunhao.service.ArticleService;
import com.sunhao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目名称：sunhaocms
 * 类 名 称：AdminController
 * 类 描 述：TODO
 * 创建时间：2019/11/12 7:26 下午
 * 创 建 人：sunhao
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    /**
     *
     */
    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @RequestMapping("index")
    public String index(){
        return "admin/index";
    }


    @RequestMapping("users")
    public String users(HttpServletRequest request,
                        @RequestParam(defaultValue = "")String name,
                        @RequestParam(defaultValue = "1")Integer page){
          PageInfo<User> info =  userService.getUserList(name,page);
          request.setAttribute("info",info);
          request.setAttribute("name",name);

        return "admin/users/list";
    }

    @RequestMapping("lockerUser")
    @ResponseBody
    public MsgResult lock(Integer userId, int status){
        if (status!=0 && status!= 1){
            return new MsgResult(2,"参数无效",null);
        }
        User user =  userService.findUserById(userId);

        if (user==null){
            return new MsgResult(2,"抱歉，该用户不存在！！",null);
        }
        if (user.getLocked()==status){
            return new MsgResult(2,"你无需此操作！！",null);
        }
        int result =  userService.updateStatus(userId,status);
        System.out.println(result+"++++++++++++++");
        if(result>0){
            return new MsgResult(1,"恭喜你，操作成功！",null);
        }else {
            return new MsgResult(2,"sorry，操作失败！请与管理员联系。。。",null);
        }

    }

    @RequestMapping("articles")
    public String articles(HttpServletRequest request,
             @RequestParam(defaultValue = "1") Integer page,
             @RequestParam(defaultValue = "-1") int status){

        PageInfo<Article> pageInfo = articleService.getPageList(page,status);

        request.setAttribute("pageInfo",pageInfo);
        request.setAttribute("status",status);
        return   "admin/articles/list" ;
    }


    @RequestMapping("getArticle")
    @ResponseBody
    public MsgResult getArticle(Integer id){
        Article article = articleService.getDetailById(id);
        CmsAssert.AssertTrue(article!=null,"文章不存在！！！");

        return new MsgResult(1,"获取成功",article);
    }

    @RequestMapping("applyArticle")
    @ResponseBody
    public MsgResult applyArticle(int id,int status){
        Article article = articleService.checkExist(id);
        CmsAssert.AssertTrue(article!=null,"文章已经不存在！！");
        int result = articleService.apply(id,status);
        if(result>0 ){
            return new MsgResult(1,"处理成功",null);
        }else {
            return new MsgResult(2,"处理失败",null);
        }

    }
    @RequestMapping("setArticleHot")
    @ResponseBody
    public MsgResult setHot(int id,int status){
       Article article =  articleService.checkExist(id);
       CmsAssert.AssertTrue(article!=null,"文章不存在！！");
       int result =  articleService.setHot(id,status);
        System.out.println(result+"++++++");
       if(result>0){
           return  new MsgResult(1,"操作成功！！",null);
       }else {
           return  new MsgResult(2,"操作失败！！",null);
       }

    }






}

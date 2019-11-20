package com.sunhao.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sunhao.common.CmsAssert;
import com.sunhao.common.ConstantClass;
import com.sunhao.common.MsgResult;
import com.sunhao.entity.Article;
import com.sunhao.entity.User;
import com.sunhao.service.ArticleService;
import com.sunhao.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目名称：sunhaocms
 * 类 名 称：UserCOntroller
 * 类 描 述：TODO
 * 创建时间：2019/11/12 11:30 上午
 * 创 建 人：sunhao
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    //测试使用
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String ConTest(HttpServletRequest request) {

        request.setAttribute("info", "hello");
        return "user/test";
    }

    /**
     * 跳转到注册页面
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(HttpServletRequest request) {
        return "user/register";
    }

    /**
     * 注册用户
     *
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(HttpServletRequest request, User user) {

        int i = userService.register(user);
        return "redirect:/user/login";
    }

    /**
     * 跳转到登陆页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        return "user/login";
    }

    /**
     * 用户登陆
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, User user) {

        //获取用户名
        User loginUser = userService.login(user);
        //
        if (loginUser != null) {
            request.getSession().setAttribute(ConstantClass.USER_KEY, loginUser);

            return loginUser.getRole() == ConstantClass.USER_ROLE_ADMIN
                    ? "redirect:/admin/index" : "redirect:/user/home";
//            return "redirect:/";
        } else {
            request.setAttribute("errorMsg", "用户名或者密码有错");
            request.setAttribute("user", user);
            return "user/login";
        }

    }

    /**
     * 进入home页面
     */
    @RequestMapping("home")
    public String myhome(HttpServletRequest request) {
        return "/user/home";
    }

    @RequestMapping("myarticles")
    public String myarticles(HttpServletRequest request, @RequestParam(defaultValue = "1") int page) {

        User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
        PageInfo<Article> pageInfo = articleService.listByUser(page, loginUser.getId());
        request.setAttribute("pageInfo", pageInfo);
        return "user/myarticles";
    }


    @RequestMapping("delArticle")
    @ResponseBody
    public MsgResult delArticle(HttpServletRequest request, Integer id) {
        CmsAssert.AssertTrue(id > 0, "文章ID必须大于0");
        Article article = articleService.checkExist(id);
        CmsAssert.AssertTrue(article != null, "该文章已经不存在");

        //获取当前用户的session key
        User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
//        System.out.println("+++++++"+loginUser.getId()+"============");
//        System.out.println("+++++++"+loginUser+"============");
//
//        System.out.println("+++++++"+ article +"++++++=====");
//        +++++++57============
//        +++++++User{id=57, username='wucaicai', password='41c810756567d3ef6c6fd2ebf8caf4f9', nickname='null', birthday=null, gender=1, locked=0, createTime=null, updateTime=null, url='', score=0, role=0}============
//        +++++++Article{id=29, title='v', content='&nbsp;飞', picture='a29bde7b-6da0-473f-86ab-567950f9888d.jpg', channelId=null, channel=null, categoryId='null', category=null, userId=null, user=null, hits=0, hot=0, status=1, deleted=0, created='Sun Sep 22 21:00:46 CST 2019', updated=Mon Sep 23 14:10:00 CST 2019, commentCnt=1, articleType=0}++++++=====
////        CmsAssert.AssertTrue(
//                loginUser.getRole() == ConstantClass.USER_ROLE_ADMIN
//                        || loginUser.getId() == article.getId()
//                , "只有管理员和文章的作者能删除文章");

        int result = articleService.delete(id);
        CmsAssert.AssertTrue(result > 0, "文章删除失败");

        return new MsgResult(1, "删除成功", null);
    }

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    @RequestMapping("checkname")
    @ResponseBody
    public boolean checkname(String username) {
        return null == userService.findUserByName(username);
    }


}
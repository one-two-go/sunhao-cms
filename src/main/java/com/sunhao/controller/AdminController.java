package com.sunhao.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sunhao.common.MsgResult;
import com.sunhao.entity.User;
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

    @RequestMapping("index")
    public String index(){
        return "admin/index";
    }

    @RequestMapping("articles")
    public String articles(){
        return "admin/articles/list";
    }

    @RequestMapping("users")
    public String users(HttpServletRequest request,
                        @RequestParam(defaultValue = "")String name,
                        @RequestParam(defaultValue = "1")Integer page){
          PageInfo<User> info =  userService.getUserList(name,page);
          request.setAttribute("info",info);

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


}

package com.sunhao.controller;

import com.github.pagehelper.PageInfo;
import com.sunhao.common.MsgResult;
import com.sunhao.entity.Link;
import com.sunhao.service.LinkService;
import com.sunhao.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 项目名称：sunhaocms
 * 类 名 称：LinkController
 * 类 描 述：TODO
 * 创建时间：2019/11/24 9:02 下午
 * 创 建 人：sunhao
 */
@Controller
@RequestMapping("link")
public class LinkController {
    @Autowired
    LinkService linkService;

    /**
     * 获取链接的list
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("list")
    public String getList(HttpServletRequest request, @RequestParam(defaultValue = "1") int page) {

        PageInfo<Link> info = linkService.getList(page);
        request.setAttribute("info", info);
        return "/admin/link/list";
    }

    @GetMapping("add")
    public String add(HttpServletRequest request) {

        request.setAttribute("link",new Link());
        return "admin/link/add";
    }

    @PostMapping("add")
    public  String add(HttpServletRequest request,@Valid @ModelAttribute("link") Link link,
                       BindingResult result){
        //判断是否是合法的URL
        System.out.println(link.getUrl()+"========");
        if(!StringUtils.isHttpUrl(link.getUrl())) {
            result.rejectValue("url", "不是合法的url", "不是合法的url");
        }

        //有错误还在原来的页面
        if (result.hasErrors()){
            request.setAttribute("link",link);
            return  "admin/link/add";
        }
        linkService.add(link);

        return "redirect:list";
    }



    //删除链接
    @RequestMapping("delLink")
    @ResponseBody
    public MsgResult delLink(int id) {

        int result = linkService.delLink(id);
        if (result > 0) {
            return new MsgResult(1, "删除成功！！", null);
        } else {
            return new MsgResult(0, "删除失败！！", null);
        }

    }

    @GetMapping("update")
    public String update(int id,HttpServletRequest request){
        request.setAttribute("link",linkService.getListById(id));
        return "admin/link/update";
    }

    @PostMapping("updateLink")
    public String updateLink(HttpServletRequest request,@Valid @ModelAttribute("link") Link link,
                             BindingResult result){

        //验证是否是合法的Url  验证未成功
        if(!StringUtils.isHttpUrl(link.getUrl())){
            result.rejectValue("url","不是合法的url","不是合法的url");
        }

        if(result.hasErrors()){
            return "admin/link/update";
        }
        linkService.update(link);
        return "redirect:list";
    }





}
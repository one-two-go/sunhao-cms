package com.sunhao.controller;

import com.github.pagehelper.PageInfo;
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
        if(!StringUtils.isHttpUrl(link.getUrl())){
            result.rejectValue("url","不是一个合法的Url","不是一个合法的URL+++");
        }
        if (result.hasErrors()){
            request.setAttribute("link",link);
            return  "admin/link/add";
        }
        linkService.add(link);

        return "redirect:list";
    }



}
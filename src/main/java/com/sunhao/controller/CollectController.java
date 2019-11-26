package com.sunhao.controller;

import com.github.pagehelper.PageInfo;
import com.sunhao.common.MsgResult;
import com.sunhao.entity.Collect;
import com.sunhao.service.CollectService;
import com.sunhao.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 项目名称：sunhaocms
 * 类 名 称：CollectController
 * 类 描 述：TODO
 * 创建时间：2019/11/25 6:38 下午
 * 创 建 人：sunhao
 */
@Controller
@RequestMapping("collect")
public class CollectController {

    @Autowired
    CollectService collectService;

    /**
     * 展示收藏夹的内容
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("list")
    public String collectList(HttpServletRequest request, @RequestParam(defaultValue = "1") int page){

        PageInfo<Collect> pageInfo = collectService.getList(page);
        request.setAttribute("pageInfo",pageInfo);
        return  "user/collect/list";
    }

    /**
     * 删除收藏夹的收藏
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("delCollect")
    @ResponseBody
    public MsgResult delCollect(HttpServletRequest request,int id ){
        int result = collectService.delCollect(id);
        if (result>0){
            return new MsgResult(1,"删除成功了呢",null);
        }else {
            return new MsgResult(2,"删除失败了呢",null);

        }
    }

    //修改收藏夹 跳转页面
    @GetMapping("updateTo")
    public  String updateTo(int id,HttpServletRequest request){

        request.setAttribute("collect",collectService.getListById(id));
        System.out.println(collectService.getListById(id));
        return "user/collect/update";
    }

    @PostMapping("update")
    public String update(HttpServletRequest request,
                         @Valid  @ModelAttribute("collect") Collect collect,
                         BindingResult result
    ) {
        if(!StringUtils.isHttpUrl(collect.getUrl())) {
            result.rejectValue("url", "不是合法的url", "不是合法的url");
        }
        System.out.println(collect+"===");
        // 有错误 还在原来的页面
        if(result.hasErrors()) {
            return "user/collect/update";
        }
        collectService.updateCollect(collect);

        // 没有错误跳转到列表页面
        return "redirect:list";
    }




}






package com.sunhao.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sunhao.common.CmsAssert;
import com.sunhao.common.ConstantClass;
import com.sunhao.common.MsgResult;
import com.sunhao.dao.ElasticDao;
import com.sunhao.entity.*;
import com.sunhao.service.*;
import javafx.scene.input.DataFormat;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.NumberUp;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import javax.validation.constraints.AssertTrue;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.SimpleFormatter;

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

    @Value("${upload.path}")
    String uploadPath;

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    ChannelService channelService;

    @Autowired
    CollectService collectService;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    ElasticDao elasticDao;

    //测试使用
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String ConTest(HttpServletRequest request) {

        request.setAttribute("info", "hello");
        return "user/test";

    }

    @RequestMapping("comment")
    @ResponseBody
    public MsgResult comment(HttpServletRequest request, Integer id, String content) {
        User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
        CmsAssert.AssertTrue(loginUser != null, "请你先登陆账号");

        int result = articleService.addComment(loginUser.getId(), id, content);
        if (result > 0) {
            return new MsgResult(1, "评论成功", null);
        } else {
            return new MsgResult(0, "评论失败", null);
        }
    }

    /**
     * 文章后面评论展示
     *
     * @param request
     * @param page
     * @param articleId
     * @return
     */
    @RequestMapping("commentList")
    public String commentList(HttpServletRequest request, @RequestParam(defaultValue = "1") int page, Integer articleId) {

        PageInfo<Comment> pageInfo = articleService.getCommentList(page, articleId);
        request.setAttribute("pageInfo", pageInfo);

        return "article/comments";
    }

    /**
     * 获取全部评论
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("userCommentList")
    public String userCommentList(HttpServletRequest request, @RequestParam(defaultValue = "1") int page) {

        //获取全部全部评论，未按照登陆人条件查询
        // User loginUser = (User)request.getSession().getAttribute(ConstantClass.USER_KEY);

        PageInfo<Comment> pageInfo = articleService.getComList(page);
        request.setAttribute("pageInfo", pageInfo);

        return "user/commentList";
    }


    /**
     * 添加 点击收藏
     */
    @RequestMapping("favorite")
    @ResponseBody
    public MsgResult favorite(HttpServletRequest request, Integer id) {
        //判断传入的id和用户是否可以
        CmsAssert.AssertTrue(id > 0, "文章ID不符合呢");
        User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
        CmsAssert.AssertTrue(loginUser != null, "请您先登陆呢");
        int result = articleService.favorite(loginUser.getId(), id);
        CmsAssert.AssertTrue(result > 0, "收藏失败了");
        return new MsgResult(1, "恭喜，收藏成功了", null);
    }

    @RequestMapping("collect")
    @ResponseBody
    public MsgResult collect(HttpServletRequest request, Collect collect) {

        //CmsAssert.AssertTrue(id>0, "id 不合法");
        User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
        CmsAssert.AssertTrue(loginUser != null, "亲 啊,你还没有登陆呢！！");

        if (collect.getName().length() > 20) {
            collect.setName(collect.getName().substring(0, 20) + "....");
        }
        collect.setUserId(loginUser.getId());
        int result = collectService.addcollect(collect);
        CmsAssert.AssertTrue(result > 0, "收藏失败了呢！！");

        return new MsgResult(1, "收藏成功了呢", null);

    }


    /**
     * 发布图片
     */
    @GetMapping("postImg")
    public String postImg(HttpServletRequest request) {
        //获取所有频道
        List<Channel> channels = channelService.getChannelList();
        request.setAttribute("channels", channels);

        return "/article/postImg";
    }

    @PostMapping("postImg")
    public MsgResult postImg(HttpServletRequest request, MultipartFile file[],
                             String desc[], Article article) throws IOException {

        //  获取用户的信息
        User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);

        List<Image> list = new ArrayList<>();
        //便利处理每个上传的图片，存入list中
        for (int i = 0; i < file.length && i < desc.length; i++) {
            String url = processFile(file[i]);
            Image image = new Image();
            image.setDesc(desc[i]);
            image.setUrl(url);
            list.add(image);
        }
        //GSON来操作java对象和json数据之间的相互转换
        Gson gson = new Gson();

        //设置作者
        article.setUserId(loginUser.getId());
        article.setContent(gson.toJson(list));
        //设置文章类型 是图片
        article.setArticleType(TypeEnum.IMG);
        int add = articleService.add(article);
        if (add > 0) {
            return new MsgResult(1, "恭喜呢，发布成功", null);
        } else {
            return new MsgResult(2, "不好意思，发布失败了", null);
        }
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

    //展示我的文章
    @RequestMapping("myarticles")
    public String myarticles(HttpServletRequest request, @RequestParam(defaultValue = "1") int page) {

        User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
        PageInfo<Article> pageInfo = articleService.listByUser(page, loginUser.getId());
        request.setAttribute("pageInfo", pageInfo);
        return "user/myarticles";
    }

    /**
     * 删除用户的文章
     *
     * @param request
     * @param id
     * @return
     */

    @RequestMapping("delArticle")
    @ResponseBody
    public MsgResult delArticle(HttpServletRequest request, Integer id) {
        CmsAssert.AssertTrue(id > 0, "文章ID必须大于0");
        Article article = articleService.checkExist(id);
        CmsAssert.AssertTrue(article != null, "该文章已经不存在");
        //获取当前用户的session key
        User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
//        System.out.println("+++++++" + loginUser.getId() + "============");
//        System.out.println("+++++++" + loginUser + "============");
//
//        System.out.println("+++++++" + article + "++++++=====");


        int result = articleService.delete(id);
        String jsonString = JSON.toJSONString(article);

        kafkaTemplate.send("hot_articles", "del=" + jsonString);
        elasticDao.deleteById(id);
        System.out.println("es删除成功！！"+id);
        CmsAssert.AssertTrue(result > 0, "文章删除失败");

        return new MsgResult(1, "删除成功", null);
    }

    /**
     * updateArticle
     * 修改用户的文章
     */
    @RequestMapping(value = "updateArticle", method = RequestMethod.GET)
    public String updateArticle(HttpServletRequest request, int id) {
        //获取文章详情，用于回显  不考虑状态，只判断是否删除
        Article article = articleService.getDetailById(id);
        request.setAttribute("article", article);

        request.setAttribute("content1", htmlspecialchars(article.getContent()));

        //获取所用的频道信息
        List<Channel> channels = channelService.getChannelList();
        System.out.println(channels);
        request.setAttribute("channels", channels);

        return "article/update";
    }

    @RequestMapping(value = "updateArticle", method = RequestMethod.POST)
    @ResponseBody
    public MsgResult updateArticle(HttpServletRequest request, MultipartFile file, Article article) throws IOException {
        //  1。先判断文章id是否存在 2。当前用户是否有权限修改这个文章

        //判断图片 ，替换图片新的
        if (!file.isEmpty()) {
            String picUrl = processFile(file);
            article.setPicture(picUrl);
        }

        int result = articleService.update(article);
        String jsonString = JSON.toJSONString(article);
        kafkaTemplate.send("hot_articles", "update=" + jsonString);
            elasticDao.save(article);
            System.err.println("es修改成功");
        if (result > 0) {
            return new MsgResult(1, "", null);
        } else {
            return new MsgResult(2, "", null);
        }


    }


    /**
     * 发布我的文章
     * GetMapping==@RequestMapping(method = RequestMethod.GET)
     */

    @GetMapping("postArticle")
    public String postArticle(HttpServletRequest request) {

        //获取所有频道
        List<Channel> channels = channelService.getChannelList();
        request.setAttribute("channels", channels);

        return "article/publish";
    }

    @PostMapping("postArticle")
    @ResponseBody
    public MsgResult postArticle(HttpServletRequest request, MultipartFile file, Article article) throws IOException {
        if (!file.isEmpty()) {
            String fileUrl = processFile(file);
            article.setPicture(fileUrl);
        }
        User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);

        article.setUserId(loginUser.getId());
        int result = articleService.add(article);
        String jsonString = JSON.toJSONString(article);
        kafkaTemplate.send("hot_articles", "add=" + jsonString);
        elasticDao.save(article);
        System.out.println("es添加成功！！！"+article.getId());
        if (result > 0) {
            return new MsgResult(1, "文章发布成功！！！", null);
        } else {
            return new MsgResult(2, "文章发布失败！！！", null);
        }

    }

    /**
     * 保存文件的相对路径
     *
     * @param file
     * @return
     */
    private String processFile(MultipartFile file) throws IOException {
        // 求文件扩展名 xxx.jsp
        String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        String fileNamePre = UUID.randomUUID().toString();
        // 计算出新的文件名称

        String fileName = fileNamePre + suffixName;

        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMdd");
        String path = dataFormat.format(new Date());

        File pathFile = new File(uploadPath + "/" + path);

        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        //最终的 新的 文件名称
        String newFileName = uploadPath + "/" + path + fileName;
        file.transferTo(new File(newFileName));

        return path + "/" + fileName;

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

    //退出登陆
    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(ConstantClass.USER_KEY);
        return "redirect:/";
    }

    //文章内容转换
    private String htmlspecialchars(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        return str;
    }

}
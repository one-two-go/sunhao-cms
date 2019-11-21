<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/resource/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
    <title>有花堪折直须折</title>
    <style type="text/css">
        .menu li {
            font-size: 26px;
            text-align: center;
        }

        .menu li:hover {
            background: gray;
        }


    </style>

</head>

<body>
<!-- 导航条 -->
<!-- 导航条 -->
<nav class="navbar navbar-default" style="background:#000099">
    <%@include  file="common/top.jsp" %>
</nav>

<div class="container-fluid">
    <div class="container" style="minheight:200px">
        <div class="row">
            <!-- 左侧菜单 -->
            <div class="col-md-2" style="minheight:200px;margin-top:20px">

                <ul class="list-group menu">
                    <li class="list-group-item active">热门文章</li>
                    <c:forEach items="${channelList}" var="channel" varStatus="index">
                        <li class="list-group-item" data="/channel?chnId=${channel.id}" >${channel.name}</li>
                    </c:forEach>
                </ul>
            </div>

            <!-- 中间的内容 -->
            <div class="col-md-8" style="background:white;minheight:200px">
                <div>
                    <hr>

                </div>

                <div id="myCarousel" class="carousel slide" style="minheight:200px">
                    <!-- 轮播（Carousel）指标 -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ol>
                    <!-- 轮播（Carousel）项目 -->
                    <div class="carousel-inner">
                        <div class="item active">
                            <img height="300px" src="/resource/images/donghua.gif" alt="First slide">
                        </div>
                        <div class="item">
                            <img height="300px" src="/resource/images/avatar1.jpg" alt="Second slide">
                        </div>
                        <div class="item">
                            <img height="300px" src="/resource/images/bg05.jpg" alt="Third slide">
                        </div>
                    </div>
                    <!-- 轮播（Carousel）导航 -->
                    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
                <!-- 放文章的列表 -->
                <div>
                    <c:forEach items="${hotList.list}" var="hl">
                        <div class=row>
                            <hr align="left" style="width:100%">
                            <div class="col-md-2"><img height="50px" width="80px" src="/resource/images/avatar1.jpg"></div>
                            <div class="col-md-10">
                                <a href="javascript:showArticle(${hl.id})">${hl.title}</a>
                                <br>
                                频道：<a>${hl.channel.name}</a>&nbsp;&nbsp;
                                分类: <a>${hl.category.name}</a>
                                <br>
                                <!--内容：<a>${hl.content}</a> -->
                                <br/>
                                <br>
                                ${hl.user.username}&nbsp;发布于 <fmt:formatDate value="${hl.created}" pattern="yyyy-MM-dd"></fmt:formatDate>
                            </div>
                        </div>

                    </c:forEach>
                    <div class="row">
                        <ul class="pagination">
                            <li><a href="/index?page=${hotList.prePage}">&laquo;</a></li>
                            <c:forEach begin="${hotList.pageNum-2 > 1 ? hotList.pageNum-2:1}" end="${hotList.pageNum+2 > hotList.pages ? hotList.pages:hotList.pageNum+2}" varStatus="index">
                                <c:if test="${hotList.pageNum!=index.index}">
                                    <li><a href="/index?page=${index.index}">${index.index}</a></li>
                                </c:if>
                                <c:if test="${hotList.pageNum==index.index}">
                                    <li><a href="javascript:"> <strong> ${index.index} </strong> </a></li>
                                </c:if>

                            </c:forEach>
                            <li><a href="/index?page=${hotList.nextPage}">&raquo;</a></li>
                        </ul>
                    </div>



                </div>

            </div>
            <!-- 中间的内容结束 -->


            <div class="col-md-2" style="minheight:200px">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">面板标题</h3>
                    </div>
                    <div class="panel-body">
                        这是一个基本的面板
                    </div>
                </div>

                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">最新文章</h3>
                    </div>
                    <div class="panel-body">
                        <c:forEach items="${newArticle}" var="article" varStatus="index">
                            ${index.index+1}   <a>${article.title}</a>
                            <br/>
                        </c:forEach>
                    </div>
                </div>

            </div>
        </div>

    </div>

</div>


<!-- 底部 -->
<nav class="navbar navbar-default">
    <div class="container-fluid">
    </div>
</nav>

<script type="text/javascript">
    function showArticle(articleId) {
        window.open("/article/detail?id="+articleId);
    }
</script>

</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
    function updateStatus(userId,status) {
        $.post("/admin/lockerUser",
            {userId:userId,status:status},
        function (data) {
            if (data.result==1){
                alert("恭喜 操作成功！！！！");
                $("#content").load("/admin/users?name=${name}&page="+${info.pageNum});
            }else {
                alert("sorry,操作失败了呦！！");
                alert(data.errorMsg)
            }
        },
        )
        
    }
    
</script>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">输入姓名</a>
        </div>
        <div>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">查询</button>
            </form>
        </div>
    </div>
</nav>

<div class="table-responsive">
    <table class="table">
        <caption>响应式表格布局</caption>
        <thead>
        <tr>
            <th>用户id</th>
            <th>用户名称</th>
            <th>注册日期</th>
            <th>生日</th>
            <th>角色</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>

    <tbody>
        <c:forEach items="${info.list}" var="l">
            <c:if test="${l.locked==1}">
                <tr class="active">
            </c:if>
            <c:if test="${l.locked!=1}">
                <tr >
            </c:if>
                <td>${l.id}</td>
                <td>${l.username}</td>
                <td><fmt:formatDate value="${l.createTime}" pattern="yyyy年mm月dd日"></fmt:formatDate></td>
                <td><fmt:formatDate value="${l.birthday}" pattern="yyyy-mm-dd"></fmt:formatDate></td>
                <td>
                    <c:choose>
                    <c:when test="${l.role==1}">
                    管理员
                    </c:when>
                    <c:when test="${l.role==0}">
                    注册用户
                    </c:when>
                    <c:otherwise>
                    未知用户
                    </c:otherwise>
                    </c:choose>
                </td>

                <td>${l.locked==1?"禁用":"正常"}</td>
                <td>
                    <c:if test="${l.locked==1}">
                        <input type="button" class="btn btn-success" onclick="updateStatus(${l.id},0)" value="解封">
                    </c:if>
                    <c:if test="${l.locked==0}">
                        <input type="button" class="btn badge-info" onclick="updateStatus(${l.id},1)" value="封印">
                    </c:if>
                </td>


                <td></td>
            </tr>
        </c:forEach>


        </tbody>
    </table>
</div>
<ul class="pagination">
    <li><a href="javascript:goPage(${info.prePage})">&laquo;</a></li>
    <c:forEach begin="${info.pageNum-2 > 1 ? info.pageNum-2:1}" end="${info.pageNum+2 > info.pages ? info.pages:info.pageNum+2}" varStatus="index">
        <c:if test="${info.pageNum!=index.index}">
            <li><a href="javascript:goPage(${index.index})">${index.index}</a></li>
        </c:if>
        <c:if test="${info.pageNum==index.index}">
            <li><a href="javascript:void"><strong> ${index.index} </strong> </a></li>
        </c:if>

    </c:forEach>
    <li><a href="javascript:goPage(${info.nextPage})">&raquo;</a></li>
</ul>

<script type="text/javascript">
    function goPage(page) {
        var url="/admin/users?page="+page+"&name=${name}";
        $("#content").load(url);
    }

</script>


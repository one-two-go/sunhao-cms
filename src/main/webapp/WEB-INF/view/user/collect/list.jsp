<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table class="table">
    <caption>我的文章</caption>
    <thead>
    <tr>
        <th>用户ID</th>
        <th>收藏路径</th>
        <th>文章标题</th>
        <th>评论时间</th>
        <th>操作
         <a href="/collect/addCollect" >增加</a>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pageInfo.list}" var="collect">
        <tr class="active">
            <td>${collect.userId}</td>
            <td>${collect.url}</td>
            <td>${collect.name}</td>
            <td><fmt:formatDate value="${collect.created}" pattern="yyyy-MM-dd"/></td>

            <td>
                <input type="button" onclick="updateCollect(${collect.id})" value="修改" class="btn-info"/>
                <input type="button" onclick="delCollect(${collect.id})" value="删除"  class="btn-danger"/>
            </td></tr>
    </c:forEach>
    </tbody>
</table>
<ul class="pagination">
    <li><a href="javascript:goPage(${pageInfo.prePage})">&laquo;</a></li>
    <c:forEach begin="${pageInfo.pageNum-2 > 1 ? pageInfo.pageNum-2:1}" end="${pageInfo.pageNum+2 > pageInfo.pages ? pageInfo.pages:pageInfo.pageNum+2}" varStatus="index">
        <c:if test="${pageInfo.pageNum!=index.index}">
            <li><a href="javascript:goPage(${index.index})">${index.index}</a></li>
        </c:if>
        <c:if test="${pageInfo.pageNum==index.index}">
            <li><a href="javascript:void"><strong> ${index.index} </strong> </a></li>
        </c:if>

    </c:forEach>
    <li><a href="javascript:goPage(${pageInfo.nextPage})">&raquo;</a></li>
</ul>

<script type="text/javascript">

    function goPage(page){
        var url="/collect/list?page="+page ;
        $("#content").load(url);
    }
    //刷新当前页面
    function refresh() {
        var url="/link/list?page={pageInfo.pageNum}";
        $("#content").load(url);
    }


    //删除收藏夹的内容
    function delCollect(id) {
        $.get("/collect/delCollect",{id:id},function (msg) {
            if(msg.result==1){
                alert("删除成功！！");
                refresh();
            }else {
                alert(msg.errorMessage);
            }  })
    }

    function updateCollect(id) {
        $("#content").load("/collect/updateTo?id="+id);
    }


</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach items="${pageInfo.list}" var="pl">
    <div class="row">
        ${pl.content}
        <br>
        ${pl.userId}
        <fmt:formatDate value="${pl.created}" pattern="yyyy-MM-dd HH:mm:ss"/>

    </div>
</c:forEach>
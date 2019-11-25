<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form modelAttribute="link" id="form">
    <form:input path="name"/><form:errors path="name"/>
    <form:input path="url"/><form:errors path="url"/>
    <form:hidden path="id"/>
    <input type="button" value="提交" onclick="updateLink()">

</form:form>

<script type="text/javascript">
    function updateLink() {
        $.post('/link/updateLink',$("#form").serialize(),
            function (msg) {
                $("#content").html(html);
            alert("修改成功！！！")
        });
    }

</script>
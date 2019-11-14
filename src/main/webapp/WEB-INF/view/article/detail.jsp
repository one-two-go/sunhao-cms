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
    <title>${article.title}</title>
</head>

<body>
<div class="row">
    <h1>${article.title}</h1>
    <h4>

     分类：   ${article.channel.name}
      作者：  ${article.user.uesrname}
      创造时间：  ${article.created}
    </h4>
    <div>
        ${article.content}
    </div>


</div>
</body>
</html>
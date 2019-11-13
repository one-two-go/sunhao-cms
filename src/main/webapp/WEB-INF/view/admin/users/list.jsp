<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

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
            <th>产品</th>
            <th>付款日期</th>
            <th>状态</th></tr>
        </thead>
        <tbody>
        <tr>
            <td>产品1</td>
            <td>23/11/2013</td>
            <td>待发货</td></tr>
        <tr>
            <td>产品2</td>
            <td>10/11/2013</td>
            <td>发货中</td></tr>
        <tr>
            <td>产品3</td>
            <td>20/10/2013</td>
            <td>待确认</td></tr>
        <tr>
            <td>产品4</td>
            <td>20/10/2013</td>
            <td>已退货</td></tr>
        </tbody>
    </table>
</div>
<ul class="pagination">
    <li><a href="#">&laquo;</a></li>
    <li><a href="#">1</a></li>
    <li><a href="#">2</a></li>
    <li><a href="#">3</a></li>
    <li><a href="#">4</a></li>
    <li><a href="#">5</a></li>
    <li><a href="#">&raquo;</a></li>
</ul>


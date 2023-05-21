<%--
  Created by IntelliJ IDEA.
  User: yaaaa
  Date: 2023/4/19
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>课程列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <!--<script type="text/javascript" src="spring_mvc_mm/static/js/jquery-1.8.2.min.js"></script>-->
    <script type="text/javascript">
        $(function () {
            $(".deleteCourse").click(function () {
                var flag=confirm("确认删除该条课程信息吗？");
                if(flag){
                    var href = $(this).attr("href");
                    $(".delform").attr("action", href).submit();
                    return false;
                }
                else{return false;}

            });
        })
    </script>

    <style>
        .btn {

            background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
            color: #fff;

        }
    </style>
</head>


<body>
<nav class="navbar navbar-expand-sm btn navbar-dark">
    <ul class="navbar-nav">
        <li class="nav-item active">
            您好：${user_session.username}&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/course/logout" >退出</a>
        </li>

    </ul>
</nav>

<form action="" class="delform" method="POST">
    <input type="hidden" name="_method" value="DELETE">
</form>

   <div class="container">
<table class="table table-striped table-bordered">
    <thead class="thead-light">
    <tr>
        <th colspan="5" style="text-align:center">课程列表</th>
    </tr>
    <tr>
        <th class="text-center">序号</th>
        <th class="text-center">课程图片</th>
        <th class="text-center">课程名称</th>
        <th class="text-center">学时</th>
        <th class="text-center">开课学院</th>
        <th class="text-center"><a href="${pageContext.request.contextPath}/to/add" class="btn" role="button">添加</a></th>
    </tr>
    <thead/>
    <tbody>
    <c:forEach var="course" items="${list}" varStatus="status">
        <tr>
            <td class="text-center"> ${course.id}</td>
            <td class="text-center">
                <c:if test="${course.images != null}">
                    <img alt="" width="100" height="100"src="${pageContext.request.contextPath}/${course.images}">
                </c:if>
            </td>
            <td class="text-center"> ${course.name}</td>
            <td class="text-center"> ${course.hours}</td>
            <td class="text-center"> ${course.sid}</td>
            <td class="text-center">
                <a href="${pageContext.request.contextPath}/course/updatecourse1/${course.id}" class="text-info">修改</a>

                <a class="deleteCourse text-info" href="${pageContext.request.contextPath}/course/deletecourse/${course.id}" >删除</a>

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

   </div>



</body>

</html>

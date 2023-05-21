<%--
  Created by IntelliJ IDEA.
  User: yaaaa
  Date: 2023/4/17
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <style>
        .btn {
            text-align: center;
            padding: 50px;
            background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
            color: #fff;
            border-width:0px;
            text-decoration:none;

            font-weight:bold;
            font-size:50px;
            width:400px;
            line-height: 60px;
            margin:0px;
            position: absolute;
            top: 50%;
            left: 50%;
            -ms-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
        }

    </style>

</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/to/login" class="btn" role="button">学生课程管理系统</a>

</div>


</body>
</html>

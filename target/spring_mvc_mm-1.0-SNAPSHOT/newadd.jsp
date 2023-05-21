<%--
  Created by IntelliJ IDEA.
  User: yaaaa
  Date: 2023/3/30
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>新增</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/registerUser" method="post">
    <h3>新增课程表单</h3>
    课程名称：<input type="text" name="coursename"/><br/>
    学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时：<input type="text" name="hours"/><br/>
    开课学院：<input type="text" name="sid"/><br/>
    <input type="submit" value="提交"/>
</form>

</body>
</html>

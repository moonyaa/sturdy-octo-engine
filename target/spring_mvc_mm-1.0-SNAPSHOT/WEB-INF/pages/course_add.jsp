<%--
  Created by IntelliJ IDEA.
  User: yaaaa
  Date: 2023/4/24
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>添加课程</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>

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
            您好：${user_session.username}&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/course/logout" class="text-primary">退出</a>
        </li>

    </ul>
</nav>

<script type="text/javascript">
    //下面用于图片上传预览功能
    function setImagePreview() {
        var docObj=document.getElementById("doc");
        var imgObjPreview=document.getElementById("preview");
        if(docObj.files &&docObj.files[0])
        {
            //火狐下，直接设img属性
            imgObjPreview.style.display = 'block';
            imgObjPreview.style.width = '100px';
            imgObjPreview.style.height = '100px';
            imgObjPreview.style.textAlign= 'center';
            //imgObjPreview.src = docObj.files[0].getAsDataURL();
            //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
        }
        else
        {
            //IE下，使用滤镜
            docObj.select();
            var imgSrc = document.selection.createRange().text;
            var localImagId = document.getElementById("localImag");
            //必须设置初始大小
            localImagId.style.width = "400px";
            localImagId.style.height = "400px";
            //图片异常的捕捉，防止用户修改后缀来伪造图片
            try{
                localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
            }
            catch(e){
                alert("您上传的图片格式不正确，请重新选择!");
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
        }
        return true;
    }
</script>

<div class="container">
<form  action="${pageContext.request.contextPath}/course/up" method="post" enctype="multipart/form-data">
    <table class="table table-striped table-bordered">
        <thead class="thead-light">
        <tr>
            <th colspan="2" style="text-align:center">添加课程</th>
        </tr>
        <tr>
            <td class="text-center">课程名称</td>
            <td class="text-center ">
                <input type="text" name="name">
            </td>
        </tr>
        </thead>

        <tbody>
        <tr>
            <td class="text-center ">学时</td>
            <td class="text-center ">
                <input type="text" name="hours">
            </td>
        </tr>
        <tr>
            <td class="text-center ">开课学院</td>
            <td class="text-center ">
                <input type="text" name="sid">
            </td>
        </tr>

        </tbody>
    </table>

    <div id="localImag" >

        课程图片&nbsp;&nbsp;<br>
        <img id="preview">
        <br>
        <input  type="file" id="doc" name="logoImage" onchange="javascript:setImagePreview();"/>
        </br>

    </div>
    <div style="text-align: center">
        <input type="submit" class="btn " value="上传" >
    </div>


</form>


</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Antoine
  Date: 2021/7/13
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en_US">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Admin Login</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function refreshCode(){
            var vcode = document.getElementById("vcode");

            vcode.src="${pageContext.request.contextPath}/verificationServlet?time="+new Date().getTime();
        }
    </script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">Admin Login</h3>
    <form action="${pageContext.request.contextPath}/loginServlet" method="post">
        <div class="form-group">
            <label for="user">Username：</label>
            <input type="text" name="username" class="form-control" id="user" placeholder="username"/>
        </div>

        <div class="form-group">
            <label for="password">Password：</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="password"/>
        </div>

        <div class="form-inline">
            <label for="vcode">Verification：</label>
            <input type="text" name="verifycode" class="form-control" id="verifycode" placeholder="Verification" style="width: 120px;"/>
            <a href="javascript:refreshCode()">
                <img src="${pageContext.request.contextPath}/verificationServlet" title="Can't see? Click me! " id="vcode"/></a>
        </div>
        <hr/>
        <div class="form-group" style="text-align: center;">
            <input style="margin-right: 10px" class="btn btn btn-primary" type="submit" value="Log in">
            <a style="margin-left: 10px" class="btn btn btn-primary" href="signup.jsp">Sign up</a>
        </div>
    </form>

    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" >
            <span>&times;</span></button>
        <strong>${login_msg}</strong>
    </div>
</div>
</body>
</html>

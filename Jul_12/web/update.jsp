<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Antoine
  Date: 2021/7/14
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en_US">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Update User</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">Update User</h3>
    <form action="${pageContext.request.contextPath}/updateUserServlet" method="post">
        <input type="hidden" value="${user.id}" name="id">

        <div class="form-group">
            <label for="name">Name：</label>
            <input type="text" class="form-control" id="name" name="name" value="${user.name}" readonly="readonly"
                   placeholder="Name"/>
        </div>

        <div class="form-group">
            <label>Gender：</label>
            <c:if test="${user.gender!='Female'}">
                <input type="radio" name="gender" value="Male" checked="checked"/>Male
                <input type="radio" name="gender" value="Female"/>Female
            </c:if>
            <c:if test="${user.gender=='Female'}">
                <input type="radio" name="gender" value="Male"/>Male
                <input type="radio" name="gender" value="Female" checked="checked"/>Female
            </c:if>

            <div class="form-group">
                <label for="age">Age：</label>
                <input type="text" class="form-control" id="age" value="${user.age}" name="age" placeholder="Age"/>
            </div>

            <div class="form-group">
                <label for="address">Address：</label>
                <select name="address" class="form-control">
                    <option value="AB">AB</option>
                    <option value="BC">BC</option>
                    <option value="MB">MB</option>
                    <option value="NL">NL</option>
                    <option value="NB">NB</option>
                    <option value="NS">NS</option>
                    <option value="ON">ON</option>
                    <option value="PE">PE</option>
                    <option value="QC">QC</option>
                    <option value="SK">SK</option>
                    <option value="NT">NT</option>
                    <option value="YT">YT</option>
                    <option value="NU">NU</option>
                </select>
            </div>

            <div class="form-group">
                <label for="phone">Phone：</label>
                <input type="text" class="form-control" name="phone" value="${user.phone}" placeholder="Phone"/>
            </div>

            <div class="form-group">
                <label for="email">Email：</label>
                <input type="text" class="form-control" name="email" value="${user.email}" placeholder="Email"/>
            </div>

            <div class="form-group" style="text-align: center">
                <input class="btn btn-primary" type="submit" value="Submit"/>
                <input class="btn btn-default" type="reset" value="Reset"/>
                <a href="${pageContext.request.contextPath}/findUserByPageServlet" class="btn btn-default">Back</a>
            </div>
    </form>
</div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Antoine
  Date: 2021/7/14
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- HTML5文档-->
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="en_US">

<script>


    /*
    分析：
    1.给表单绑定onsubmit事件。监听器中判断每一个方法校验的结果。
    如果都为true，则监听器方法返回true
    如果有一个为false，则监听器方法返回false

    2.定义一些方法分别校验各个表单项。
    3.给各个表单项绑定onblur事件。



    */

    window.onload = function () {
        //1.给表单绑定onsubmit事件
        document.getElementById("form").onsubmit = function () {
            //调用用户校验方法   chekUsername();
            //调用密码校验方法   chekPassword();
            //return checkUsername() && checkPassword();

            return checkName() && checkAge() &&checkPhone()&&checkEmail();
        }

        //给用户名和密码框分别绑定离焦事件
        document.getElementById("name").onblur = checkName;
        document.getElementById("age").onblur = checkAge;
        document.getElementById("phone").onblur=checkPhone;
        document.getElementById("email").onblur=checkEmail;
    }

    //校验用户名
    function checkName() {
        //1.获取用户名的值
        var name = document.getElementById("name").value;
        //2.定义正则表达式
        var reg_name = /^\w{1,16}$/;
        //3.判断值是否符合正则的规则
        var flag = reg_name.test(name);
        //4.提示信息
        var s_name = document.getElementById("s_name");

        if (flag) {
            //提示绿色对勾
            s_name.innerHTML = "<img width='35' height='25' src='img/checkmark.png'/>";
        } else {
            //提示红色用户名有误
            s_name.innerHTML = "Invalid format";
        }
        return flag;
    }


    function checkAge() {

        var age = document.getElementById("age").value;

        var reg_age = /^\d{1,3}$/;

        var flag = reg_age.test(age);

        var s_age = document.getElementById("s_age");

        if (flag) {

            s_age.innerHTML = "<img width='35' height='25' src='img/checkmark.png'/>";
        } else {

            s_age.innerHTML = "Invalid number";
        }
        return flag;
    }

    function checkPhone(){
        var phone=document.getElementById("phone").value;
        var reg_phone = /^\d{10}$/;
        var flag = reg_phone.test(phone);
        var s_phone=document.getElementById("s_phone");

        if(flag){
            s_phone.innerHTML="<img width='35' height'25' src='img/checkmark.png'/>";

        }else{
            s_phone.innerHTML="Invalid phone number"
        }
        return flag;
    }

    function checkEmail(){
        var email=document.getElementById("email").value;
        var reg_email = /^\w+@[a-zA-Z0-9]{2,10}(?:\.[a-z]{2,4}){1,3}$/;
        var flag = reg_email.test(email);
        var s_email=document.getElementById("s_email");

        if(flag){
            s_email.innerHTML="<img width='35' height'25' src='img/checkmark.png'/>";

        }else{
            s_email.innerHTML="Invalid format"
        }
        return flag;
    }
</script>

<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Add User</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <center><h3>Add Customer</h3></center>
    <form action="${pageContext.request.contextPath}/addUserServlet" id="form" method="post">
        <div class="form-group">
            <label for="name">Name：</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Type in your name">
            <span id="s_name" class="error"></span>
        </div>

        <div class="form-group">
            <label>Gender: </label>
                <input type="radio" name="gender" value="Male" checked="checked"/>Male
                <input type="radio" name="gender" value="Female"/>Female
        </div>

        <div class="form-group">
            <label for="age">Age：</label>
            <input type="text" class="form-control" id="age" name="age" placeholder="Type in your age">
            <span id="s_age" class="error"></span>
        </div>

        <div class="form-group">
            <label for="address">Address：</label>
            <select name="address" class="form-control" id="addr">
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
            <input type="text" class="form-control" id="phone" name="phone" placeholder="Type in your phone number"/>
            <span id="s_phone" class="error"></span>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" id="email" name="email" placeholder="Type in your email"/>
            <span id="s_email" class="error"></span>
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

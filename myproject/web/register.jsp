<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link href="css/register.css" rel="stylesheet">

</head>
<body>
<div class="head">
    <div class="log">
        <img src="img/log.png">
        <div class="word1">
            <span style="font-size: 35px;font-family: 方正隶变简体">右·路</span>
        </div>
        <div class="word2">账号注册</div>
    </div>
</div>
<div class="body">
    <div class="register">
        <div class="yx"><div style="margin-top: 2px;float: left"><img src="img/邮箱.png" ></div><span>&nbsp;邮箱注册</span></div>
        <form action="<c:url value="/user?method=register"/>" method="post">
            <div class="username">
                <label for="username">登录账号</label>
                <input name="username" type="text" id="username" style="width: 450px;height: 50px;font-size: 20px;font-family: 楷体"
                       placeholder="请输入邮箱地址"
                       onfocus="usernameOnFocus(this)"
                       onblur="usernameOnBlur(this)"
                >
                <span id="bof" style="color: red;visibility: hidden">邮箱地址</span>
            </div>
            <div class="password">
                <label for="password">登录密码</label>
                <input name="password" type="password" id="password"  style="width: 450px;height: 50px;font-size: 20px;font-family: 楷体"
                       placeholder="请输入登录密码"
                       onfocus=passwordOnFocus(this)
                       onblur=passwordOnBlur(this)

                >
                <span id="fof" style="color: red;visibility: hidden">请输入密码</span>
            </div>
            <div class="agree">
                <input type="checkbox" id="agree">
                <label for="agree"> 同意并遵守<a href="" style="color:rgb(71,118,209)">《服务条款》</a></label>
            </div>
            <input class="next" type="submit" value="下一步" style="cursor: pointer"
                onmouseover="this.style.backgroundColor='rgb(80,141,232)'"
                   onmouseout="this.style.backgroundColor='rgb(59,128,229)'"
                   onmousedown="this.style.backgroundColor='cornflowerblue';
                                this.style.border='solid 2px white'"
                   onmouseup="this.style.backgroundColor='rgb(80,141,232)'"
            >
        </form>
    </div>
</div>
</body>
<script src="js/register.js"></script>
</html>
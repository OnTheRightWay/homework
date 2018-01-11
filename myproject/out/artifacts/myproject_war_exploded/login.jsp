<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link href="css/login.css" rel="stylesheet">
</head>
<body>
    <div class="head">
        <div class="log">
            <img src="img/log.png">
            <div class="word1">
                <span style="font-size: 35px;font-family: 方正隶变简体">右·路</span>
            </div>
            <div class="word2">登录</div>
        </div>
        <ul>
            <li style="border-right: solid 1px rgb(223,223,223)"><a href="index.jsp"><span style="font-family: 鹿亡玫瑰海by木一">RW</span>首页</a></li>
            <li><a href="">帮助与文档</a></li>
        </ul>
    </div>
    <div id="time"></div>
    <div class="body">
        <img src="img/Dream.png" class="dream">
        <div class="input">
            <p class="word1"><span style="font-family: 鹿亡玫瑰海by木一">RW</span>登录</p>
            <form action="<c:url value="/user?method=login"/>" method="post">
                <input name="username" type="text"class="username" style="font-size: 20px;font-family: 楷体"
                       placeholder="邮箱地址"
                       onfocus=usernameOnFocus(this)
                       onblur=usernameOnBlur(this)
                       value="${cookie.username.value}"
                >
                <img id="u" src="img/用户.png">
                <input name="password" type="password"class="password" style="font-size: 20px;font-family: 楷体"
                       placeholder="登录密码"
                       onfocus=passwordOnFocus(this);
                       onblur=passwordOnBlur(this)>
                <img id="p" src="img/密码.png">
                <div class="word2">
                    <input class="agree" type="checkbox" id="agree" style="margin-top: 40px;height: 14px;width: 14px">
                    <label class="agree" for="agree"> 同意并遵守<a href="" style="color:rgb(71,118,209)">《服务条款》</a></label>
                    <div style="float: right">
                        <input class="t15" type="checkbox" id="15" style="margin-top: 40px;height: 14px;width: 14px">
                        <label class="t15" for="15"> 15天免登录</label>
                    </div>
                </div>
                <input type="submit" value="登 录" class="button" style="cursor: pointer"
                       onmouseover="this.style.backgroundColor='rgb(80,141,232)'"
                       onmouseout="this.style.backgroundColor='rgb(59,128,229)'"
                       onmousedown="this.style.backgroundColor='cornflowerblue';
                                this.style.border='solid 2px white'"
                       onmouseup="this.style.backgroundColor='rgb(80,141,232)'"
                >
            </form>
            <div class="word3">
                <span style="float: left">还没有账号？<a href="register.jsp" style="color:rgb(71,118,209)" target="_blank">免费注册</a></span>
                <span style="float: right"><a href="" style="color:rgb(71,118,209) ">忘记密码？</a></span>
            </div>
        </div>
    </div>
    <div></div>
</body>
<script src="js/login.js">

</script>
</html>
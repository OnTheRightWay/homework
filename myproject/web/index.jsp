<%--
  Created by IntelliJ IDEA.
  User: lanou3g
  Date: 2018/1/2
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>RW首页</title>
  <link href="css/homepage.css" rel="stylesheet">
  <script src="js/jquery-3.2.1.min.js"></script>
  <style>
    a{
      text-decoration-line: none;
      color: black;
    }
  </style>

</head>
<body>
<div class="head">
  <div class="log">
    <img src="img/log.png">
    <div class="word1">
      <span style="font-size: 35px;font-family: 方正隶变简体">右·路</span>
    </div>
    <div class="word2">首页</div>
  </div>
  <p style="float: right;line-height: 95px;margin-right: 40px"><a href="home">退出登录</a>
  <div id="user" style="">
    <%
      String username = (String)session.getAttribute("username");
      if (username!=null){
    %>
          当前用户：<%=username%>
    <%
      }else {
    %>
          <a href="login.jsp" id="quit">请登录</a>
    <%
      }
    %>
  </div>
</div>
<div class="body">
  <div class="nav">
    <%
      if (username!=null){
    %>
    <form action="home" method="post">
    书名：<input type="text" name="bname">
    <br>
    价钱：<input type="text" name="price">
    <br>
    作者：<input type="text" name="author">
    <br>
    <input type="submit" value="确定" id="submit">
    </form>
    <%
      }
    %>
  </div>
  <%
    if (username!=null){
  %>
  <table id="show">
  </table>
  <%
    }
  %>
</div>
</body>
<script type="text/javascript">
  function show() {
      var url= "http://localhost:8080/Day29/login";
  $.getJSON(
      url,function (JSONData, status) {
          if (status=="success"){
              $('table').append(
                  $('<tr>').append($('<td>').text("书名"))
                      .append($('<td>').text("作者"))
                      .append($('<td>').text("价钱"))
              );
              $.each(JSONData,function (index, data) {
                  $('table').append(
                      $('<tr>').append($('<td>').append($('<a>').attr({"href":"http://localhost:8080/Day29/show.jsp?bname="+data['bname']}).text(data['bname'])))
                          .append($('<td>').text(data['author']))
                          .append($('<td>').text(data['price']))
                  );
              })
          }
      })
  }
  show();
</script>
</html>

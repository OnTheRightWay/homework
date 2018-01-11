<%--
  Created by IntelliJ IDEA.
  User: lanou3g
  Date: 2018/1/2
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>RW首页</title>
  <link href="css/homepage.css" rel="stylesheet">
  <script src="js/jquery-3.2.1.min.js"></script>
  <style>

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
  <p style="float: right;line-height: 95px;margin-right: 40px"><a href="<c:url value="/user?method=quit"/>">退出登录</a>
  <div id="user" style="">
  <c:if test="${sessionScope.user!=null}" var="user" scope="request">
    当前用户：${sessionScope.user.username}
  </c:if>
  <c:if test="${!requestScope.user}">
    <a href="login.jsp" id="quit">请登录</a>
  </c:if>

  </div>
</div>
<div class="body">
  <div class="nav">
    <ul>
      <li><button onclick="showAll()">全部</button></li>
      <li><button onclick=show("小说")>小说</button></li>
      <li><button onclick=show("文学")>文学</button></li>
      <li><button onclick=show("传记")>传记</button></li>
      <li><button onclick=show("教育")>教育</button></li>
      <li><button onclick=show("动漫")>动漫</button></li>
      <li><button onclick=show("外语")>外语</button></li>
      <li><button onclick=show("杂志")>杂志</button></li>
    </ul>
  </div>
  <div class="find" style="height: 35px;width: 1600px;padding: 15px 0 0 0;
        background-color:rgb(200,200,200);float: left;
        margin-left: 20px">
    <form action="" method="post">
      <input type="text" style="margin-left: 50px;height: 27px;width: 255px">
      <input type="submit" value="搜索" style="width: 72px;height: 26px">
    </form>
  </div>
  <c:if test="${requestScope.user}">
    <div id="show">
    </div>
  </c:if>
</div>
</body>
<script type="text/javascript">

  function addTable(JSONData) {
      $.each(JSONData,function (index, data) {
          $('#show').append(
              $('<table>').append(
                  ($('<tr>')).append(
                      $('<td>').attr({"value":data['bkid']}).
                      append($('<a>').attr({"href":"<c:url value="/show.jsp?bkid="/>"+data['bkid']}).append($('<img>').
                      attr({"src":data['cover'],"class":"cover"})))
                  )
              ).append(
                  ($('<tr>')).append(
                      $('<td>').text("书名："+data['bkname'])
                  )
              ).append(
                  ($('<tr>')).append(
                      $('<td>').text("作者："+data['author'])
                  )
              ).append(
                  ($('<tr>')).append(
                      $('<td>').text("价格："+data['price']+"元")
                  )
              )
          )
      })
  }
  function showAll() {
  $.getJSON(
       "<c:url value="/show"/>",function (JSONData, status) {
          if (status=="success"){
             addTable(JSONData);
          }
      })
  }
  function show(type) {
      $.getJSON(
          "<c:url value="/show"/>",function (JSONData, status) {
              if (status=="success"){
                  $.each(JSONData,function (index, data) {
                      if (data['type'].eq(type)){
                          addTable(JSONData);
                      }
                  })
              }
          })
  }
  showAll();
</script>
</html>

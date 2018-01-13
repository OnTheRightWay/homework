<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>left</title>
    <base target="body"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	  <script src="<c:url value="/js/jquery-3.2.1.min.js"/>" type="text/javascript"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		*{
			font-size:10pt;
			text-align: center;
		}
		.left{
			width:120px;
			height: 100%;
			/*border: solid 1px black;*/
			border-right: solid 1px black;
			float: left;
		}
		.right{
			width: 853px;
			height: 100%;
			border: solid 1px black;
			float: left;
		}
		.left div {
			background: #87CEFA; 
			margin: 3px; 
			padding: 3px;
		}
		a {
			text-decoration: none;
		}
		.icon {
			margin:10px;
			border: solid 2px gray;
			width: 160px;
			height: 180px;
			text-align: center;
			float: left;
		}
		/*iframe {*/
			/*width: 100%;*/
			/*height: 100%;*/
		/*}*/
	</style>
  </head>
  
  <body>
  <div class="left">
	  <div>
		  <div onclick=show("findAll",'')>全部分类</div>

	  </div>
	  <c:forEach var="category1" items="${requestScope.category}">
		  <div>
				  <%--<a href="<c:url value="/book?method=findByCname&cname=${category1.cname}"/>">${category1.cname}</a>--%>
			  <div onclick=show("findByCname","${category1.cname}")>${category1.cname}</div>
		  </div>
	  </c:forEach>
  </div>
  <div class="right">

  </div>
  </body>
  <script type="text/javascript">
      function show(method,cname) {
          <c:if test="${requestScope.category==null}">
		  	<c:redirect url="category"/>
		  </c:if>
          var url = "book?method="+method+"&cname="+cname;
          console.log(url);
          $.getJSON(url,function (JSONData, status) {
              console.log(url);
              if (status=="success"){
                  $('.right div').remove();
                  $.each(JSONData,function (index, data) {
                      $('.right').append(
                          $('<div>').attr({"class":"icon"}).append(
                              $('<a>').attr({"href":"<c:url value="/book?method=showBook&bid="/>"+data['bid'],"target":"_self"}).append(
                                  $('<img>').attr({"src":"/BookStore"+data['image'],"border":"0"})
                              )
                          ).append($('<br>')).append(
                              $('<a>').attr({"href":"<c:url value='/jsps/book/desc.jsp'/>","target":"_self"})
                                  .text(data['bname'])
                          )
                      );
                  })
              }
          })
      }
  </script>
</html>

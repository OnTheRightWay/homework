<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
      <script src="../../js/jquery-3.2.1.min.js" type="text/javascript"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		font-size: 10pt;
	}
	.icon {
		margin:10px;
		border: solid 2px gray;
		width: 160px;
		height: 180px;
		text-align: center;
		float: left;
	}
</style>
  </head>

  <body>

  <%--<c:forEach var="book" items="${requestScope.books}">--%>
      <%--<div class="icon">--%>
      <%--<a href="<c:url value="/book?method=showBook&bid=${book.bid}"/>">--%>
          <%--<img src="<c:url value='${book.image}'/>" border="0"/></a>--%>
      <%--<br/>--%>
      <%--<a href="<c:url value='/jsps/book/desc.jsp'/>">${book.bname}</a>--%>
      <%--</div>--%>
  <%--</c:forEach>--%>
  </body>
<script type="text/javascript">
    function show() {
        var url="<c:url value="/book?method=${param.method}&cname=${param.cname}"/>";
        console.log(url);
        $.getJSON(url,function (JSONData, status) {
            console.log(url);
            if (status=="success"){
                $.each(JSONData,function (index, data) {
                    $('body').append(
                        $('<div>').attr({"class":"icon"}).append(
                            $('<a>').attr({"href":"<c:url value="/book?method=showBook&bid="/>"+data['bid']}).append(
                                $('<img>').attr({"src":"/BookStore"+data['image'],"border":"0"})
                            )
                        ).append($('<br>')).append(
                            $('<a>').attr({"href":"<c:url value='/jsps/book/desc.jsp'/>"})
                                .text(data['bname'])
                        )
                    );
                })
            }
        })
    }
    show();
</script>
 
</html>


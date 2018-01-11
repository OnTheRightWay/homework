<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'bookdesc.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		font-size: 10pt;
		background: rgb(254,238,189);
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
</style>
  </head>
  
  <body>
  <div>
    <img src="<c:url value='${requestScope.book.image}'/>" border="0"/>
  </div>
  <form style="margin:20px;" id="form" action="javascript:alert('操作成功！');" method="post">
  	图书名称：<input type="text" name="bname" value="${requestScope.book.bname}"/><br/>
  	图书单价：<input type="text" name="price" value="${requestScope.book.price}"/><br/>
  	图书作者：<input type="text" name="author" value="${requestScope.book.author}"/><br/>
	  图书分类：<select style="width: 150px; height: 20px;" name="cid">
	  <c:forEach var="category1" items="${requestScope.categories}">
		  <c:if test="${category1.cid eq requestScope.book.cid}">
		  <option value="${category1.cid}">${category1.cname}</option>
		  </c:if>
	  </c:forEach>
	  <c:forEach var="category2" items="${requestScope.categories}">
		  <c:if test="${!(category2.cid eq requestScope.book.cid)}">
			  <option value="${category2.cid}">${category2.cname}</option>
		  </c:if>
	  </c:forEach>
    	</select><br/>
  	<input type="submit" name="method" value="删除" onclick="del(this.form)"/>
  	<input type="submit" name="method" value="修改" onclick="edit(this.form)"/>
  </form>
  </body>
<script type="text/javascript">
	function del(form) {
		form.action="<c:url value="/adminBook?method=delete&bid=${requestScope.book.bid}"/>";
		form.submit();
    }
	function edit(form) {
		form.action="<c:url value="/adminBook?method=edit&bid=${requestScope.book.bid}&image=${requestScope.book.image}"/>";
		form.submit();
    }
</script>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
    
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
	* {
		font-size: 11pt;
	}
	div {
		border: solid 2px gray;
		width: 75px;
		height: 75px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buy {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -902px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -938px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
<h1>我的订单</h1>
<table border="1" width="100%" cellspacing="0" background="black">
	<c:forEach items="${requestScope.orders}" var="map1">
		<tr bgcolor="gray" bordercolor="gray">
			<td colspan="6">
				订单编号：${map1.key.oid}　成交时间：${map1.key.ordertime}　金额：<font color="red"><b>${map1.key.price}</b></font>　
				<c:if test="${map1.key.state eq 0}">
					<a href="<c:url value='/order?method=load&oid=${map1.key.oid}'/>">待付款</a>
				</c:if>
				<c:if test="${map1.key.state eq 1}">
					已付款 等待发货

				</c:if>
				<c:if test="${map1.key.state eq 2}">
					已发货
					<a href="<c:url value="/order?method=confirm&oid=${map1.key.oid}"/>">确认收货</a>
				</c:if>
				<c:if test="${map1.key.state eq 3}">
					订单结束
				</c:if>


			</td>
		</tr>
		<c:forEach var="map2" items="${map1.value}">
			<tr bordercolor="gray" align="center">
				<td width="15%">
					<div><img src="<c:url value='${map2.value.image}'/>" height="75"/></div>
				</td>
				<td>书名：${map2.value.bname}</td>
				<td>单价：${map2.value.price}元</td>
				<td>作者：${map2.value.author}</td>>
				<td>数量：${map2.key.count}</td>
				<td>小计：${map2.key.subtotal}元</td>
			</tr>
		</c:forEach>
	</c:forEach>
</table>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>main</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <script src="/js/jquery-3.2.1.min.js"></script>
	<style type="text/css">
		*{
			font-size:10pt;
		}
		body{
			text-align:center;
		}
		.table{
			width:1024px;
			height:100%;
			border:1px solid gray;/*固定边框,1像素*/
		    border-collapse: collapse;/*单线的列表边框*/
		}
		.table td{
			border:1px solid gray;/*固定边框,1像素*/
		}
		iframe {
			width: 100%;
			height: 100%;
		}


		#body .icon {
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
<table class="table" align="center">
	<tr style="background: #4682B4; height: 120px; ">
		<td colspan="2" align="center">
			<iframe frameborder="0" src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe>
		</td>
	</tr>
	<tr>
		<td
				width="1024"
			style="padding:5px;" align="center" valign="top" n>
			<iframe frameborder="0" width="120" src="<c:url value='/category?method=queryAll'/>" name="left"></iframe>

		</td>



		<%--<td>--%>
			<%--&lt;%&ndash;<div id="body"></div>&ndash;%&gt;--%>
			<%--&lt;%&ndash;<c:forEach var="book" items="${requestScope.books}">&ndash;%&gt;--%>
				<%--&lt;%&ndash;<div class="icon">&ndash;%&gt;--%>
					<%--&lt;%&ndash;<a href="<c:url value="/book?method=showBook&bid=${book.bid}"/>">&ndash;%&gt;--%>
						<%--&lt;%&ndash;<img src="<c:url value='${book.image}'/>" border="0"/></a>&ndash;%&gt;--%>
					<%--&lt;%&ndash;<br/>&ndash;%&gt;--%>
					<%--&lt;%&ndash;<a href="<c:url value='/jsps/book/desc.jsp'/>">${book.bname}</a>&ndash;%&gt;--%>
				<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
			<%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
			<%--<iframe frameborder="0" src="<c:url value='/jsps/body.jsp'/>" name="body"></iframe>--%>
		<%--</td>--%>
	</tr>
</table>
  </body>
  <script type="text/javascript">

  </script>
</html>

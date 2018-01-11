<%--
  Created by IntelliJ IDEA.
  User: lanou3g
  Date: 2018/1/3
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>书籍详情</title>

</head>
<body>
<%--<h1 class="bname"></h1>--%>
<%--<h1 class="author"></h1>--%>
<%--<h1 class="price"></h1>--%>
<img id="cover" style="height: 200px;width: 150px"/>
<br>
<p id="bkname"></p>
<p id="author"></p>
<p id="type"></p>
<h1 id="title"></h1>
<p id="details"></p>


</body>
<script src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    <%--<%--%>
           <%--request.setCharacterEncoding("utf-8");--%>
<%--//           String bname = request.getParameter("bname");--%>
       <%--%>--%>
    function show() {
        var src="<c:url value="/show"/>"+"?bkid=${param.bkid}";
        $.getJSON(
            src,
            function (JSONData, status) {
                if (status=="success"){
                    $('#cover').attr({"src":JSONData['cover']});
                    $('#bkname').text("书名："+JSONData['bkname']);
                    $('#author').text("作者："+JSONData['author']);
                    $('#type').text("类型："+JSONData['type']);
                    $('#title').text("书籍简介：");
                    $('#details').text("作者："+JSONData['details']);
                }
            }
        );
    }
    show();
</script>

</html>

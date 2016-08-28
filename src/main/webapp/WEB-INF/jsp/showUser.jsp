<%--
  Created by IntelliJ IDEA.
  User: lichen.ll
  Date: 2016/8/28
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>用户信息</title>
</head>
<body>
姓名：<%=request.getAttribute("userName")%>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 2019/5/11
  Time: 11:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>error</title>
</head>
<body>
<c:if test="${allErrors!=null}">
    <c:forEach items="${allErrors}" var="error">
        <font color="aqua">${error.defaultMessage}</font>
    </c:forEach>
</c:if>
</body>
</html>

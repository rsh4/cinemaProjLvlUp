<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style><%@include file="/WEB-INF/css/main.css"%></style>
</head>
<body>
<div class="header">
    <c:if test="${not empty loggeduser.username}">
        <div class="le"><a href="${pageContext.request.contextPath}/main/panel/logout" class="registration_reference">logout</a></div>
        <div class="le1">
            <a href="${pageContext.request.contextPath}/main/person/client"><img src="${pageContext.request.contextPath}/main/getimage/inside/pr" width="44" height="44"/>
            </a></div>
    </c:if>
    <c:if test="${empty loggeduser.username}">
        <div class="le"><a href="${pageContext.request.contextPath}/main/panel/login" class="registration_reference">login</a></div>
        <div class="le"><a href="${pageContext.request.contextPath}/main/panel/registration" class="registration_reference">registration</a></div>
    </c:if>
    <c:if test="${client.city != ' '}">
    <div class="le">
        <a href="${pageContext.request.contextPath}/main/choice" class="registration_reference">${client.city}</a>
    </div>
    </c:if>
</div>
</body>
</html>

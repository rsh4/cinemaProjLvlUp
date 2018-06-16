<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style><%@include file="/WEB-INF/css/main.css"%></style>
</head>
<body>
<c:import url="head.jsp"/>
<div class="login_div">
<form method="POST" action="${pageContext.request.contextPath}/main/panel/login">
        <input name="username" type="text" placeholder="Username" class="text_input"
               autofocus="true"/>
        <br/>
        <input name="password" type="password" placeholder="Password" class="text_input"/>
        <br/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="accept">Log In</button>
        <br/>
        <c:if test="${not empty param.error}">
        <p class="wrong">Wrong login or password</p>
        </c:if>
        <br/>
        <a href="${pageContext.request.contextPath}/main/panel/registration"
               class="registration_reference">Create an account</a>
</form>
</div>
</body>
</html>

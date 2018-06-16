<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style><%@include file="/WEB-INF/css/main.css"%></style>
</head>
<body>
<c:import url="head.jsp"/>
<div class="registration_div">
<form:form action="/main/panel/registration" modelAttribute="user" method="post">
    <form:label path="username">Login</form:label>
    <br/>
    <form:input path="username" />
    <br/>
    <form:errors path="username" cssClass="wrong"/>
    <br/>
    <form:label path="password" >Password</form:label>
    <br/>
    <form:password path="password" />
    <br/>
    <form:errors path="password" cssClass="wrong"/>
    <br/>
    <form:label path="confirmPassword" >Confirm password</form:label>
    <br/>
    <form:password path="confirmPassword" />
    <br/>
    <form:errors path="confirmPassword" cssClass="wrong"/>
    <br/>
    <button type="submit" class="accept">Sign up</button>
</form:form>
<p>${successfully}</p>
<a href="${pageContext.request.contextPath}/main">Back to main menu</a>
</div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style><%@include file="/WEB-INF/css/main.css"%></style>
</head>
<body>
<c:import url="head.jsp "/>
<div class="admin_choice">
<br/><br/>
<a href="${pageContext.request.contextPath}/main/person/admin/addfilm" class="admin_choice_reference">Add Film</a>
<br/><br/><br/>
<a href="${pageContext.request.contextPath}/main/person/admin/deletefilm" class="admin_choice_reference">Delete Film</a>
<br/><br/><br/>
<a href="${pageContext.request.contextPath}/main/person/admin/addsession" class="admin_choice_reference">Add Session</a>
<br/><br/>
</div>
</body>
</html>

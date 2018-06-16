<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style><%@include file="/WEB-INF/css/main.css"%></style>
</head>
<body>
<c:import url="head.jsp"/>
<c:forEach var="session" items="${sessions}">
    <div class="delete_film">
        <p>${session.id}</p>
        <p>${session.startTime}</p>
        <p>${session.endTime}</p>
        <p>${session.product.cost}</p>
        <p>${session.product.film.title}</p>
        <a href="/main/person/admin/deletesession/${session.id}" class="delete_film_reference">delete</a>
    </div>
</c:forEach>
</body>
</html>

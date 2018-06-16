<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style><%@include file="/WEB-INF/css/main.css"%></style>
</head>
<body>
<c:import url="head.jsp"/>
<c:forEach var="film" items="${films}">
<div class="delete_film">
    <p>Id:${film.id}</p>
    <p>Title:${film.title}</p>
    <p>Age limit:${film.ageLimit}</p>
    <img src="/main/getimage/${film.poster}" class="film_image">
    <p>${film.description}</p>
    <p>${film.premiere}</p>
    <a href="/main/person/admin/deletefilm/${film.id}" class="delete_film_reference">delete</a>
</div>
</c:forEach>
<br/><br/><br/>
</body>
</html>

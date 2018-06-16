<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style><%@include file="/WEB-INF/css/main.css"%></style>
</head>
<body>
<c:import url="head.jsp"/>
    <div>
        <div class="film_container">
            <img src="/main/getimage/${film.poster}" class="premiere_image"></a>
            <p class="film_description">Название: ${film.title}</p>
            <br/>
            <p class="film_description">Возрастной лимит: ${film.ageLimit}</p>
            <br/>
            <p class="film_description">Жанр:
            <c:forEach var="genre" items="${genres}">
                ${genre.genre}
            </c:forEach></p>
            <br/>
            <p class="film_description">Дата премьеры: ${film.premiere}</p>
            <br/>
            <p class="film_description">Описание: ${film.description}</p>
            <br/>
            <a href="/main/${film.title}/table" class="delete_film_reference">Table</a>
        </div>
    </div>
</body>
<c:import url="footer.jsp"/>
</html>

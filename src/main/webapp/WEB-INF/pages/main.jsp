<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
    <style><%@include file="/WEB-INF/css/main.css"%></style>
</head>
<body>
<c:import url="head.jsp"/>
    <div class="main_container">
        <c:forEach var="film" items="${premierefilms}">
            <div class="main_container_element_premiere">
                <a href="/main/${film.title}">
                    <img src="/main/getimage/${film.poster}" class="premiere_image"></a>
            </div>
        </c:forEach>
        <c:forEach var="film" items="${films}">
            <div class="main_container_element_film">
                <a href="/main/${film.title}">
                    <img src="/main/getimage/${film.poster}" class="film_image">
                </a>
            </div>
        </c:forEach>
    </div>
<br/>
<br/>
<br/>
<c:import url="footer.jsp"/>
</body>
</html>

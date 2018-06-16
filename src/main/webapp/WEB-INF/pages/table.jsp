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
        <c:forEach var="sessionsArray" items="${sessions}" varStatus="status">
            <div class="cinema_name"><p>${cinemas.get(status.index).name}</p></div>
            <br/>
            <c:forEach var="session" items="${sessionsArray}">
                <div class="outside_container">
                <div class="cinema_text">Time:${session.session.startTime}
                    ${session.session.endTime}</div>
                    <div class="cinema_text">Type:${session.session.product.cost.filmKind}D
                        <br/>
                    Cost:${session.session.product.cost.cost}rubles</div>
                <br/>
                </div>
                <br/>
                </c:forEach>
            </c:forEach>
    </div>
</body>
</html>

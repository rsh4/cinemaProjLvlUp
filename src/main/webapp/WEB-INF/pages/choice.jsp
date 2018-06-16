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
<script type='text/javascript' src='../css/select.js'></script>
<div class="choice">
<form action="${pageContext.request.contextPath}/main/choice" method="post">
    <br/>
    <label class="label_choice">Выберите город</label>
    <div class="select-style">
    <select name="choice">
        <option value="Moscow">Moscow</option>
        <option value="Volgograd">Volgograd</option>
    </select>
    </div>
    <br/>
    <button type="submit" class="accept">Accept</button>
    <br/>
</form>
</div>
</body>
</html>

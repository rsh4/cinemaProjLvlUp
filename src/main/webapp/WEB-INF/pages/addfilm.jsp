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
<div class="add_film">
<form:form action="/main/person/admin/addfilm" enctype="multipart/form-data" modelAttribute="film" method="post">
    <br/>
    <label>Image</label>
    <br/>
    <input name="image" class="image" type="file"/>
    <br/>
    <form:errors path="image" cssClass="wrong"/>
    <br/>
    <form:label path="title">Title</form:label>
    <br/>
    <form:input path="title"/>
    <br/>
    <form:errors path="title" cssClass="wrong"/>
    <br/>
    <form:label path="ageLimit" >Age limit</form:label>
    <br/>
    <form:input path="ageLimit"/>
    <br/>
    <form:errors path="ageLimit" cssClass="wrong"/>
    <br/>
    <form:label path="description">Description</form:label>
    <br/>
    <form:input path="description"/>
    <br/>
    <form:errors path="description" cssClass="wrong"/>
    <br/>
    <form:label path="filmGenres">Genres</form:label>
    <br/>
    <form:input path="filmGenres"/>
    <br/>
    <form:errors path="filmGenres" cssClass="wrong"/>
    <br/>
    <label title="odate">Premiere</label>
    <br/>
    <input name="odate" type="date"/>
    <br/>
    ${odateError}
    <br/>
    <button type="submit" class="admin_choice_reference">Submit</button>
</form:form>
<c:if test="${not empty successfully}">
${successfully}
<br/>
<a href="${pageContext.request.contextPath}/main/person/admin">Back to admin menu</a>
</c:if>
</div>
<br/><br/><br/>
</body>
</html>

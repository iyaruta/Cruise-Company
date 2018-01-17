<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="include/header.jsp" %>

<h1>Регистрация</h1>
<form class="" action="/registration" method="post">
    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${user.name}">
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="${user.password}">
    </div>
    <div class="form-group">
        <input type="submit">
    </div>
</form>

<%@include file="include/footer.jsp" %>
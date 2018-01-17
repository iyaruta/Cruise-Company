<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="include/header.jsp" %>

<h1>Вход</h1>
<form class="" action="/login" method="post">
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
    <c:if test="${error_login}">
        <div class="form-group">
            <span class="error">Пароль неверный</span>
        </div>
    </c:if>
</form>

<%@include file="include/footer.jsp" %>

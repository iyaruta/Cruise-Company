<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<table>
    <caption>Ships</caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Passengers</th>
        <th>Crew</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ships}" var="ship">
        <tr>
            <td>${ship.id}</td>
            <td>${ship.name}</td>
            <td>${ship.passengers}</td>
            <td>${ship.crew}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>

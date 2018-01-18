<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../include/header.jsp" %>
<h1>Круиз - ${cruise.name}</h1>
<td>${ship.name}</td>
<table class="table">
    <caption>Билеты</caption>
    <thead>
    <tr>
        <th>TicketClass</th>
        <th>Количестьво</th>
        <th>Продано</th>
        <th>Bonus</th>
        <th>Цена</th>
        <c:if test="${sessionScope.user.role eq 'ADMIN'}">
            <th>Изменить цены</th>
        </c:if>
        <th>Buy</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ticketClasses}" var="ticketClass">
        <tr>
            <td>${ticketClass.type}</td>
            <td>${ticketClass.count}</td>
            <td>***</td>
            <td>${ticketClass.bonus}</td>
            <td>${ticketClass.price}</td>
            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <td><a href="/admin/ticket/save?cruiseId=${cruise.id}&ticketClassId=${ticketClass.id}" class="active">Изменить
                    цены</a></td>
            </c:if>
            <td>
                <c:if test="${sessionScope.user.role eq 'USER'}">
                    <c:if test="${not empty ticketClass.price}">
                        <a href="/ticketClass?id=${ticketClass.id}" class="active">Buy</a>
                    </c:if>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
<hr>
<table class="table">
    <caption>Маршрут</caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Arrival</th>
        <th>Departure</th>
        <th>Excursion</th>
        <c:if test="${sessionScope.user.role eq 'ADMIN'}">
            <th>Update port</th>
            <th>Delete port</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${waypoints}" var="waypoint">
        <tr>
            <td>${waypoint.id}</td>
            <td>${waypoint.port.name}</td>
            <td>
                <c:choose>
                    <c:when test="${empty waypoint.arrival}">
                        Начало круиза
                    </c:when>
                    <c:otherwise>
                        <javatime:format value="${waypoint.arrival}" pattern="dd.MM.yyyy HH:mm"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${empty waypoint.departure}">
                        Конец круиза
                    </c:when>
                    <c:otherwise>
                        <javatime:format value="${waypoint.departure}" pattern="dd.MM.yyyy HH:mm"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td><a href="/excursion?portId=${waypoint.port.id}" class="active">Excursion</a></td>
            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <td><a href="/admin/waypoint/save?id=${waypoint.id}&cruiseId=${cruise.id}" class="active">Изминить</a>
                </td>
                <td><a href="/admin/waypoint/delete?id=${waypoint.id}" class="active">Удалить</a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${sessionScope.user.role eq 'ADMIN'}">
    <a href="/admin/waypoint/save?cruiseId=${cruise.id}" class="btn btn-success">Add new</a>
</c:if>

<%@include file="../include/footer.jsp" %>
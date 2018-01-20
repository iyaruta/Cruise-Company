<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../include/header.jsp" %>
<table class="table">
    <caption>Круиз</caption>
    <thead>
    <tr>
        <th>Cruise name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cruises}" var="cruise">
        <tr>
            <td><a href="/cruise/details?id=${cruise.id}" class="active">${cruise.name}</a></td>
        </tr>
    </c:forEach>
    </tbody>

</table>
<hr>
<table class="table">
    <caption>Excursion</caption>
    <thead>
    <tr>
        <th>Excursion name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${excursions}" var="excursion">
        <tr>
            <td><a href="/excursion?portId=${excursion.portId}" class="active">${excursion.name}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@include file="../include/footer.jsp" %>

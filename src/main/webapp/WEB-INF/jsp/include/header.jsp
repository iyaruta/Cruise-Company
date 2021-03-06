<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Cruise Company</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <style>
        .head_shift {
            margin-top: 65px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Cruise Company</a>
            <a class="navbar-brand" href="/cruise">Круизы</a>
            <a class="navbar-brand" href="/ship">Корабли</a>
            <a class="navbar-brand" href="/port">Порты</a>

        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <form class="navbar-form navbar-right">

                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <div class="form-group">
                            <a href="/privateOffice" class="btn btn-success">Welcome, ${sessionScope.user.name}</a>
                            <a href="/logout" class="btn btn-danger">Выход</a>

                        </div>
                    </c:when>
                    <c:otherwise>
                        <a href="/login" class="btn btn-success">Вход</a>
                        <a href="/registration" class="btn btn-success">Регистрация</a>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
    </div>
</nav>
<div class="head_shift"></div>
<div class="container">
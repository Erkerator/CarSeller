<%--
  Created by IntelliJ IDEA.
  User: erkerator
  Date: 29.01.19
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="label.title"/></title>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
    <!--<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>-->
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <a href="/welcome" class="navbar-brand">Carseller</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a href="/login" class="nav-link"><fmt:message key="label.login"/></a>
                </li>
                <li class="nav-item">
                    <a href="/registration" class="nav-link"><fmt:message key="label.registration"/></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="label.lang"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="/registration?sessionLocale=en">ENG</a>
                        <a class="dropdown-item" href="/registration?sessionLocale=ru">РУС</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
    <form class="registerForm" action="/register" method="post">
        <h3 class="text-center"><fmt:message key="label.registration"/></h3>
        <c:choose>
            <c:when test="${incorrectData == true}">
                <p class="text-center" style="color: red"><fmt:message key="label.incorrectFields"/></p>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${userExists == true}">
                <p class="text-center" style="color: red"><fmt:message key="label.userExists"/></p>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${incorrectInputText == true}">
                <p class="text-center" style="color: red"><fmt:message key="label.signsNotAllowed"/></p>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${incorrectPhoneNumber == true}">
                <p class="text-center" style="color: red"><fmt:message key="label.incorrectPhoneNumber"/></p>
            </c:when>
        </c:choose>
        <div class="form-group">
            <label><fmt:message key="label.enterUsername"/></label>
            <input type="text" name="username" placeholder="<fmt:message key="label.enterUsername"/>" class="form-control" id="username" maxlength="20">
            <p><fmt:message key="label.checkUsername"/><span id="message" style="font-size: 15px; color: red;"></span></p>
            <p>(V - <fmt:message key="label.free"/> , X - <fmt:message key="label.used"/>)</p>
        </div>
        <div class="form-group">
            <label><fmt:message key="label.password"/></label>
            <input type="password" name="password" placeholder="<fmt:message key="label.password"/>" class="form-control" maxlength="20">
        </div>
        <div class="form-group">
            <label><fmt:message key="label.repeatPassword"/></label>
            <input type="password" name="passwordRepeat" placeholder="<fmt:message key="label.repeatPassword"/>" class="form-control" maxlength="20">
        </div>
        <div class="form-group">
            <label><fmt:message key="label.firstName"/> </label>
            <input type="text" name="firstName" placeholder="<fmt:message key="label.firstName"/>" class="form-control" maxlength="20">
        </div>
        <div class="form-group">
            <label><fmt:message key="label.secondName"/> </label>
            <input type="text" name="secondName" placeholder="<fmt:message key="label.secondName"/>" class="form-control" maxlength="20">
        </div>
        <div class="form-group">
            <label><fmt:message key="label.phoneNumber"/></label>
            <input type="text" name="phoneNumber" placeholder="<fmt:message key="label.phoneNumber"/>" class="form-control" maxlength="12">
        </div>
        <input type="submit" value="<fmt:message key="label.registration"/>" class="btn btn-success">
    </form>

    <footer class="container-fluid text-center bg-dark text-light" >
        <h5><fmt:message key="label.footer"/></h5>
    </footer>

    <script>
        $(document).ready(function() {
            $("#username").change(function() {
                var username = $("#username").val();
                console.log(username);
                $.ajax({
                    type: 'POST',
                    url: "/checkUsername",
                    data: { username : username },
                    success : function (results) {
                        $('#message').text(results);
                    }
                });
            });
        });
    </script>

    <!--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>

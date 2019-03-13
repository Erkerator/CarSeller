<%--
  Created by IntelliJ IDEA.
  User: erkerator
  Date: 14.02.19
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Welcome page</title>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>

    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <a href="/welcome" class="navbar-brand">Carseller</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <c:choose>
                    <c:when test="${sessionScope.username != null}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    ${sessionScope.username}
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <a class="dropdown-item" href="/account">личный кабинет</a>
                                <a class="dropdown-item" href="/logout">выйти</a>
                            </div>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a href="/login" class="nav-link"><fmt:message key="label.login"/></a>
                        </li>
                        <li class="nav-item">
                            <a href="/registration" class="nav-link"><fmt:message key="label.registration"/></a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="label.lang"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="/cardetails?sessionLocale=en">ENG</a>
                        <a class="dropdown-item" href="/cardetails?sessionLocale=ru">РУС</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>

    <h3 class="text-center">Car Details</h3>

    <div class="container">
        <img src="${pageContext.request.contextPath}/ImageHandler?id=${carData.carId}" class="d-block w-50 mx-auto">

        <div class="w-60">
            <ul class="list-group">
                <li class="list-group-item"><fmt:message key="label.model"/> ${carData.modelId}</li>
                <li class="list-group-item"><fmt:message key="label.transmission"/> ${carData.transmissionId}</li>
                <li class="list-group-item"><fmt:message key="label.category"/> ${carData.categoryId}</li>
                <li class="list-group-item"><fmt:message key="label.state"/> ${carData.stateId}</li>
                <li class="list-group-item"><fmt:message key="label.dateOfProduce"/> ${carData.yearOfProduce}</li>
                <li class="list-group-item"><fmt:message key="label.dateOfCreation"/> ${carData.dateOfCreation}</li>
                <li class="list-group-item"><fmt:message key="label.engineVolume"/> ${carData.engineVolume}</li>
                <li class="list-group-item"><fmt:message key="label.userName"/> ${carData.userId}</li>
            </ul>
        </div>
    </div>

    <footer class="container-fluid text-center bg-dark text-light" >
        <h5><fmt:message key="label.footer"/></h5>
    </footer>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>

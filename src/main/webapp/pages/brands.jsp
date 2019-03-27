<%--
  Created by IntelliJ IDEA.
  User: erkerator
  Date: 12.03.19
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <c:choose>
                                <c:when test="${sessionScope.role == 'Admin'}">
                                    <a class="dropdown-item" href="/admin"><fmt:message key="label.adminPanel"/></a>
                                    <a class="dropdown-item" href="/logout"><fmt:message key="label.exit"/></a>
                                </c:when>
                                <c:otherwise>
                                    <a class="dropdown-item" href="/account"><fmt:message key="label.account"/></a>
                                    <a class="dropdown-item" href="/logout"><fmt:message key="label.exit"/></a>
                                </c:otherwise>
                            </c:choose>
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
                    <a class="dropdown-item" href="/admin/brands?sessionLocale=en">ENG</a>
                    <a class="dropdown-item" href="/admin/brands?sessionLocale=ru">РУС</a>
                </div>
            </li>
        </ul>
    </div>
</nav>

<h1 class="text-center"><fmt:message key="label.correctBrand"/></h1>
<c:choose>
    <c:when test="${incorrectData == true}">
        <p class="text-center" style="color: red"><fmt:message key="label.incorrectFields"/></p>
    </c:when>
</c:choose>
<form method="get" action="/admin/addBrand">
    <h4 class="text-center"><fmt:message key="label.addBrandAuto"/></h4>

    <div class="row justify-content-center">
        <div class="col-6">
            <div class="form-group">
                <label><fmt:message key="label.enterBrand"/></label>
                <input type="text" name="newBrand" placeholder="<fmt:message key="label.brandName"/>" class="form-control" required maxlength="20">
            </div>
            <input type="submit" value="<fmt:message key="label.add"/>" class="btn btn-success">
        </div>
    </div>
</form>
<hr/>
<form method="get" action="/admin/correctBrand">
    <h4 class="text-center"><fmt:message key="label.correctingAutoBrand"/></h4>
    <div class="row justify-content-center">
        <div class="col-6">
            <div class="form-group">
                <label><fmt:message key="label.enterBrand"/></label>
                <select name="brandToUpdate" class="form-control" style="margin-top: 10px" required>
                    <option disabled selected value><fmt:message key="label.selectOption"/></option>
                    <c:forEach var="brand" items="${brands}">
                        <option value="${brand.brandId}">${brand.brand}</option>
                    </c:forEach>
                </select>
                <hr/>
                <input type="text" name="changedValue" placeholder="<fmt:message key="label.enterCorrection"/>" class="form-control" required maxlength="20">
            </div>
            <input type="submit" value="<fmt:message key="label.correct"/>" class="btn btn-info">
        </div>
    </div>
</form>
<hr/>
<form method="get" action="/admin/deleteBrand">
    <h4 class="text-center"><fmt:message key="label.deletingAutoBrand"/></h4>
    <div class="row justify-content-center">
        <div class="col-6">
            <div class="form-group">
                <label><fmt:message key="label.chooseBrand"/></label>
                <select name="brandToDelete" class="form-control" required>
                    <option disabled selected value><fmt:message key="label.selectOption"/></option>
                    <c:forEach var="brand" items="${brands}">
                        <option value="${brand.brandId}">${brand.brand}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" value="<fmt:message key="label.delete"/>" class="btn btn-danger">
        </div>
    </div>
</form>

<footer class="container-fluid text-center bg-dark text-light" >
    <h5><fmt:message key="label.footer"/></h5>
</footer>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>

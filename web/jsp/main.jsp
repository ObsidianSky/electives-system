<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="auth" uri="http://electives.ua/taglib/auth" %>
<%@ taglib prefix="n" uri="http://electives.ua/taglib/notification" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<c:set var="user" scope="page" value="${auth:account(pageContext.request)}"/>
<html>
<head>
    <title><fmt:message key="main.page.title"/></title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/vendor/css/bootstrap.min.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

<%@ include file="../WEB-INF/jspf/header.jspf"%>

<div class="content">

    <h1><fmt:message key="main.page.intro.title"/></h1>
    <p><fmt:message key="main.page.intro.body"/></p>

    <h1><fmt:message key="main.page.how_to"/> </h1>
    <div class="blocks">
    <div class="block block-search">
        <h1><fmt:message key="block.login.title"/> </h1>
        <img src="${pageContext.servletContext.contextPath}/images/login.png"/>
        <p><fmt:message key="block.login.text"/> </p>
    </div>

    <div class="block block-order">
        <h1><fmt:message key="block.students.title"/> </h1>
        <img src="${pageContext.servletContext.contextPath}/images/students.png"/>
        <p><fmt:message key="block.students.text"/> </p>
    </div>

    <div class="block block-complete">
        <h1><fmt:message key="block.teachers.title"/> </h1>

        <img src="${pageContext.servletContext.contextPath}/images/teachers.png"/>
        <p><fmt:message key="block.teachers.text"/> </p>
    </div>
    </div>
</div>

<%@ include file="../WEB-INF/jspf/footer.jspf"%>

</body>
</html>
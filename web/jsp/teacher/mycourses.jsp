<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="auth" uri="http://electives.ua/taglib/auth" %>
<%@ taglib prefix="n" uri="http://electives.ua/taglib/notification" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<c:set var="user" scope="page" value="${auth:user(pageContext.request)}"/>

<html>
<head>
    <title><fmt:message key="teacher.courses.title"/></title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/vendor/css/bootstrap.min.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $('.btn-red').click(function(){
                if (confirm ('<fmt:message key="teacher.courses.end_text"/>')){
                    return true;
                }
                return false;
            })
        });
    </script>
</head>
<body>

<%@ include file="../../WEB-INF/jspf/header.jspf"%>

<div class="content">
    <c:if test="${not empty courses}">
        <c:forEach items="${courses}" var="course">
            <div class="course">
                <h3>${course.name}
                    <c:if test="${course.status.id == 2}">
                        <span class="open"><fmt:message key="teacher_table.open"/></span>
                    </c:if>
                </h3>
                <div class="inner">
                    <p><b><fmt:message key="course_table.details"/>:</b> ${course.description}</p>
                    <p><b><fmt:message key="course_table.status"/>:</b> ${course.status.courseStatus}</p>
                    <p><b><fmt:message key="course_table.listeners"/></b> ${course.listeners} / ${course.maxListeners}</p>
                    <c:forEach items="${course.ratingList}" var="rating">
                        <p><b><fmt:message key="teacher.courses.listener"/></b> ${rating.student.name} ${rating.student.surname}</p>
                        <c:choose>
                            <c:when test="${ empty rating.rating}">
                                <a class="btn btn-blue" href="app?c=edit_rating&cid=${course.id}&sid=${rating.student.id}&lang=${locale}"><fmt:message key="teacher.courses.add_rating"/></a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-blue" href="app?c=edit_rating&cid=${course.id}&sid=${rating.student.id}&lang=${locale}"><fmt:message key="teacher.courses.edit_rating"/></a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
                <a class="btn btn-red" href="app?c=end_course&cid=${course.id}&lang=${locale}"><fmt:message key="teacher.courses.end"/></a>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${empty courses}">
        <div class="course">
            <h3><fmt:message key="teacher.courses.no_courses"/></h3>
        </div>
    </c:if>
</div>

<%@ include file="../../WEB-INF/jspf/footer.jspf"%>

</body>
</html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">

        <a href="${pageContext.request.contextPath}/" class="navbar-brand"><spring:message
                code="messages.app.title"/></a>
        <form:form class="navbar-form" action="logout" method="post">
            <sec:authorize access="isAuthenticated()">
                <div class="nav navbar-nav navbar-right">
                    <input type="submit" class="btn btn-primary" value="<spring:message code="app.logout"/>">
                </div>
            </sec:authorize>
        </form:form>
    </div>
</div>

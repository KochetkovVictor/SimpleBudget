<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<sec:authorize access="isAuthenticated()">
    <div id="panel">
        <div id="hidden_panel">
            <ul>
                <li><a href="${pageContext.request.contextPath}/purses"><fmt:message key="purses.title"/></a></li>
                <li><a href="${pageContext.request.contextPath}/incomes"><fmt:message key="incomes.title"/></a></li>
                <li><a href="${pageContext.request.contextPath}/receipts"><fmt:message key="receipts.title"/></a></li>
                <li><a href="${pageContext.request.contextPath}/profile"><fmt:message key="profile.title"/></a></li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="${pageContext.request.contextPath}/users"><fmt:message key="users.title"/></a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</sec:authorize>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header navbar-brand"><spring:message code="messages.app.title"/></div>
        <form:form class="navbar-form" action="logout" method="post">
            <sec:authorize access="isAuthenticated()">
                <div class="nav navbar-nav navbar-right">
                    <input type="submit" class="btn btn-primary" value="<spring:message code="app.logout"/>">
                </div>
            </sec:authorize>
        </form:form>
    </div>
</div>

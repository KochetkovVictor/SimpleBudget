<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/head.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <p/>

        <%--<form method="post" action="users">
            <fmt:message key="messages.app.login"/>: <select name="userId">
            <option value="100000" selected>User</option>
            <option value="100001">Admin</option>
        </select>
            <button type="submit"><fmt:message key="common.select"/></button>
        </form>--%>
        <ul>
            <li><a href="${pageContext.request.contextPath}/purses"><fmt:message key="purses.title"/></a></li>
            <li><a href="${pageContext.request.contextPath}/incomes"><fmt:message key="incomes.title"/></a></li>
            <li><a href="${pageContext.request.contextPath}/receipts"><fmt:message key="receipts.title"/></a></li>
        </ul>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

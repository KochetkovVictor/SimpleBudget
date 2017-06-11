<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/head.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header navbar-brand"><spring:message code="messages.app.title"/></div>
    </div>
</div>

<div class="jumbotron">
    <div class="container">
        <c:if test="${error}">

            <div class="error">
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="message">
                <spring:message code="${message}"/>
            </div>
        </c:if>
        <p>

        <p>User login: <b>che18@yandex.ru / password</b></p>

        <p>Admin login: <b>secretphiz@gmail.com / strongpassword</b></p>

        <p><a class="btn btn-primary btn-lg" role="button" href="register"><spring:message code="app.register"/></a></p>
        <form:form class="form-signin" role="form"
                   action="${pageContext.request.contextPath}/spring_security_check"
                   method="post">
        <div class="row">
            <div class="form-group col-xs-3">
                <input type="text" placeholder="Email or Login" class="form-control" name='username'>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-xs-3">
                <input type="password" placeholder="Password" class="form-control" name='password'>
            </div>
        </div>
        <button type="submit" class="btn btn-success"><spring:message code="app.login"/></button>
    </div>
    </form:form>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">

        <a href="${pageContext.request.contextPath}/" class="navbar-brand"><fmt:message key="messages.app.title"/></a>

        <%--<div class="collapse navbar-collapse">
            <form class="navbar-form navbar-right">
                <a class="btn btn-info" role="button" href="${pageContext.request.contextPath}/purses"><fmt:message key="purses.title"/></a>
                &lt;%&ndash; <a class="btn btn-primary" role="button" href=""><fmt:message key="messages.app.login"/></a>&ndash;%&gt;
            </form>
        </div>--%>
    </div>
</div>

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<jsp:include page="fragments/head.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<h2>${register ? 'Register new' : user.nickName.concat(' profile')}</h2>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <jsp:useBean id="user" class="ru.simplebudget.model.user.User" scope="request"/>

            <form method="post" action="incomes">
                <input type="hidden" name="id" value="${user.id}">
                <dl>
                    <dt>First Name:</dt>
                    <dd><label>
                        <input type="text" value="${user.firstName}" name="firstName">
                    </label></dd>
                </dl>
                <dl>
                    <dt>Last Name:</dt>
                    <dd><label>
                        <input type="text" value="${user.lastName}" name="lastName">
                    </label></dd>
                </dl>
                <dl>
                    <dt>NickName:</dt>
                    <dd><label>
                        <input required type="text" value="${user.nickName}" name="nickName">
                    </label></dd>
                </dl>
                <dl>
                    <dt>Password:</dt>
                    <dd><label>
                        <input required type="password" value="${user.password}" name="password">
                    </label></dd>
                </dl>
                <dl>
                    <dt>Email:</dt>
                    <dd><label>
                        <input required type="email" value="${user.email}" name="email">
                    </label></dd>
                </dl>
                <dl>
                    <dt>Date of birth:</dt>
                    <dd><label>
                        <input required type="text" value="${user.dateOfBirth}" name="dateOfBirth" id="dateOfBirth">
                    </label></dd>
                </dl>
                <button type="submit">Save</button>
                <button onclick="window.history.back()">Cancel</button>
            </form>
        </div>
    </div>
</div>


<jsp:include page="fragments/footer.jsp"/>
</body>

<script type="text/javascript" src="webjars/datetimepicker/2.5.4/build/jquery.datetimepicker.full.min.js"></script>
<script>
    $("#dateOfBirth").datetimepicker({
        format: 'd.m.Y',
        timepicker: false,
        locale: 'ru'
    });
</script>
</html>

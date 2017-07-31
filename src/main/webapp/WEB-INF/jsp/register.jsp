<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags" %>

<html>
<jsp:include page="fragments/head.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<link rel="stylesheet" href="webjars/datetimepicker/2.5.4/jquery.datetimepicker.css">

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <c:set var="userName">${!empty(user.lastName) && !empty(user.firstName) ? user.lastName.concat(" ").concat(user.firstName):user.nickName}</c:set>
            <h2>${register ? 'Register new' : userName.concat(' profile')}</h2>

            <div class="view-box">
                <form:form modelAttribute="user" class="form-horizontal" method="post"
                           action="${register ? 'register' : 'profile'}" charset="utf-8"
                           accept-charset="UTF-8">

                    <myTag:inputField label="First Name:" name="firstName"/>
                    <myTag:inputField label="Last Name:" name="lastName"/>
                    <myTag:inputField label="Date of Birth:" name="dateOfBirth"/>
                    <myTag:inputField label="Email:" name="email"/>
                    <myTag:inputField label="NickName:" name="nickName"/>
                    <myTag:inputField label="Password" name="password" inputType="password"/>

                    <div class="form-group">
                        <div class="col-xs-offset-2 col-xs-10">
                            <button type="submit" class="btn btn-primary">${register ? 'Add' : 'Update'}</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="webjars/datetimepicker/2.5.4/build/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript" src="resources/js/userregister.js"></script>
</html>
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

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h2>${register ? 'Register new' : user.nickName.concat(' profile')}</h2>

            <div class="view-box">
                <form:form modelAttribute="user" class="form-horizontal" method="post"
                           action="${register ? 'register' : 'profile'}" charset="utf-8"
                           accept-charset="UTF-8">

                    <myTag:inputField label="First Name:" name="firstName"/>
                    <myTag:inputField label="Last Name:" name="lastName"/>
                    <myTag:inputField label="Date of Birth:" name="dateOfBirth" id="userDOB"/>
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
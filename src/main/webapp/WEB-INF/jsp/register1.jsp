<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/head.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<link rel="stylesheet" href="webjars/datetimepicker/2.5.4/jquery.datetimepicker.css">
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h2>${register ? 'Register new' : user.nickName.concat(' profile')}</h2>

            <div class="view-box">
                <form:form method="post" class="form-horizontal" role="form" id="register"
                           modelAttribute="user" action="${register ? 'register' : 'profile'}" charset="utf-8"
                           accept-charset="UTF-8">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="userFirstName">First Name:</label>

                        <div class="col-sm-2">
                            <input class="form-control" name="firstName" id="userFirstName">
                        </div>

                        <label class="control-label col-sm-2" for="userLastName">Last Name:</label>

                        <div class="col-sm-2">
                            <input class="form-control" name="lastName" id="userLastName">
                        </div>
                        <label class="control-label col-sm-2" for="userDOB">Date Of Birth:</label>

                        <div class="col-sm-2">
                            <input class="form-control" name="dateOfBirth" id="userDOB">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="userEmail">Email:</label>

                        <div class="col-sm-2">
                            <input class="form-control" name="email" id="userEmail" type="email" required>
                        </div>

                        <label class="control-label col-sm-2" for="userNickName">Nickname:</label>

                        <div class="col-sm-2">
                            <input class="form-control" name="nickName" id="userNickName" required>
                        </div>
                        <label class="control-label col-sm-2" for="userPassword">Password:</label>

                        <div class="col-sm-2">
                            <input class="form-control" name="password" id="userPassword" type="password" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-7">
                            <button type="submit"
                                    class="btn btn-primary pull-right">${register ? 'Register' : 'Update'}</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</body>
<script type="text/javascript" src="webjars/datetimepicker/2.5.4/build/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript" src="resources/js/userregister.js"></script>
</html>



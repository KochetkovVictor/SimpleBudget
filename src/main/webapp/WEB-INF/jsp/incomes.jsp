<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://fn.simplebudget.ru/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include page="fragments/head.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.13/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="webjars/datetimepicker/2.5.4/jquery.datetimepicker.css">
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><spring:message code="messages.app.title"/></h3>

            <div class="view-box">
                <form:form method="post" class="form-horizontal" role="form" id="filter">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="startDate">From Date:</label>

                        <div class="col-sm-2">
                            <input class="form-control" name="startDate" id="startDate">
                        </div>

                        <label class="control-label col-sm-2" for="endDate">To Date:</label>

                        <div class="col-sm-2">
                            <input class="form-control" name="endDate" id="endDate">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-8">
                            <button type="submit" class="btn btn-primary pull-right">Filter</button>
                        </div>
                    </div>
                </form:form>
                <a class="btn btn-sm btn-info" onclick="add()"><spring:message code="income.add"/></a>

                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th>Description</th>
                        <th>Date</th>
                        <th>Amount</th>
                        <th>Purse</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><spring:message code="income.edit"/></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" method="post" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="dateTime" class="control-label col-xs-3">Date</label>

                        <div class="col-xs-9">
                            <input class="form-control" id="dateTime" name="incomeDate" placeholder="Date">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Description</label>

                        <div class="col-xs-9">
                            <input required type="text" class="form-control" id="description" name="description"
                                   placeholder="Description">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="value" class="control-label col-xs-3">Amount</label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="value" name="value">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="purse" class="control-label col-xs-3">Purse</label>
                        <div class="col-xs-9">
                            <select required id="purse" name=purseid>
                                <option selected disabled>Choose a purse</option>
                                <c:forEach items="${purseList}" var="purse">
                                    <option value="${purse.id}">
                                            ${purse.description}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="webjars/datetimepicker/2.5.4/build/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.13/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.4.1/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript" src="resources/js/incomesDatatables.js"></script>
</html>

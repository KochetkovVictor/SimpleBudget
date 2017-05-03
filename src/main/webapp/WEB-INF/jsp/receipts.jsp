<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/head.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.13/css/jquery.dataTables.min.css">
<body onload="init()">
<jsp:include page="fragments/bodyHeader.jsp"/>
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
                <a class="btn btn-sm btn-info" onclick="add()"><spring:message code="receipts.add"/></a>
                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th>Shop</th>
                        <th>Date</th>
                        <th>Value</th>
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
                <h2 class="modal-title"><spring:message code="receipts.edit"/></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" method="post" id="detailsForm" action="autocomplete">
                    <input type="hidden" id="id" name="id">
                    <div class="form-group">
                        <label for="shop" class="control-label col-xs-3">Shop</label>

                        <div class="col-xs-9">
                            <input class="form-control" type="text" id="shop" onkeyup="doCompletion();"
                                   name="editedShop">
                            <table>
                                <tr>
                                    <td id="auto-row" colspan="2">
                                        <table id="complete-table"></table>
                                    </td>
                                </tr>
                            </table>
                                <%--<select required id="shop" name="editedShop">
                                    <option selected disabled>Choose a shop</option>
                                    <c:forEach items="${shopList}" var="shop">
                                        <option value="${shop.id}">
                                                ${shop.name}
                                        </option>
                                    </c:forEach>
                                </select>--%>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="dateTime" class="control-label col-xs-3">Date</label>

                        <div class="col-xs-9">
                            <input class="form-control" id="dateTime" name="receiptDate" placeholder="Date">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="amount" class="control-label col-xs-3">Amount</label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="amount" name="amount">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="purse" class="control-label col-xs-3">Purse</label>

                        <div class="col-xs-9">
                            <select required id="purse" name="editedPurse">
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
<script type="text/javascript" src="resources/js/receiptesDatatables.js"></script>
<script type="text/javascript" src="resources/js/experimental/autocompleteShop.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
</html>

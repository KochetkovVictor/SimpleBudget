<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include page="fragments/head.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.12/css/jquery.dataTables.min.css">
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><spring:message code="purses.title"/></h3>

            <div class="view-box">
                <a class="btn btn-sm btn-info" onclick="add()"><spring:message code="purses.add"/></a>
                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th>Description</th>
                        <th>Amount</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
                <div>
                    <table class="table table-striped display">
                        <tr>
                            <td>Сумма по кошелькам:</td>
                            <td>${totalAmount}</td>
                        </tr>
                    </table>
                </div>
            </div>
            <form action="purses/transfer" method="post">
                <dl>
                    <dt>
                        <label>From Purse:</label>
                        <label>
                            <select required name="fromPurse">
                                <c:forEach items="${purseList}" var="purse">
                                    <option value="${purse.purseId}">
                                            ${purse.description}
                                    </option>
                                </c:forEach>
                            </select>
                        </label>
                        <label>To Purse:</label>
                        <label>
                            <select required name="toPurse">
                                <c:forEach items="${purseList}" var="purse">
                                    <option value="${purse.purseId}">
                                            ${purse.description}
                                    </option>
                                </c:forEach>
                            </select>
                        </label>
                        <label>Amount: </label>
                        <label>
                            <input required name="transferAmount"/>
                        </label>
                    </dt>
                </dl>
                <button type="submit">Transfer</button>
                <button onclick="window.history.back()">Cancel</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><spring:message code="purses.add"/></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" method="post" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Name</label>

                        <div class="col-xs-9">
                            <input class="form-control" id="description" name="description" placeholder="Description">
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

<script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript" src="resources/js/pursesDatatabels.js"></script>
</html>

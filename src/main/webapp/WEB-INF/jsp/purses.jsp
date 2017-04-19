<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include page="fragments/head.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.13/css/jquery.dataTables.min.css">
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
                    <tfoot>
                    <tr>
                        <th>Total:</th>
                        <th colspan="2" style="text-align:right"></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>
<div>
    <form method="post" action="${pageContext.request.contextPath}/ajax/purses/transfer">
        <label> From:
            <select name="from">
                <option disabled selected>Choose a Purse</option>
                <c:forEach items="${purseList}" var="purse">
                    <jsp:useBean id="purse1" class="ru.simplebudget.model.common.Purse" scope="request"/>
                    <option value="${purse.purseId}">
                            ${purse.description}
                    </option>
                </c:forEach>
            </select>
        </label>
        <label> To:
            <select name="to">
                <option disabled selected>Choose a Purse</option>
                <c:forEach items="${purseList}" var="purse">
                    <option value="${purse.purseId}">
                            ${purse.description}
                    </option>
                </c:forEach>
            </select>
        </label>
        <button>Transfer</button>
    </form>
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
                    <label for="id"></label><input <%--type="hidden"--%> id="id" name="id">

                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Name</label>

                        <div class="col-xs-9">
                            <input required class="form-control" id="description" name="description"
                                   placeholder="Description">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="amount" class="control-label col-xs-3">Amount</label>

                        <div class="col-xs-9">
                            <input required type="number" class="form-control" id="amount" name="amount"
                                   placeholder="0.0">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="active" class="control-label col-xs-3">Active</label>

                        <div class="col-xs-9">
                            <input required class="form-control" id="active" name="active" placeholder="true">
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
<script type="text/javascript" src="webjars/datatables/1.10.13/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.4.1/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript" src="resources/js/pursesDatatables.js"></script>

</html>

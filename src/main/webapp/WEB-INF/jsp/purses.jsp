<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<jsp:include page="fragments/head.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.12/css/jquery.dataTables.min.css">
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><fmt:message key="purses.title"/></h3>
            <div class="view-box">
                <a class="btn btn-sm btn-info" id="add"><fmt:message key="purses.add"/></a>

                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th>Description</th>
                        <th>Amount</th>
                        <th>Active</th>
                    </tr>
                    </thead>
                    <c:forEach items="${purseList}" var="purse">
                        <jsp:useBean id="purse" type="ru.simplebudget.model.common.Purse"/>
                        <tr class="${purse.active ? 'enable' : 'disable'}">
                            <td><a href="purses/${purse.purseId}">${purse.description}</a></td>
                            <td>${purse.amount}</td>
                            <td>
                                <label for="${purse.purseId}"></label><input type="checkbox"
                                                                             <c:if test="${purse.active}">checked</c:if> id="${purse.purseId}"/>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
                <div>
                    <table class="table table-striped display">
                        <tr>
                            <td>Сумма по кошелькам: </td>
                            <td>${totalAmount}</td>
                        </tr>
                    </table>
                </div>
            </div>
            <form action="purses" method="post">
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
            <div>

            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><fmt:message key="purses.add"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="description"
                                   placeholder="Desription">
                        </div>
                    </div>

                    <%--<div class="form-group">
                        <label for="amount" class="control-label col-xs-3">Email</label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="amount" name="amount" placeholder="amount">
                        </div>
                    </div>--%>

                    <%--<div class="form-group">
                        <label for="password" class="control-label col-xs-3">Password</label>

                        <div class="col-xs-9">
                            <input type="password" class="form-control" id="password" name="password" placeholder="">
                        </div>
                    </div>--%>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript">
    var ajaxUrl = 'ajax/admin/users/';
    var datatableApi;

    // $(document).ready(function () {
    $(function () {
        datatableApi = $('#datatable').dataTable({
            "bPaginate": true,
            "bInfo": false,
            "aoColumns": [
                {
                    "mData": "description"
                },
                {
                    "mData": "amount"
                },
                {
                    "mData": "active"
                }
            ],
            "aaSorting": [
                [
                    0,
                    "asc"
                ]
            ]
        });
        makeEditable();
    });
</script>
<jsp:include page="fragments/footer.jsp"/>
</html>

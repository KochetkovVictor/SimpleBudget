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
                <a class="btn btn-sm btn-info" id="add"><fmt:message key="receipts.add"/></a>

                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th>Shop</th>
                        <th>Date</th>
                        <th>Amount</th>
                        <th>Purse</th>
                    </tr>
                    </thead>
                    <c:forEach items="${receiptList}" var="receipt">
                        <jsp:useBean id="receipt" scope="page" class="ru.simplebudget.model.out.Receipt"/>
                        <tr>
                            <td><a href="/receipts/${receipt.id}">${receipt.shop.name}</a></td>
                            <td>${receipt.dateTime}</td>
                            <td>${receipt.amount}</td>
                            <td>${receipt.purse.description}</td>
                        </tr>
                    </c:forEach>

                </table>
                <table>
                    <tr>
                        <td>Сумма расходов</td>
                        <td>${totalAmount}</td>
                    </tr>
                </table>
                <a href="${pageContext.request.contextPath}/receipts/add">Add a Receipt</a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><fmt:message key="receipts.add"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="shop" class="control-label col-xs-3">Shop</label>

                        <div class="col-xs-9">
                            <select required name="shop">
                                <option disabled selected>Choose a Shop </option>
                                <c:forEach items="${purseList}" var="purse">
                                    <jsp:useBean id="purse1" class="ru.simplebudget.model.common.Purse" scope="request"/>
                                    <option value="${purse.purseId}">
                                            ${purse.description}
                                    </option>
                                </c:forEach>
                            </select>
                            <%--<input type="text" class="form-control" id="shop" name="shop"
                                   placeholder="Shop">--%>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="date" class="control-label col-xs-3">Date</label>

                        <div class="col-xs-9">
                            <input type="datetime-local" class="form-control" id="date" name="date" placeholder="Date">
                        </div>
                    </div>

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
                    "mData": "Shop"
                },
                {
                    "mData": "Date"
                },
                {
                    "mData": "Amount"
                },
                {
                    "mData": "Purse"
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

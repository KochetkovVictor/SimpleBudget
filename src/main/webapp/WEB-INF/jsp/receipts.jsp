<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://fn.simplebudget.ru/functions"%>

<html>
<jsp:include page="fragments/head.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.12/css/jquery.dataTables.min.css">
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><fmt:message key="receipts.title"/></h3>
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
                            <td><a href="${pageContext.request.contextPath}/receipts/update/${receipt.id}">${receipt.shop.name}</a></td>
                            <td> ${fn:formatDate(receipt.receiptDate)}</td>
                            <td>${receipt.amount}</td>
                            <td>${receipt.purse.description}</td>
                        </tr>
                    </c:forEach>

                </table>
                <table class="table table-striped display">
                    <tr>
                        <td>Сумма расходов: </td>
                        <td>${totalAmount}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
<!--Pop up window for adding  receipt-->
<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><fmt:message key="receipts.add"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <label for="id"></label><input type="text" hidden="hidden" id="id" name="id">
                    <!--Selection of receipts shop-->
                    <div class="form-group">
                        <label for="shop" class="control-label col-xs-3">Shop</label>

                        <div  class="col-xs-9">
                            <label >
                                <select required  name="shop" id="shop">
                                    <option disabled selected>Choose a Shop</option>
                                    <c:forEach items="${shopList}" var="shop">
                                        <jsp:useBean id="shop" class="ru.simplebudget.model.common.Shop"
                                                     scope="request"/>
                                        <option value="${shop.shopId}">
                                            <c:out value="${shop.name}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </label>
                        </div>
                    </div>
                    <!--Receipts date-->
                    <div class="form-group">
                        <label for="date" class="control-label col-xs-3">Date</label>

                        <div class="col-xs-9">
                            <input type="datetime-local" class="form-control" id="date" name="date" placeholder="Date">
                        </div>
                    </div>
                    <!--Receipts amount-->
                    <div class="form-group">
                        <label for="amount" class="control-label col-xs-3">Amount:</label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="amount" name="amount" placeholder="">
                        </div>
                    </div>

                    <!--Put receipt in a purse-->
                    <div class="form-group">
                        <label for="purse" class="control-label col-xs-3">Purse</label>

                        <%--<div class="col-xs-9">
                            <label>--%>
                                <select required name="purse" id="purse">
                                    <option disabled selected>Choose a Purse</option>
                                    <c:forEach items="${purseList}" var="purses">
                                        <jsp:useBean id="purses" class="ru.simplebudget.model.common.Purse"
                                                     scope="request"/>
                                        <option value="${purses.purseId}">
                                                ${purses.description}
                                        </option>
                                    </c:forEach>
                                </select>
                            <%--</label>--%>
                        <%--</div>--%>
                    </div>
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

<script type="text/javascript" src="webjars/datatables/1.10.13/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.4.1/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript">

    var ajaxUrl = '${pageContext.request.contextPath}/receipts';
    var datatableApi;

    // $(document).ready(function () {
    $(function () {
        datatableApi = $('#datatable').dataTable({
            "bPaginate": true,
            "bInfo": false,
            "aoColumns": [
                {
                    "mData": "shop"
                },
                {
                    "mData": "date"
                },
                {
                    "mData": "amount"
                },
                {
                    "mData": "purse"
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

</html>

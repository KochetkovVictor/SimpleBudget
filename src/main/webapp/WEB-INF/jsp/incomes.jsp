<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<jsp:include page="fragments/head.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.12/css/jquery.dataTables.min.css">
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><fmt:message key="incomes.title"/></h3>
            <div class="viewbox">
                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th>Description</th>
                        <th>Date</th>
                        <th>Amount</th>
                        <th>Purse</th>
                    </tr>
                    </thead>
                    <c:forEach items="${incomeList}" var="income">
                        <jsp:useBean id="income" scope="page" class="ru.simplebudget.model.in.Income"/>
                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/incomes/update/${income.incomeId}"> ${income.description}</a>
                            </td>
                            <td>${income.incomeDate}</td>
                            <td>${income.value}</td>
                            <td>${income.purse.description}</td>
                        </tr>
                    </c:forEach>
                </table>
                <table class="table table-striped display">
                    <tr>
                        <td>Сумма поступлений:</td>
                        <td>${totalAmount}</td>
                    </tr>
                </table>
                <a href="${pageContext.request.contextPath}/incomes/add">Add an Income</a>
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

    var ajaxUrl = 'incomes';
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
<jsp:include page="fragments/footer.jsp"/>
</html>

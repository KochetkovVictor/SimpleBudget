<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/head.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <table border="1" cellpadding="8" cellspacing="0">
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
                <td>${income.incomeDateTime}</td>
                <td>${income.value}</td>
                <td>${income.purse.description}</td>
            </tr>
        </c:forEach>
    </table>
    <table border="0">
        <tr>
            <td>Сумма поступлений:</td>
            <td>${totalAmount}</td>
        </tr>
    </table>
    <a href="${pageContext.request.contextPath}/incomes/add">Add an Income</a>
</div>
</body>
<jsp:include page="fragments/footer.jsp"/>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/head.jsp"/>
<body>
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
            <td><a href="income/${income.incomeId}"> ${income.description}</a></td>
            <td>${income.incomeDateTime}</td>
            <td>${income.value}</td>
            <td>${income.purse.description}</td>
        </tr>
    </c:forEach>
    <td/>
    <td>Сумма поступлений</td>
    <td>${totalAmount}</td>
    <td/>
</table>
<a href="/incomes/add">Add an Income</a>
</body>
</html>

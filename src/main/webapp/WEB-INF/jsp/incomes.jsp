<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Simple Budget v.0.1</title>
</head>
<body>
<a href="incomes?action=add">Add Income</a>
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
            <td>${income.description}</td>
            <td>${income.incomeDateTime}</td>
            <td>${income.value}</td>
            <td>${income.purse.description}</td>
        </tr>
    </c:forEach>
    <td>Сумма поступлений</td>
    <td>${totalAmount}</td>
</table>
<!--h3> Add new INCOME</h3>
<!--jsp:useBean id="income" type="ru.simplebudget.model.in.Income"/>
<!--jsp:useBean id="purse" type="ru.simplebudget.model.common.Purse"/>
<form method="post" action="incomes">
    <input type="hidden" name="id" value="${income.incomeId}">
    <dl>
        <dt>
            DateTime:
        </dt>
        <dd><input type="datetime-local" value="${income.incomeDateTime}" name="dateTime"></dd>
    </dl>
    <dl>
        <dt>
            Description:
        </dt>
        <dd><input type="text" value="${income.description}" name="description"></dd>
    </dl>
    <dl>
        <dt>
            Amount:
        </dt>
        <dd><input type="number" value="${income.value}" name="amount"></dd>
    </dl>
    <dl>
        <dt>
            Purse:
        </dt>
        <dd><input type="number" value="${income.purse}" name="purse"></dd>
    </dl>
    <button type="submit">Save</button>
    <button onclick="window.history.back()">Cancel</button>
</form-->
</body>
</html>

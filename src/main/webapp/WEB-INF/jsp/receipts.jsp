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
    <c:forEach items="${receiptList}" var="receipt">
        <jsp:useBean id="receipt" scope="page" class="ru.simplebudget.model.out.Receipt"/>
        <tr>
            <td><a href="income/${receipt.id}">${receipt.id}</a></td>
            <td>${receipt.dateTime}</td>
            <td>${receipt.amount}</td>
            <td>${receipt.purse.description}</td>
        </tr>
    </c:forEach>
    <td/>
    <td>Сумма расходов</td>
    <td>${totalAmount}</td>
    <td/>
</table>
</body>
</html>

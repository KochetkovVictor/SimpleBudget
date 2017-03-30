<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/head.jsp"/>
<body>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Description</th>
        <th>Amount</th>
    </tr>
    </thead>
    <c:forEach items="${purseList}" var="purse">
        <jsp:useBean id="purse" scope="page" type="ru.simplebudget.model.common.Purse"/>
        <tr class="${purse.active ? 'enable' : 'disable'}">
            <td><a href = "purses/${purse.purseId}">${purse.description}</a></td>
            <td>${purse.amount}</td>
        </tr>
    </c:forEach>
    <td>Сумма по кошелькам</td>
    <td>${totalAmount}</td>
</table>

</body>
</html>

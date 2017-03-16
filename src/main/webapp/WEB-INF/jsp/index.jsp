<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<title>Simple Budget</title>
<style>
    .enable {visibility: visible;}
    .disable{visibility: collapse; border: hidden;}
    .hidden {visibility: hidden;}
</style>
<body>
<h2>Simple Budget v.0.1</h2>

<!--link href="color.css" rel="stylesheet" type="text/css"  property="class"-->
<a href="purses?action=add">Add Purse</a>
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
            <td>${purse.description}</td>
            <td>${purse.amount}</td>
        </tr>
    </c:forEach>
    <td class='disable'/>
    <td>${totalAmount}</td>
</table>
</body>
</html>

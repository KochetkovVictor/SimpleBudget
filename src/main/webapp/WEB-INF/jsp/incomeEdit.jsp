<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/head.jsp"/>
<body>
<section>
    <h3><fmt:message key="${income.isNew() ? 'meals.add' : 'meals.edit'}"/></h3>
    <hr>
    <jsp:useBean id="income" type="ru.simplebudget.model.in.Income" scope="request"/>
    <form method="post" action="/incomes">
        <input type="hidden" name="id" value="${income.incomeId}">
        <dl>
            <dt>DateTime:</dt>
            <dd><input type="datetime-local" value="${income.incomeDateTime}" name="dateTime"></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" value="${income.description}" size=40 name="description"></dd>
        </dl>
        <dl>
            <dt>Value:</dt>
            <dd><input type="number" value="${income.value}" name="value"></dd>
        </dl>
        <dl>
            <dt>Purse:</dt>
            <dd><select type="ru.simplebudget.model.common.Purse" value="${income.purse.description}" name="purse"/></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>

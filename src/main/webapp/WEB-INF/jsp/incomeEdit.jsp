<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/head.jsp"/>
<h3>${action}</h3>
<body>
<section>
    <hr>
    <jsp:useBean id="income" type="ru.simplebudget.model.in.Income" scope="request"/>

    <form method="post" action="${pageContext.request.contextPath}/incomes/add">
        <input type="hidden" name="id" value="${income.incomeId}">
        <dl>
            <dt>DateTime:</dt>
            <dd><label>
                <input type="datetime-local" value="${income.incomeDateTime}" name="dateTime">
            </label></dd>
        </dl>
        <dl>
            <dt>Description:</dt>

            <dd><label>
                <input type="text" value="${income.description}" size=40 name="description">
            </label></dd>
        </dl>
        <dl>
            <dt>Value:</dt>
            <dd><label>
                <input type="number" value="${income.value}" name="value">
            </label></dd>
        </dl>
        <dl>
            <dt>Purse:</dt>
            <dd>
                <label>
                    <select required>
                        <c:forEach items="${purseList}" var="purse">
                            <jsp:useBean id="purse" class="ru.simplebudget.model.common.Purse" scope="request"/>
                            <option name="purse">
                                ${purse.description}
                            </option>
                        </c:forEach>
                    </select>
                </label>
            </dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
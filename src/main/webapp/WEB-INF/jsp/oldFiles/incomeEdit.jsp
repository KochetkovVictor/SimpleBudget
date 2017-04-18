<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="../fragments/head.jsp"/>
<h3>${action}</h3>
<body>
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <hr>
    <jsp:useBean id="income" type="ru.simplebudget.model.in.Income" scope="request"/>

    <form method="post" action="incomes">
        <input type="hidden" name="id" value="${income.id}">
        <dl>
            <dt>DateTime:</dt>
            <dd><label>
                <input required type="datetime" value="${income.incomeDate}" name="dateTime" >
            </label></dd>
        </dl>
        <dl>
            <dt>Description:</dt>

            <dd><label>
                <input required type="text" value="${income.description}" size=40 name="description" >
            </label></dd>
        </dl>
        <dl>
            <dt>Value:</dt>
            <dd><label>
                <input required type="number" value="${income.value}" name="value" >
            </label></dd>
        </dl>
        <dl>
            <dt>Purse:</dt>
            <dd>
                <label>
                    <select required name="purse">
                        <option disabled selected>Choose a Purse </option>
                        <c:forEach items="${purseList}" var="purse">
                            <jsp:useBean id="purse1" class="ru.simplebudget.model.common.Purse" scope="request"/>
                            <option value="${purse.purseId}">
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
</div>
</body>
<jsp:include page="../fragments/footer.jsp"/>
</html>

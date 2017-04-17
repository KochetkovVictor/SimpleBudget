<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/head.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <hr>
    <h3>${action}</h3>
    <jsp:useBean id="receipt" type="ru.simplebudget.model.out.Receipt" scope="request"/>

    <form method="post" action="receipts">
        <input type="hidden" name="id" value="${receipt.id}">
        <dl>
            <dt>DateTime:</dt>
            <dd><label>
                <input required type="datetime-local" value="${receipt.receiptDate}" name="dateTime" >
            </label></dd>
        </dl>
        <dl>
            <dt>Shop:</dt>

            <dd><label>
                <select required name="shop" >
                    <c:forEach items="${shopList}" var="shop">
                        <jsp:useBean id="shop" type="ru.simplebudget.model.common.Shop"/>
                        <option  value="${shop.id}">
                                ${shop.name}
                        </option>
                    </c:forEach>
                </select>
            </label></dd>
        </dl>
        <dl>
            <dt>Value:</dt>
            <dd><label>
                <input required type="number" value="${receipt.amount}" name="value" >
            </label></dd>
        </dl>
        <dl>
            <dt>Purse:</dt>
            <dd>
                <label>
                    <select required name="purse">
                        <option selected value="${receipt.purse.id}">${receipt.purse.description}</option>
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
<jsp:include page="fragments/footer.jsp"/>
</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/head.jsp"/>
<h3>${action}</h3>
<body>
<section>
    <hr>
    <jsp:useBean id="receipt" type="ru.simplebudget.model.out.Receipt" scope="request"/>

    <form method="post" action="receipts">
        <input type="hidden" name="id" value="${receipt.id}">
        <dl>
            <dt>DateTime:</dt>
            <dd><label>
                <input type="datetime-local" value="${receipt.dateTime}" name="dateTime">
            </label></dd>
        </dl>
        <dl>
            <dt>Shop:</dt>

            <dd><label>
                <select name="shop">
                    <c:forEach items="${shopList}" var="shop">
                        <jsp:useBean id="shop" type="ru.simplebudget.model.common.Shop"/>
                        <option  value="${shop.shopId}">
                                ${shop.name}
                        </option>
                    </c:forEach>
                </select>
            </label></dd>
        </dl>
        <dl>
            <dt>Value:</dt>
            <dd><label>
                <input type="number" value="${receipt.amount}" name="value">
            </label></dd>
        </dl>
        <dl>
            <dt>Purse:</dt>
            <dd>
                <label>
                    <select required name="purse">
                        <option disabled>Choose a Purse </option>
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
</section>
</body>
<jsp:include page="fragments/footer.jsp"/>
</html>


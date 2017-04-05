<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/head.jsp"/>
<body>
<section>
    <table>
        <tr>
            <td>
                <table border="1" cellpadding="8" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Description</th>
                        <th>Amount</th>
                    </tr>
                    </thead>
                    <c:forEach items="${purseList}" var="purse">
                        <jsp:useBean id="purse" type="ru.simplebudget.model.common.Purse"/>
                        <tr class="${purse.active ? 'enable' : 'disable'}">
                            <td><a href="purses/${purse.purseId}">${purse.description}</a></td>
                            <td>${purse.amount}</td>
                        </tr>
                    </c:forEach>
                    <td>Сумма по кошелькам</td>
                    <td>${totalAmount}</td>
                </table>
            </td>
            <td>
                <form action="purses" method="post">
                    <dl>
                        <dt>
                            <label>From Purse:</label>
                            <label>
                                <select required name="fromPurse">
                                    <c:forEach items="${purseList}" var="purse">
                                        <option value="${purse.purseId}">
                                                ${purse.description}
                                        </option>
                                    </c:forEach>
                                </select>
                            </label>
                            <label>To Purse:</label>
                            <label>
                                <select required name="toPurse">
                                    <c:forEach items="${purseList}" var="purse">
                                        <option value="${purse.purseId}">
                                                ${purse.description}
                                        </option>
                                    </c:forEach>
                                </select>
                            </label>
                            <label>Amount: </label>
                            <label>
                                <input name="transferAmount"/>
                            </label>
                        </dt>
                    </dl>
                    <button type="submit">Transfer</button>
                    <button onclick="window.history.back()">Cancel</button>
                </form>
            </td>
        </tr>
    </table>
</section>
</body>
<jsp:include page="fragments/footer.jsp"/>
</html>

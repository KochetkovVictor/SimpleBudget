<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
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
        <tr class="${purse.active ? 'exceeded' : 'normal'}">
            <td>${purse.description}</td>
            <td>${purse.amount}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

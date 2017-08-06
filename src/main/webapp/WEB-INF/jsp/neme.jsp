<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/head.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <div class="view-box">
                <label>${exception}</label>
            </div>
        </div>
    </div>
</div>
</body>
<jsp:include page="fragments/footer.jsp"/>
</html>

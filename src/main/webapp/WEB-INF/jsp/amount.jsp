<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/head.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
</body>
<jsp:include page="fragments/footer.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><spring:message code="purses.title"/></h3>
            <table class="table table-striped display" id="datatable">
                <thead>
                <tr>
                    <th></th>
                    <th>Сумма по кошелькам</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript" src="webjars/datatables/1.10.13/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.4.1/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript" src="resources/js/pursesamount.js"></script>
</html>

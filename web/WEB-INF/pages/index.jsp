<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css">

    <title>新兴技术项目投资综合评价软件</title>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script>
        $(function () {
            if ("${from}" == "notLogin")
                alert("请先登录");
            else if ("${from}" == "notExpert")
                alert("请以专家身份登录");
            else if ("${from}" == "notInvestor")
                alert("请以投资方身份登录");
            else if ("${from}" == "notAdmin")
                alert("请以管理员身份登录");
        });
    </script>
</head>
<body>
<jsp:include page="header.jsp" />
<hr>
<div id="main-div" class="container">
    <div class="row justify-content-center">
        <c:if test="${not empty sessionScope.username}">
        <div id="menu-div" class="col-3 px-2 py-2">
            <c:if test="${sessionScope.role == 'GUEST'}">
                <jsp:include page="guest/menu.jsp" />
            </c:if>
            <c:if test="${sessionScope.role == 'EXPERT'}">
                <jsp:include page="expert/menu.jsp" />
            </c:if>
            <c:if test="${sessionScope.role == 'INVESTOR'}">
                <jsp:include page="investor/menu.jsp" />
            </c:if>
            <c:if test="${sessionScope.role == 'ADMIN'}">
                <jsp:include page="admin/menu.jsp" />
            </c:if>
        </div>
        </c:if>
        <div id="content-div" class="col-9 px-2 py-2">
            <jsp:include page="introduction.jsp" />
        </div>
    </div>
</div>
<hr>
<footer class="py-3">
    <jsp:include page="copyright.jsp" />
</footer>
</body>
</html>
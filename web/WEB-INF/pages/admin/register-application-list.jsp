<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css">

    <title>注册申请审核</title>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../header.jsp" />
<hr>
<div id="main-div" class="container">
    <div class="row">
        <div id="menu-div" class="col-3 px-2 py-2">
            <jsp:include page="menu.jsp" />
        </div>
        <div id="content-div" class="col-9 px-2 py-2">
            <h1 class="text-center">注册申请审核</h1>
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th>用户名</th>
                    <th>姓名/公司名称</th>
                    <th>申请时间</th>
                    <th>审核时间</th>
                    <th>审核管理员</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${applicationList}" var="application">
                    <tr>
                        <td>${application.username}</td>
                        <td>${application.name}</td>
                        <td>${application.applicationTime}</td>
                        <td>${application.approvalTime}</td>
                        <td>${application.approvalAdmin}</td>
                        <td>
                        <c:choose>
                            <c:when test="${application.approvalTime == null}">
                                <a href="${pageContext.request.contextPath}/admin/register-application/detail/${application.username}"
                                   class="btn btn-primary mx-1">审核</a>
                            </c:when>
                            <c:otherwise>
                                <span>已审核</span>
                            </c:otherwise>
                        </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<hr>
<footer class="py-3">
    <jsp:include page="../copyright.jsp" />
</footer>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css">

    <title>专家信息管理</title>

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
            <h1 class="text-center">专家信息管理</h1>
            <div class="row mb-3">
                <div class="col-3">
                    <a href="${pageContext.request.contextPath}/admin/expert/insert-page" class="btn btn-primary w-100">新增</a>
                </div>
            </div>
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th>姓名</th>
                    <th>机构</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${expertList}" var="expert">
                    <tr>
                        <td>${expert.name}</td>
                        <td>${expert.institution}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/expert/detail/${expert.id}"
                               class="btn btn-primary mx-1">修改</a>
                            <a href="${pageContext.request.contextPath}/admin/expert/delete/${expert.id}"
                               class="btn btn-danger mx-1" onclick="return confirm('确认删除？')">删除</a>
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
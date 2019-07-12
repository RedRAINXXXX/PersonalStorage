<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css">

    <title>项目评价结果及评价报告</title>

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
            <h1 class="text-center">项目评价结果及评价报告</h1>
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th>项目名称</th>
                    <th>投资方</th>
                    <th>平均得分</th>
                    <th>评价报告</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${investList}" var="invest">
                    <tr>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/project/detail/${invest.projectId}">${invest.title}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/investor/detail/${invest.investorId}">${invest.companyName}</a>
                        </td>
                        <td>${invest.score}</td>
                        <td>
                        <c:choose>
                            <c:when test="${reportKeyList.contains(invest.projectId.toString().concat('-').concat(invest.investorId.toString()))}">
                                <a href="${pageContext.request.contextPath}/admin/report/detail?projectId=${invest.projectId}&investorId=${invest.investorId}"
                                   class="btn btn-primary mx-1">查看评价</a>
                                <a href="${pageContext.request.contextPath}/admin/report/download?projectId=${invest.projectId}&investorId=${invest.investorId}"
                                   class="btn btn-success mx-1">下载</a>
                                <a href="${pageContext.request.contextPath}/admin/report/delete-redirect?projectId=${invest.projectId}&investorId=${invest.investorId}"
                                   class="btn btn-danger mx-1" onclick="return confirm('确认删除评价报告？')">删除</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/admin/report/detail?projectId=${invest.projectId}&investorId=${invest.investorId}"
                                   class="btn btn-primary">查看评价/上传报告</a>
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
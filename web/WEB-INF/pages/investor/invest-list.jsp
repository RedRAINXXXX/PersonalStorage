<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css">

    <title>已投资项目</title>

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
            <h1 class="text-center">已投资项目</h1>
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th>项目名称</th>
                    <th>投资日期</th>
                    <th>平均得分</th>
                    <th>评价报告</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${investScoreList}" var="invest">
                    <tr>
                        <td>${invest.title}</td>
                        <td>${investDateMap.get(invest.projectId)}</td>
                        <td>${invest.score}</td>
                        <td>
                        <c:choose>
                            <c:when test="${projectWithReport.contains(invest.projectId)}">
                                <a href="${pageContext.request.contextPath}/investor/download-report/${invest.projectId}"
                                   class="btn btn-success mx-1">下载</a>
                            </c:when>
                            <c:otherwise>
                                <span>无</span>
                            </c:otherwise>
                        </c:choose>
                        </td>
                        <td>
                        <c:choose>
                            <c:when test="${empty invest.score}">
                                <a href="${pageContext.request.contextPath}/investor/invest-page/${invest.projectId}"
                                   class="btn btn-primary mx-1">修改投资</a>
                                <a href="${pageContext.request.contextPath}/investor/give-up/${invest.projectId}"
                                   class="btn btn-danger mx-1" onclick="return confirm('确认放弃投资？')">放弃投资</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/investor/view-invest/${invest.projectId}"
                                   class="btn btn-primary mx-1">查看</a>
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
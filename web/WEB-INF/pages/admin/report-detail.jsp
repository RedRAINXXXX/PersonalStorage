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
    <script src="${pageContext.request.contextPath}/resources/js/form-validation.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/report.js"></script>
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
            <div class="row mb-3">
                <div class="col">
                    <h3>项目名称：${averageScore.title}</h3>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <h3>投资方：${averageScore.companyName}</h3>
                </div>
            </div>
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th>专家姓名</th>
                    <th>专家评分</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${nameScoreMap}" var="nameScore">
                    <tr>
                        <td>${nameScore.key}</td>
                        <td>${nameScore.value}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <hr>
            <form id="report-form" method="post" enctype="multipart/form-data"
                  action="${pageContext.request.contextPath}/admin/report/upload?projectId=${averageScore.projectId}&investorId=${averageScore.investorId}"
                  onsubmit="return reportValidate(this)" novalidate>
                <div class="row">
                    <div class="col">
                        <label for="report-input">上传评价报告</label>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-8">
                        <input id="report-input" class="form-control-file" type="file" name="report"
                               onchange="if (!checkReportFileSize()) alert('评价报告大小不能超过16 MB')">
                    </div>
                    <div class="col-4">
                        <small>不超过16 MB，重复上传将覆盖已上传的评价报告</small>
                    </div>
                </div>
                <c:if test="${not empty report}">
                    <div id="report-div" class="row mb-3">
                        <div class="col-8">已上传的评价报告：${report}</div>
                        <div class="col-2">
                            <a href="${pageContext.request.contextPath}/admin/report/download?projectId=${averageScore.projectId}&investorId=${averageScore.investorId}"
                               class="btn btn-success w-100" target="_blank">下载</a>
                        </div>
                        <div class="col-2">
                            <button class="btn btn-danger w-100" type="button"
                                    onclick="confirmDeleteReport(${averageScore.projectId}, ${averageScore.investorId})">删除</button>
                        </div>
                    </div>
                </c:if>
                <hr>
                <div class="row mb-3 justify-content-center">
                    <div class="col-3">
                        <button type="submit" class="btn btn-primary w-100">确定</button>
                    </div>
                    <div class="col-3">
                        <a class="btn btn-secondary w-100"
                           href="${pageContext.request.contextPath}/admin/report/list">取消</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<hr>
<footer class="py-3">
    <jsp:include page="../copyright.jsp" />
</footer>
</body>
</html>
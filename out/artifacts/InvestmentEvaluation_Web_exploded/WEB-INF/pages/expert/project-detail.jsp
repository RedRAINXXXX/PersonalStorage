<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">

    <title>项目信息</title>

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
            <h1 class="text-center">项目信息</h1>
            <table class="table table-bordered">
                <thead class="thead-light">
                <tr>
                    <th width="25%"></th>
                    <th width="75%"></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>项目名称</td>
                    <td>${project.title}</td>
                </tr>
                <tr>
                    <td>投资类型</td>
                    <td>${project.type}</td>
                </tr>
                <tr>
                    <td>投资总额</td>
                    <td>${project.investment}</td>
                </tr>
                <tr>
                    <td>开始日期</td>
                    <td>${project.beginDate}</td>
                </tr>
                <tr>
                    <td>国家</td>
                    <td>${project.country}</td>
                </tr>
                <tr>
                    <td>省、自治区、直辖市</td>
                    <td>${project.province}</td>
                </tr>
                <tr>
                    <td>城市</td>
                    <td>${project.city}</td>
                </tr>
                <tr>
                    <td>邮政编码</td>
                    <td>${project.zip}</td>
                </tr>
                <tr>
                    <td>项目地点</td>
                    <td>${project.address}</td>
                </tr>
                <tr>
                    <td>项目负责人</td>
                    <td>${project.leader}</td>
                </tr>
                <tr>
                    <td>联系电话</td>
                    <td>${project.tel}</td>
                </tr>
                <tr>
                    <td>电子邮箱</td>
                    <td>${project.email}</td>
                </tr>
                <tr>
                    <td>技术领域</td>
                    <td>${project.fields}</td>
                </tr>
                <tr>
                    <td>技术成熟度阶段</td>
                    <td>${project.techStage}</td>
                </tr>
                <tr>
                    <td>技术领域对应的新兴产业</td>
                    <td>${project.industry}</td>
                </tr>
                <tr>
                    <td>新兴产业成长阶段</td>
                    <td>${project.industryStage}</td>
                </tr>
                <tr>
                    <td>项目简介</td>
                    <td>${project.introduction}</td>
                </tr>
                </tbody>
            </table>
            <div class="row mb-3">
                <div class="col-10">项目成果及产业化前期信息表：${empty industrial ? "无" : industrial}</div>
                <c:if test="${not empty industrial}">
                    <div class="col-2">
                        <a href="${pageContext.request.contextPath}/expert/download-industrial/${project.id}"
                           class="btn btn-success w-100" target="_blank">下载</a>
                    </div>
                </c:if>
            </div>
            <hr>
            <div class="row mb-3 justify-content-center">
                <div class="col-3">
                    <a href="${pageContext.request.contextPath}/expert/evaluate-list"
                       class="btn btn-primary w-100">返回项目列表</a>
                </div>
            </div>
        </div>
    </div>
</div>
<hr>
<footer class="py-3">
    <jsp:include page="../copyright.jsp" />
</footer>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css">

    <title>投资项目查询</title>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/query-project.js"></script>
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
            <h1 class="text-center">投资项目查询</h1>
            <div id="query-div">
                <div class="row mb-3">
                    <div class="col">
                        <label for="title-input">项目名称</label>
                        <input id="title-input" class="form-control" type="text" maxlength="255">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-6">
                        <label for="fields-select">技术领域</label>
                        <select id="fields-select" class="form-control">
                            <option></option>
                            <c:forEach items="${fieldLevel1List}" var="fieldLevel1">
                                <option disabled>-----${fieldLevel1.name}-----</option>
                                <c:forEach items="${childListMapLevel1.get(fieldLevel1.id)}" var="fieldLevel2">
                                    <option>${fieldLevel2.name}</option>
                                </c:forEach>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-6">
                        <label for="techStage-select">技术成熟度所属阶段</label>
                        <select id="techStage-select" class="form-control">
                            <option></option>
                            <option>应用基础研究</option>
                            <option>关键技术研究</option>
                            <option>实验验证</option>
                            <option>中试孵化</option>
                        </select>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-6">
                        <label for="industry-input">技术领域对应的新型产业</label>
                        <input id="industry-input" class="form-control" type="text" maxlength="255">
                    </div>
                    <div class="col-6">
                        <label for="industryStage-select">新兴产业成长阶段</label>
                        <select id="industryStage-select" class="form-control">
                            <option></option>
                            <option>初创</option>
                            <option>成长</option>
                            <option>扩张</option>
                            <option>成熟</option>
                        </select>
                    </div>
                </div>
                <div class="row mb-3 justify-content-center">
                    <div class="col-3">
                        <button type="button" class="btn btn-primary w-100" onclick="queryProject()">查询</button>
                    </div>
                </div>
            </div>
            <table id="project-table" class="table">
                <thead class="thead-light">
                <tr>
                    <th>项目名称</th>
                    <th>项目负责人</th>
                    <th>投资类型</th>
                    <th>投资总额</th>
                    <th>开始日期</th>
                    <th style="display: none;">看不见我吧</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${projectList}" var="project">
                    <tr>
                        <td>${project.title}</td>
                        <td>${project.leader}</td>
                        <td>${project.type}</td>
                        <td>${project.investment}</td>
                        <td>${project.beginDate}</td>
                        <td style="display: none;">
                            <p>${project.fields}</p>
                            <p>${project.techStage}</p>
                            <p>${project.industry}</p>
                            <p>${project.industryStage}</p>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/investor/invest-page/${project.id}"
                               class="btn btn-primary">查看详细信息</a>
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
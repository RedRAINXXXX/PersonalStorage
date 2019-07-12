<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">

    <title>投资项目</title>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/form-validation.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/invest.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/file.js"></script>
    <c:if test="${empty view}">
        <script>
            $(function () { setExitAlert(); });
        </script>
    </c:if>
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
                        <a href="${pageContext.request.contextPath}/investor/download-industrial/${project.id}"
                           class="btn btn-success w-100" target="_blank">下载</a>
                    </div>
                </c:if>
            </div>
            <hr>
            <form id="invest-form" method="post" enctype="multipart/form-data"
                    <c:choose>
                        <c:when test="${empty evidence}">
                              action="${pageContext.request.contextPath}/investor/invest/${project.id}"
                              onsubmit="return investValidate(this, '${project.fields}', '投资成功')"
                        </c:when>
                        <c:otherwise>
                              action="${pageContext.request.contextPath}/investor/update-invest/${project.id}"
                              onsubmit="return investValidate(this, '${project.fields}', '修改投资成功')"
                        </c:otherwise>
                    </c:choose>>
                <c:if test="${empty view}">
                    <div class="row">
                        <div class="col">
                            <label for="evidence-input">上传投资评估要件证据材料</label>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-8">
                            <input id="evidence-input" class="form-control-file" type="file" name="evidence"
                                   onchange="checkFileSize(this, 16, '投资评估要件证据材料')"
                                   <c:if test="${empty evidence}">required</c:if>>
                        </div>
                        <div class="col-4">
                            <small>不超过16 MB，重复上传将覆盖已上传的投资评估要件证据材料</small>
                        </div>
                    </div>
                </c:if>
                <c:if test="${not empty evidence}">
                    <div id="evidence-div" class="row mb-3 justify-content-between">
                        <div class="col-8">已上传的投资评估要件证据材料：${evidence}</div>
                        <div class="col-2">
                            <a href="${pageContext.request.contextPath}/investor/download-evidence/${project.id}"
                               class="btn btn-success w-100" target="_blank">下载</a>
                        </div>
                        <c:if test="${empty view}">
                            <div class="col-2">
                                <button class="btn btn-danger w-100" type="button"
                                        onclick="confirmDeleteEvidence(${project.id})">删除</button>
                            </div>
                        </c:if>
                    </div>
                </c:if>
                <hr>
                <div class="row mb-3 justify-content-center">
                <c:choose>
                    <c:when test="${empty view}">
                        <div class="col-3">
                            <button type="submit" class="btn btn-primary w-100">投资该项目</button>
                        </div>
                        <div class="col-3">
                            <c:choose>
                                <c:when test="${empty evidence}">
                                    <a href="${pageContext.request.contextPath}/investor/project-list"
                                       class="btn btn-secondary w-100">返回项目列表</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/investor/invest-list"
                                       class="btn btn-secondary w-100">返回投资列表</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="col-3">
                            <a href="${pageContext.request.contextPath}/investor/invest-list"
                               class="btn btn-primary w-100">返回投资列表</a>
                        </div>
                    </c:otherwise>
                </c:choose>
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
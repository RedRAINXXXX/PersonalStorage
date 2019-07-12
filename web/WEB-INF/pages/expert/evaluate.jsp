<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css">

    <title>专家综合评价</title>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/form-validation.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/evaluate.js"></script>
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
            <h1 class="text-center">项目评价</h1>
            <div class="row mb-3">
                <div class="col">
                    <h3>项目名称：${project.title}</h3>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <h3>投资方：${investor.companyName}</h3>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-10">项目成果及产业化前期信息表：${empty industrial ? "无" : industrial}</div>
                <c:if test="${not empty industrial}">
                    <div class="col-2">
                        <a href="${pageContext.request.contextPath}/expert/download-industrial/${project.id}"
                           class="btn btn-success w-100" target="_blank">下载</a>
                    </div>
                </c:if>
            </div>
            <div class="row mb-3">
                <div class="col-10">项目投资评估要件证据材料：${empty evidence ? "无" : evidence}</div>
                <c:if test="${not empty evidence}">
                    <div class="col-2">
                        <a href="${pageContext.request.contextPath}/expert/download-evidence?projectId=${project.id}&investorId=${investor.id}"
                           class="btn btn-success w-100" target="_blank">下载</a>
                    </div>
                </c:if>
            </div>
            <form id="evaluate-form" action="${pageContext.request.contextPath}/expert/evaluate?projectId=${project.id}&investorId=${investor.id}"
                  method="post" onsubmit="return confirmSubmitEvaluation(this)" novalidate>
                <table id="evaluate-table" class="table table-bordered">
                    <thead class="thead-light">
                    <tr>
                        <th width="12%">目标层</th>
                        <th width="12%">准则层</th>
                        <th width="15%">指标层</th>
                        <th width="30%">描述</th>
                        <th width="19%">评分标准</th>
                        <th width="12%">评分</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${indexLevel1List}" var="indexLevel1">
                        <c:forEach items="${childListMapLevel1.get(indexLevel1.id)}" var="indexLevel2">
                            <c:forEach items="${childListMapLevel2.get(indexLevel2.id)}" var="indexLevel3">
                                <tr>
                                <c:if test="${indexLevel2.id == childListMapLevel1.get(indexLevel1.id).get(0).id
                                    && indexLevel3.id == childListMapLevel2.get(indexLevel2.id).get(0).id}">
                                    <td rowspan="${childCountMapLevel1.get(indexLevel1.id)}">${indexLevel1.name}</td>
                                </c:if>
                                <c:if test="${indexLevel3.id == childListMapLevel2.get(indexLevel2.id).get(0).id}">
                                    <td rowspan="${childCountMapLevel2.get(indexLevel2.id)}">${indexLevel2.name}</td>
                                </c:if>
                                    <td>${indexLevel3.name} ${indexLevel3.weight}</td>
                                    <td>${indexLevel3.description}</td>
                                <c:if test="${indexLevel3.id == childListMapLevel2.get(indexLevel2.id).get(0).id}">
                                    <td rowspan="${childCountMapLevel2.get(indexLevel2.id)}">
                                        <p style="white-space: pre;">${indexLevel2.standard}</p>
                                    </td>
                                </c:if>
                                    <td>
                                    <c:choose>
                                        <c:when test="${empty view}">
                                            <input class="form-control" type="number" name="${indexLevel3.id}"
                                                   min="0" max="100" required>
                                            <p style="display: none;">${indexLevel3.weight}</p>
                                        </c:when>
                                        <c:otherwise>
                                            <span>${indexScoreMap.getOrDefault(indexLevel3.id, "")}</span>
                                        </c:otherwise>
                                    </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="row mb-3 justify-content-end">
                    <div class="col-3 row">
                        <label for="total-input" class="col-6 col-form-label">总分：</label>
                        <div class="col-6">
                            <input id="total-input" class="form-control-plaintext" type="text"
                                   name="total-score" value="${totalScore}" readonly>
                        </div>
                    </div>
                </div>
                <div class="row mb-3 justify-content-center">
                <c:choose>
                    <c:when test="${empty view}">
                        <div class="col-3">
                            <button type="submit" class="btn btn-primary w-100">确定</button>
                        </div>
                        <div class="col-3">
                            <a href="${pageContext.request.contextPath}/expert/evaluate-list"
                               class="btn btn-secondary w-100">返回项目列表</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="col-3">
                            <a href="${pageContext.request.contextPath}/expert/evaluate-list"
                               class="btn btn-primary w-100">返回项目列表</a>
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">

    <title>项目信息管理</title>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/form-validation.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/project-validation.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/file.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/city-select.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/field-select.js"></script>
    <script>
        $(function () {
            setFormValidation(document.getElementById("project-form"));
            setExitAlert();

            var countrySelect = document.getElementById("country-select");
            var provinceSelect = document.getElementById("province-select");
            var citySelect = document.getElementById("city-select");
            var zipInput = document.getElementById("zip-input");
            setCountryOnchange(countrySelect, provinceSelect, citySelect, zipInput);
            setProvinceOnchange(provinceSelect, citySelect);

            setCountryOptions(countrySelect, "${project.country}");
            if (countrySelect.value == "中国") {
                var provinceIndex = setProvinceOptions(provinceSelect, "${project.province}");
                setCityOptions(citySelect, provinceIndex, "${project.city}");
            }
            else
                setProvinceCityZipDisplay(provinceSelect, citySelect, zipInput, false);

            var fieldsSelect = document.getElementById("fields-select");
            setFieldOptionSelected(fieldsSelect, "${project.fields}");
            $("#fields-select").change(function () {
                $("#fields-input").val(getSelectedFields(fieldsSelect));
            });
            setOptionSelected(document.getElementById("techStage-select"), "${project.techStage}");
            setOptionSelected(document.getElementById("industryStage-select"), "${project.industryStage}");
        });
    </script>
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
            <form id="project-form" method="post" enctype="multipart/form-data"
                    <c:choose>
                        <c:when test="${empty project}">
                            action="${pageContext.request.contextPath}/admin/project/insert"
                            onsubmit="return projectValidate(this, '新增项目成功')"
                        </c:when>
                        <c:otherwise>
                            action="${pageContext.request.contextPath}/admin/project/update/${project.id}"
                            onsubmit="return projectValidate(this, '修改项目信息成功')"
                        </c:otherwise>
                    </c:choose>
                  novalidate>
                <div class="row mb-3">
                    <div class="col">
                        <label for="title-input">项目名称</label>
                        <input id="title-input" class="form-control" type="text" name="title"
                               maxlength="255" value="${project.title}" required>
                        <div class="invalid-feedback">请输入项目名称</div>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-3">
                        <label for="type-input">投资类型</label>
                        <input id="type-input" class="form-control" type="text" name="type"
                               maxlength="255" value="${project.type}">
                    </div>
                    <div class="col-3">
                        <label for="investment-input">投资总额</label>
                        <input id="investment-input" class="form-control" type="text" name="investment"
                               maxlength="255" value="${project.investment}">
                    </div>
                    <div class="col-4">
                        <label for="beginDate-input">开始日期</label>
                        <input id="beginDate-input" class="form-control" type="date" name="beginDate"
                               maxlength="255" value="${project.beginDate}">
                        <div class="invalid-feedback">非法日期</div>
                    </div>
                </div>
                <hr>
                <div class="row mb-3">
                    <div class="col-3">
                        <label for="country-select">国家</label>
                        <select id="country-select" class="form-control" name="country"></select>
                    </div>
                    <div class="col-3">
                        <label for="province-select">省、自治区、直辖市</label>
                        <select id="province-select" class="form-control" name="province"></select>
                    </div>
                    <div class="col-3">
                        <label for="city-select">城市</label>
                        <select id="city-select" class="form-control" name="city"></select>
                    </div>
                    <div class="col-3">
                        <label for="zip-input">邮政编码</label>
                        <input id="zip-input" class="form-control" type="text" name="zip"
                               maxlength="255" value="${project.zip}">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col">
                        <label for="address-input">项目地点</label>
                        <input id="address-input" class="form-control" type="text" name="address"
                               maxlength="255" value="${project.address}">
                    </div>
                </div>
                <hr>
                <div class="row mb-3">
                    <div class="col-4">
                        <label for="leader-input">项目负责人</label>
                        <input id="leader-input" class="form-control" type="text" name="leader"
                               maxlength="255" value="${project.leader}" required>
                        <div class="invalid-feedback">请输入项目负责人</div>
                    </div>
                    <div class="col-4">
                        <label for="tel-input">联系电话</label>
                        <input id="tel-input" class="form-control" type="text" name="tel"
                               maxlength="255" value="${project.tel}">
                    </div>
                    <div class="col-4">
                        <label for="email-input">电子邮箱</label>
                        <input id="email-input" class="form-control" type="email" name="email"
                               maxlength="255" value="${project.email}">
                        <div class="invalid-feedback">邮箱格式错误</div>
                    </div>
                </div>
                <hr>
                <div class="row mb-3">
                    <div class="col">
                        <label for="fields-select">技术领域（至多选择5个）</label>
                        <select id="fields-select" class="form-control" multiple size="10" required>
                            <c:forEach items="${fieldLevel1List}" var="fieldLevel1">
                                <option disabled>-----${fieldLevel1.name}-----</option>
                                <c:forEach items="${childListMapLevel1.get(fieldLevel1.id)}" var="fieldLevel2">
                                    <option>${fieldLevel2.name}</option>
                                </c:forEach>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">至少选择1个领域</div>
                        <input id="fields-input" style="display: none;" type="text" name="fields">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-4">
                        <label for="techStage-select">技术成熟度所属阶段</label>
                        <select id="techStage-select" class="form-control" name="techStage" required>
                            <option>应用基础研究</option>
                            <option>关键技术研究</option>
                            <option>实验验证</option>
                            <option>中试孵化</option>
                        </select>
                        <div class="invalid-feedback">请选择项目技术成熟度所属阶段</div>
                    </div>
                    <div class="col-4">
                        <label for="industry-input">技术领域对应的新型产业</label>
                        <input id="industry-input" class="form-control" type="text" name="industry"
                               maxlength="255" value="${project.industry}" required>
                        <div class="invalid-feedback">请输入项目技术领域对应的新型产业</div>
                    </div>
                    <div class="col-4">
                        <label for="industryStage-select">新兴产业成长阶段</label>
                        <select id="industryStage-select" class="form-control" name="industryStage" required>
                            <option>初创</option>
                            <option>成长</option>
                            <option>扩张</option>
                            <option>成熟</option>
                        </select>
                        <div class="invalid-feedback">请选择新兴产业成长阶段</div>
                    </div>
                </div>
                <hr>
                <div class="row mb-3">
                    <div class="col">
                        <label for="introduction-input">项目简介</label>
                        <textarea id="introduction-input" class="form-control"
                                  rows="8" name="introduction">${project.introduction}</textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="industrial-input">上传项目成果及产业化前期信息表</label>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-8">
                        <input id="industrial-input" class="form-control-file" type="file" name="industrial"
                               onchange="checkFileSize(this, 16, '项目成果及产业化前期信息表')">
                    </div>
                    <div class="col-4">
                        <small>不超过16 MB，重复上传将覆盖已上传的项目成果及产业化前期信息表</small>
                    </div>
                </div>
                <c:if test="${not empty industrial}">
                    <div id="industrial-div" class="row mb-3">
                        <div class="col-8">已上传的项目成果及产业化前期信息表：${industrial}</div>
                        <div class="col-2">
                            <a href="${pageContext.request.contextPath}/admin/project/download-industrial/${project.id}"
                               class="btn btn-success w-100" target="_blank">下载</a>
                        </div>
                        <div class="col-2">
                            <button class="btn btn-danger w-100" type="button"
                                    onclick="confirmDeleteIndustrial(${project.id})">删除</button>
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
                           href="${pageContext.request.contextPath}/admin/project/list">取消</a>
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
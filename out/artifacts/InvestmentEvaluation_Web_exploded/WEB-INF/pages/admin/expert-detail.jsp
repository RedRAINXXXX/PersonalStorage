<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">

    <title>专家信息管理</title>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/form-validation.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/city-select.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/field-select.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/field-validation.js"></script>
    <script>
        $(function () {
            setFormValidation(document.getElementById("expert-form"));
            setExitAlert();

            var countrySelect = document.getElementById("country-select");
            var provinceSelect = document.getElementById("province-select");
            var citySelect = document.getElementById("city-select");
            var zipInput = document.getElementById("zip-input");
            setCountryOnchange(countrySelect, provinceSelect, citySelect, zipInput);
            setProvinceOnchange(provinceSelect, citySelect);

            setCountryOptions(countrySelect, "${expert.country}");
            if (countrySelect.value == "中国") {
                var provinceIndex = setProvinceOptions(provinceSelect, "${expert.province}");
                setCityOptions(citySelect, provinceIndex, "${expert.city}");
            }
            else
                setProvinceCityZipDisplay(provinceSelect, citySelect, zipInput, false);

            var fieldsSelect = document.getElementById("fields-select");
            setFieldOptionSelected(fieldsSelect, "${expert.fields}");
            $("#fields-select").change(function () {
                $("#fields-input").val(getSelectedFields(fieldsSelect));
            });
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
            <form id="expert-form" method="post"
                  <c:choose>
                      <c:when test="${empty expert}">
                          action="${pageContext.request.contextPath}/admin/expert/insert"
                          onsubmit="return fieldValidate(this, '新增专家成功')"
                      </c:when>
                      <c:otherwise>
                          action="${pageContext.request.contextPath}/admin/expert/update/${expert.id}"
                          onsubmit="return fieldValidate(this, '修改专家信息成功')"
                      </c:otherwise>
                  </c:choose>
                  novalidate>
                <div class="row mb-3">
                    <div class="col-3">
                        <label for="name-input">姓名</label>
                        <input id="name-input" class="form-control" type="text" name="name"
                               maxlength="255" value="${expert.name}" required>
                        <div class="invalid-feedback">请输入姓名</div>
                    </div>
                    <div class="col-3">
                        <label>性别</label><br>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input id="sex-male-radio" class="custom-control-input" type="radio" name="sex"
                                   value="男" <c:if test="${expert.sex == '男'}">checked</c:if>>
                            <label for="sex-male-radio" class="custom-control-label">男</label>
                        </div>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input id="sex-female-radio" class="custom-control-input" type="radio" name="sex"
                                   value="女" <c:if test="${expert.sex == '女'}">checked</c:if>>
                            <label for="sex-female-radio" class="custom-control-label">女</label>
                        </div>
                    </div>
                    <div class="col-3">
                        <label for="birthYear-input">出生年份</label>
                        <input id="birthYear-input" class="form-control" type="number" name="birthYear"
                               min="1900" max="9999" value="${expert.birthYear}">
                    </div>
                    <div class="col-3">
                        <label for="people-input">民族</label>
                        <input id="people-input" class="form-control" type="text" name="people"
                               maxlength="255" value="${expert.people}">
                    </div>
                </div>
                <hr>
                <div class="row mb-3">
                    <div class="col-6">
                        <label for="institution-input">所在单位</label>
                        <input id="institution-input" class="form-control" type="text" name="institution"
                               maxlength="255" value="${expert.institution}">
                    </div>
                    <div class="col-6">
                        <label for="title-input">职称</label>
                        <input id="title-input" class="form-control" type="text" name="title"
                               maxlength="255" value="${expert.title}">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col">
                        <label for="duty-input">行政职务</label>
                        <textarea id="duty-input" class="form-control"
                                  rows="5" name="duty" maxlength="2047">${expert.duty}</textarea>
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
                               maxlength="255" value="${expert.zip}">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col">
                        <label for="address-input">通信地址</label>
                        <input id="address-input" class="form-control" type="text" name="address"
                               maxlength="255" value="${expert.address}">
                    </div>
                </div>
                <hr>
                <div class="row mb-3">
                    <div class="col-4">
                        <label for="degree-input">最高学位</label>
                        <input id="degree-input" class="form-control" type="text" name="degree"
                               maxlength="255" value="${expert.degree}">
                    </div>
                    <div class="col-4">
                        <label for="degreeDate-input">授予时间</label>
                        <input id="degreeDate-input" class="form-control" type="text" name="degreeDate"
                               maxlength="255" value="${expert.degreeDate}">
                    </div>
                    <div class="col-4">
                        <label for="university-input">授予学校</label>
                        <input id="university-input" class="form-control" type="text" name="university"
                               maxlength="255" value="${expert.university}">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col">
                        <label for="award-input">荣誉及奖励</label>
                        <textarea id="award-input" class="form-control"
                                  rows="5" name="award" maxlength="1023">${expert.award}</textarea>
                    </div>
                </div>
                <hr>
                <div class="row mb-3">
                    <div class="col-6">
                        <label for="tel-input">联系电话</label>
                        <input id="tel-input" class="form-control" type="text" name="tel"
                               maxlength="255" value="${expert.tel}">
                    </div>
                    <div class="col-6">
                        <label for="email-input">电子邮箱</label>
                        <input id="email-input" class="form-control" type="email" name="email"
                               maxlength="255" value="${expert.email}">
                        <div class="invalid-feedback">邮箱格式错误</div>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col">
                        <label for="fields-select">熟悉的技术领域（至多选择5个）</label>
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
                <div class="row mb-3 justify-content-center">
                    <div class="col-3">
                        <button type="submit" class="btn btn-primary w-100">确定</button>
                    </div>
                    <div class="col-3">
                        <a class="btn btn-secondary w-100"
                           href="${pageContext.request.contextPath}/admin/expert/list">取消</a>
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
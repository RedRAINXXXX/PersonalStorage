<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">

    <title>投资方信息管理</title>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/form-validation.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/city-select.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/field-select.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/field-validation.js"></script>
    <script>
        $(function () {
            setFormValidation(document.getElementById("investor-form"));
            setExitAlert();

            var countrySelect = document.getElementById("country-select");
            var provinceSelect = document.getElementById("province-select");
            var citySelect = document.getElementById("city-select");
            var zipInput = document.getElementById("zip-input");
            setCountryOnchange(countrySelect, provinceSelect, citySelect, zipInput);
            setProvinceOnchange(provinceSelect, citySelect);

            setCountryOptions(countrySelect, "${investor.country}");
            if (countrySelect.value == "中国") {
                var provinceIndex = setProvinceOptions(provinceSelect, "${investor.province}");
                setCityOptions(citySelect, provinceIndex, "${investor.city}");
            }
            else
                setProvinceCityZipDisplay(provinceSelect, citySelect, zipInput, false);

            var fieldsSelect = document.getElementById("fields-select");
            setFieldOptionSelected(fieldsSelect, "${investor.mainBusiness}");
            $("#fields-select").change(function () {
                $("#fields-input").val(getSelectedFields(fieldsSelect));
            });
        })
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
            <form id="investor-form" method="post"
                    <c:choose>
                        <c:when test="${empty investor}">
                            action="${pageContext.request.contextPath}/admin/investor/insert"
                            onsubmit="return fieldValidate(this, '新增投资方成功')"
                        </c:when>
                        <c:otherwise>
                            action="${pageContext.request.contextPath}/admin/investor/update/${investor.id}"
                            onsubmit="return fieldValidate(this, '修改投资方信息成功')"
                        </c:otherwise>
                    </c:choose>
                  novalidate>
                <div class="row mb-3">
                    <div class="col">
                        <label for="companyName-input">公司名称</label>
                        <input id="companyName-input" class="form-control" type="text" name="companyName"
                               maxlength="255" value="${investor.companyName}" required>
                        <div class="invalid-feedback">请输入公司名称</div>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col">
                        <label for="fields-select">主营业务（至多选择5个）</label>
                        <select id="fields-select" class="form-control" multiple size="10" required>
                            <c:forEach items="${fieldLevel1List}" var="fieldLevel1">
                                <option disabled>-----${fieldLevel1.name}-----</option>
                                <c:forEach items="${childListMapLevel1.get(fieldLevel1.id)}" var="fieldLevel2">
                                    <option>${fieldLevel2.name}</option>
                                </c:forEach>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">至少选择1个领域</div>
                        <input id="fields-input" style="display: none;" type="text" name="mainBusiness">
                    </div>
                </div>
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
                               maxlength="255" value="${investor.zip}">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col">
                        <label for="address-input">公司地址</label>
                        <input id="address-input" class="form-control" type="text" name="address"
                               maxlength="255" value="${investor.address}">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-3">
                        <label for="registeredCapital-input">注册资本</label>
                        <input id="registeredCapital-input" class="form-control" type="text" name="registeredCapital"
                               maxlength="255" value="${investor.registeredCapital}">
                    </div>
                    <div class="col-3">
                        <label for="legalRepresentative-input">法定代表人</label>
                        <input id="legalRepresentative-input" class="form-control" type="text" name="legalRepresentative"
                               maxlength="255" value="${investor.legalRepresentative}" required>
                        <div class="invalid-feedback">请输入法定代表人</div>
                    </div>
                    <div class="col-3">
                        <label for="establishmentDate-input">公司成立时间</label>
                        <input id="establishmentDate-input" class="form-control" type="date" name="establishmentDate"
                               maxlength="255" value="${investor.establishmentDate}">
                        <div class="invalid-feedback">非法日期</div>
                    </div>
                    <div class="col-3">
                        <label for="employeeNumber-input">员工人数</label>
                        <input id="employeeNumber-input" class="form-control" type="number" name="employeeNumber"
                               maxlength="255" min="1" max="2147483647" value="${investor.employeeNumber}">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col">
                        <label for="url-input">公司网址</label>
                        <input id="url-input" class="form-control" type="text" name="url"
                               maxlength="255" value="${investor.url}">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-6">
                        <label for="tel-input">联系电话</label>
                        <input id="tel-input" class="form-control" type="text" name="tel"
                               maxlength="255" value="${investor.tel}">
                    </div>
                    <div class="col-6">
                        <label for="email-input">电子邮箱</label>
                        <input id="email-input" class="form-control" type="email" name="email"
                               maxlength="255" value="${investor.email}">
                        <div class="invalid-feedback">邮箱格式错误</div>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col">
                        <label for="introduction-input">投资人简介</label>
                        <textarea id="introduction-input" class="form-control"
                                  rows="8" name="introduction">${investor.introduction}</textarea>
                    </div>
                </div>
                <div class="row mb-3 justify-content-center">
                    <div class="col-3">
                        <button type="submit" class="btn btn-primary w-100">确定</button>
                    </div>
                    <div class="col-3">
                        <a class="btn btn-secondary w-100"
                           href="${pageContext.request.contextPath}/admin/investor/list">取消</a>
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
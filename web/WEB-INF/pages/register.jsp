<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">

    <title>用户注册</title>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/form-validation.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/register.js"></script>
</head>
<body>
<jsp:include page="header.jsp" />
<hr>
<div id="main-div" class="container">
    <div class="mx-auto" style="max-width: 400px;">
        <h1 class="text-center my-3">用户注册</h1>
        <form id="register-form" action="${pageContext.request.contextPath}/register" method="post"
              onsubmit="return registerValidate(this)" novalidate>
            <div class="row mb-3">
                <div class="col">
                    <label for="username-input">用户名</label>
                    <input id="username-input" class="form-control" type="text" name="username"
                           pattern="[0-9A-Za-z_]+" maxlength="255" placeholder="只能包含字母、数字和下划线" required>
                    <div class="invalid-feedback">请输入用户名，并保证格式正确</div>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <label for="password-input">密码</label>
                    <input id="password-input" class="form-control" type="password" name="password" maxlength="255" required>
                    <div class="invalid-feedback">请输入密码</div>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <label for="repeat-password-input">确认密码</label>
                    <input id="repeat-password-input" class="form-control" type="password" maxlength="255" required>
                    <div class="invalid-feedback">请确认密码</div>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <label for="name-input">姓名</label>
                    <input id="name-input" class="form-control" type="text" name="name"
                           maxlength="255" placeholder="投资方用户请输入公司名称" required>
                    <div class="invalid-feedback">请输入姓名</div>
                </div>
            </div>
            <div class="row mb-3 justify-content-center">
                <div class="col">
                    <button type="submit" class="btn btn-primary w-100">注册</button>
                </div>
            </div>
        </form>
    </div>
</div>
<hr>
<footer class="py-3">
    <jsp:include page="copyright.jsp" />
</footer>
</body>
</html>
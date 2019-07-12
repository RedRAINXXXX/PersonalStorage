<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">

    <title>修改密码</title>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/form-validation.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/change-password.js"></script>
</head>
<body>
<jsp:include page="header.jsp" />
<hr>
<div id="main-div" class="container">
    <div class="mx-auto" style="max-width: 400px;">
        <h1 class="text-center my-3">修改密码</h1>
        <form id="password-form" action="${pageContext.request.contextPath}/change-password" method="post"
              onsubmit="return passwordValidate(this)" novalidate>
            <div class="row mb-3">
                <div class="col">
                    <label for="old-password-input">旧密码</label>
                    <input id="old-password-input" class="form-control" type="password" maxlength="255" required>
                    <div class="invalid-feedback">请输入旧密码</div>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <label for="new-password-input">新密码</label>
                    <input id="new-password-input" class="form-control" type="password" name="newPassword" maxlength="255" required>
                    <div class="invalid-feedback">请输入旧密码</div>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <label for="repeat-password-input">确认新密码</label>
                    <input id="repeat-password-input" class="form-control" type="password" maxlength="255" required>
                    <div class="invalid-feedback">请确认新密码</div>
                </div>
            </div>
            <div class="row mb-3 justify-content-center">
                <div class="col">
                    <button type="submit" class="btn btn-primary w-100">确定</button>
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
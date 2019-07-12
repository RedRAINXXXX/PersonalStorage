<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">

    <title>注册申请审核</title>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/form-validation.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/approve.js"></script>
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
            <table class="table">
                <tbody>
                    <tr>
                        <th scope="row">用户名</th>
                        <td>${application.username}</td>
                    </tr>
                    <tr>
                        <th scope="row">姓名/公司名称</th>
                        <td>${application.name}</td>
                    </tr>
                    <tr>
                        <th scope="row">申请时间</th>
                        <td>${application.applicationTime}</td>
                    </tr>
                </tbody>
            </table>
            <form id="approve-form" method="post"
                  action="${pageContext.request.contextPath}/admin/register-application/approve/${application.username}"
                  onsubmit="return approveValidation(this)" novalidate>
                <div class="row mb-3">
                    <div class="col">
                        <label>用户角色</label><br>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input id="role-guest-radio" class="custom-control-input"
                                   type="radio" name="role" value="GUEST" checked>
                            <label for="role-guest-radio" class="custom-control-label">普通用户</label>
                        </div>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input id="role-expert-radio" class="custom-control-input"
                                   type="radio" name="role" value="EXPERT">
                            <label for="role-expert-radio" class="custom-control-label">专家用户</label>
                        </div>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input id="role-investor-radio" class="custom-control-input"
                                   type="radio" name="role" value="INVESTOR">
                            <label for="role-investor-radio" class="custom-control-label">投资方用户</label>
                        </div>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input id="role-admin-radio" class="custom-control-input"
                                   type="radio" name="role" value="ADMIN">
                            <label for="role-admin-radio" class="custom-control-label">管理员</label>
                        </div>
                    </div>
                </div>
                <div class="row mb-3 justify-content-center">
                    <div class="col-3">
                        <button type="submit" class="btn btn-primary w-100">确定</button>
                    </div>
                    <div class="col-3">
                        <a class="btn btn-secondary w-100"
                           href="${pageContext.request.contextPath}/admin/register-application/list">取消</a>
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
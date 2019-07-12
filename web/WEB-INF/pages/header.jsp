<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    contextPath = "${pageContext.request.contextPath}";
</script>
<div class="container">
    <header class="py-3">
        <div class="row flex-nowrap">
            <div class="col-6">
                <a href="${pageContext.request.contextPath}/index" class="navbar-brand text-dark">
                    <h3>新兴技术项目投资综合评价软件</h3>
                </a>
            </div>
            <div class="col-6 d-flex justify-content-end align-items-center">
                <c:choose>
                    <c:when test="${not empty sessionScope.username}">
                        <c:if test="${sessionScope.role == 'EXPERT'}"><span>专家</span></c:if>
                        <c:if test="${sessionScope.role == 'INVESTOR'}"><span>投资方</span></c:if>
                        <c:if test="${sessionScope.role == 'ADMIN'}"><span>管理员</span></c:if>
                        <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">${sessionScope.name}</a>
                        <span>，欢迎！</span>
                        <div class="dropdown-menu">
                            <c:if test="${sessionScope.role == 'EXPERT'}">
                                <a href="${pageContext.request.contextPath}/expert/profile"
                                   class="dropdown-item">个人信息</a>
                            </c:if>
                            <c:if test="${sessionScope.role == 'INVESTOR'}">
                                <a href="${pageContext.request.contextPath}/investor/profile"
                                   class="dropdown-item">公司信息</a>
                            </c:if>
                            <a href="${pageContext.request.contextPath}/change-password-page"
                               class="dropdown-item">修改密码</a>
                            <a href="${pageContext.request.contextPath}/logout"
                               class="dropdown-item" onclick="return confirm('确认注销？')">注销</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/login-page"
                           class="btn btn-outline-secondary mx-1">登录</a>
                        <a href="${pageContext.request.contextPath}/register-page"
                           class="btn btn-outline-secondary mx-1">注册</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </header>
</div>
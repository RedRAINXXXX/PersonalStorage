<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="bg-light">
    <ul class="nav flex-column">
        <li>
            <a class="nav-link" href="${pageContext.request.contextPath}/introduction">系统简介</a>
        </li>
        <li>
            <a class="nav-link" href="${pageContext.request.contextPath}/admin/register-application/list">注册申请审核</a>
        </li>
        <li>
            <a class="nav-link" href="${pageContext.request.contextPath}/admin/expert/list">专家信息管理</a>
        </li>
        <li>
            <a class="nav-link" href="${pageContext.request.contextPath}/admin/project/list">项目信息管理</a>
        </li>
        <li>
            <a class="nav-link" href="${pageContext.request.contextPath}/admin/investor/list">投资方信息管理</a>
        </li>
        <li>
            <a class="nav-link" href="${pageContext.request.contextPath}/admin/report/list">评价报告</a>
        </li>
    </ul>
</nav>
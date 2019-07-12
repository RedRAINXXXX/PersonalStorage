package com.zzy.investeval.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录过滤器，拦截需要登录的请求并重定向到首页<br>
 * 需要登录的请求：
 * <ul>
 * <li>/guest/* -- 需要登录</li>
 * <li>/expert/* -- 需要以专家身份登录</li>
 * <li>/investor/* -- 需要以投资方身份登录</li>
 * <li>/admin/* -- 需要以管理员身份登录</li>
 * </ul>
 *
 * @author 赵正阳
 */
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		String path = httpRequest.getServletPath();
		boolean isLogin = session.getAttribute("username") != null;
		String role = (String) session.getAttribute("role");

		if (path.startsWith("/guest") && !isLogin)
			httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + "/index?from=notLogin");
		else if (path.startsWith("/expert") && (!isLogin || !role.equals("EXPERT")))
			httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + "/index?from=notExpert");
		else if (path.startsWith("/investor") && (!isLogin || !role.equals("INVESTOR")))
			httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + "/index?from=notInvestor");
		else if (path.startsWith("/admin") && (!isLogin || !role.equals("ADMIN")))
			httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + "/index?from=notAdmin");
		else
			chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}

}

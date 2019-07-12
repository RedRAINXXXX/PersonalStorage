package com.zzy.investeval.controller;

import com.zzy.investeval.entity.UserRole;
import com.zzy.investeval.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

/**
 * 注册申请审核模块
 *
 * @author 赵正阳
 */
@Controller
@RequestMapping("/admin/register-application")
public class AdminRegisterApplicationController {
	private final UserService userService;

	@Autowired
	public AdminRegisterApplicationController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		return new ModelAndView("admin/register-application-list")
				.addObject("applicationList", userService.findAllRegisterApplication());
	}

	@RequestMapping("/detail/{username}")
	public ModelAndView detail(@PathVariable("username") String username) {
		return new ModelAndView("admin/register-application-detail")
				.addObject("application", userService.findRegisterApplicationByUsername(username));
	}

	@RequestMapping("/approve/{username}")
	public String approve(HttpSession session, @PathVariable("username") String username, @RequestParam("role") String role) {
		String adminUsername = (String) session.getAttribute("username");
		try {
			UserRole userRole = UserRole.valueOf(role);
			userService.updateRegisterApplication(username, new Timestamp(System.currentTimeMillis()), adminUsername);
			userService.updateRole(username, userRole);
		}
		catch (IllegalArgumentException e) {
			System.err.println("非法用户角色：" + role);
		}
		return "redirect:/admin/register-application/list";
	}

}

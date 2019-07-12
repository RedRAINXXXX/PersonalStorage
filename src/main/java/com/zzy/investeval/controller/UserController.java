package com.zzy.investeval.controller;

import com.zzy.investeval.entity.User;
import com.zzy.investeval.entity.UserRole;
import com.zzy.investeval.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 用户功能模块
 *
 * @author 赵正阳
 */
@Controller
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/register-page")
	public String registerPage() {
		return "register";
	}

	@RequestMapping("/check-username/{username}")
	@ResponseBody
	public String checkUsername(@PathVariable("username") String username) {
		return userService.isUsernameExist(username) ? "error" : "success";
	}

	@PostMapping("/register")
	public String register(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("name") String name) {
		userService.insertRegisterApplication(username, name);
		userService.insertUser(username, password, name);
		return "redirect:/index";
	}

	@RequestMapping("/login-page")
	public String loginPage() {
		return "login";
	}

	@PostMapping("/check-login")
	@ResponseBody
	public String checkLogin(
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		User user = userService.findByUsername(username);
		if (user != null && user.getPassword().equals(password))
			return user.getRole() == UserRole.UNDEFINED ? "unapproved" : "success";
		else
			return "error";
	}

	@PostMapping("/login")
	public String login(
			HttpSession session,
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		User user = userService.findByUsername(username);
		// 前端表单验证保证用户名和密码正确
		assert user != null : "登录：用户名不存在";
		assert user.getPassword().equals(password) : "登录：密码错误";
		session.setAttribute("username", username);
		session.setAttribute("name", user.getName());
		session.setAttribute("role", user.getRole().toString());
		return "redirect:/index";
	}

	@RequestMapping("/change-password-page")
	public String changePasswordPage() {
		return "change-password";
	}

	@PostMapping("/check-password")
	@ResponseBody
	public String checkPassword(HttpSession session, @RequestParam("password") String password) {
		User user = userService.findByUsername((String) session.getAttribute("username"));
		assert user != null : "修改密码：用户名不存在";
		return user.getPassword().equals(password) ? "success" : "error";
	}

	@PostMapping("/change-password")
	public String changePassword(HttpSession session, @RequestParam("newPassword") String newPassword) {
		userService.updatePassword((String) session.getAttribute("username"), newPassword);
		return "redirect:/index";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("username");
		session.removeAttribute("name");
		session.removeAttribute("role");
		return "redirect:/index";
	}

}

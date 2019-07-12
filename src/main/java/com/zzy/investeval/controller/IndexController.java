package com.zzy.investeval.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统首页
 *
 * @author 赵正阳
 */
@Controller
public class IndexController {

	@RequestMapping("/index")
	public ModelAndView index(String from) {
		return new ModelAndView("index")
				.addObject("from", from);
	}

	@RequestMapping("/introduction")
	public String introduction() {
		return "index";
	}

}

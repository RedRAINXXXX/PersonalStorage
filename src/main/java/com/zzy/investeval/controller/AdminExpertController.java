package com.zzy.investeval.controller;

import com.zzy.investeval.service.EvaluateService;
import com.zzy.investeval.service.ExpertService;
import com.zzy.investeval.service.FieldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 专家信息管理模块
 *
 * @author 赵正阳
 */
@Controller
@RequestMapping("/admin/expert")
public class AdminExpertController {
	private final ExpertService expertService;
	private final FieldService fieldService;
	private final EvaluateService evaluateService;

	@Autowired
	public AdminExpertController(
			ExpertService expertService,
			FieldService fieldService,
			EvaluateService evaluateService) {
		this.expertService = expertService;
		this.fieldService = fieldService;
		this.evaluateService = evaluateService;
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		return new ModelAndView("admin/expert-list")
				.addObject("expertList", expertService.findAllBrief());
	}

	@RequestMapping("/detail/{id}")
	public ModelAndView detail(@PathVariable("id") Integer id) {
		return new ModelAndView("admin/expert-detail")
				.addObject("expert", expertService.findById(id))
				.addObject("fieldLevel1List", fieldService.findFieldByLevel(1))
				.addObject("childListMapLevel1", fieldService.findChildFieldListByLevel(1));
	}

	@RequestMapping("/insert-page")
	public ModelAndView insertPage() {
		return new ModelAndView("admin/expert-detail")
				.addObject("fieldLevel1List", fieldService.findFieldByLevel(1))
				.addObject("childListMapLevel1", fieldService.findChildFieldListByLevel(1));
	}

	@PostMapping("/insert")
	public String insert(
			@RequestParam("name") String name,
			@RequestParam(value = "sex", required = false) String sex,
			@RequestParam("birthYear") Integer birthYear,
			@RequestParam("people") String people,
			@RequestParam("institution") String institution,
			@RequestParam("title") String title,
			@RequestParam("duty") String duty,
			@RequestParam("country") String country,
			@RequestParam(value = "province", required = false) String province,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam("zip") String zip,
			@RequestParam("address") String address,
			@RequestParam("degree") String degree,
			@RequestParam("degreeDate") String degreeDate,
			@RequestParam("university") String university,
			@RequestParam("award") String award,
			@RequestParam("tel") String tel,
			@RequestParam("email") String email,
			@RequestParam("fields") String fields) {
		expertService.insert(name, sex, birthYear, people,
				institution, title, duty,
				country, province, city, zip, address,
				degree, degreeDate, university,
				award, tel, email, fields);
		return "redirect:/admin/expert/list";
	}

	@PostMapping("/update/{id}")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam("name") String name,
			@RequestParam(value = "sex", required = false) String sex,
			@RequestParam("birthYear") Integer birthYear,
			@RequestParam("people") String people,
			@RequestParam("institution") String institution,
			@RequestParam("title") String title,
			@RequestParam("duty") String duty,
			@RequestParam("country") String country,
			@RequestParam(value = "province", required = false) String province,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam("zip") String zip,
			@RequestParam("address") String address,
			@RequestParam("degree") String degree,
			@RequestParam("degreeDate") String degreeDate,
			@RequestParam("university") String university,
			@RequestParam("award") String award,
			@RequestParam("tel") String tel,
			@RequestParam("email") String email,
			@RequestParam("fields") String fields) {
		expertService.updateById(id, name, sex, birthYear, people,
				institution, title, duty,
				country, province, city, zip, address,
				degree, degreeDate, university,
				award, tel, email, fields);
		return "redirect:/admin/expert/list";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		expertService.delete(id);
		evaluateService.deleteIndexScoreByExpert(id);
		evaluateService.deleteInvestScoreByExpert(id);
		return "redirect:/admin/expert/list";
	}

}

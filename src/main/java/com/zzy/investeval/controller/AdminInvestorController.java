package com.zzy.investeval.controller;

import com.zzy.investeval.service.EvaluateService;
import com.zzy.investeval.service.FieldService;
import com.zzy.investeval.service.InvestService;
import com.zzy.investeval.service.InvestorService;
import com.zzy.investeval.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

/**
 * 投资方信息管理模块
 *
 * @author 赵正阳
 */
@Controller
@RequestMapping("/admin/investor")
public class AdminInvestorController {
	private final InvestorService investorService;
	private final FieldService fieldService;
	private final InvestService investService;
	private final EvaluateService evaluateService;
	private final ReportService reportService;

	@Autowired
	public AdminInvestorController(
			InvestorService investorService,
			FieldService fieldService,
			InvestService investService,
			EvaluateService evaluateService,
			ReportService reportService) {
		this.investorService = investorService;
		this.fieldService = fieldService;
		this.investService = investService;
		this.evaluateService = evaluateService;
		this.reportService = reportService;
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		return new ModelAndView("admin/investor-list")
				.addObject("investorList", investorService.findAllBrief());
	}

	@RequestMapping("/detail/{id}")
	public ModelAndView detail(@PathVariable("id") Integer id) {
		return new ModelAndView("admin/investor-detail")
				.addObject("investor", investorService.findById(id))
				.addObject("fieldLevel1List", fieldService.findFieldByLevel(1))
				.addObject("childListMapLevel1", fieldService.findChildFieldListByLevel(1));
	}

	@RequestMapping("/insert-page")
	public ModelAndView insertPage() {
		return new ModelAndView("admin/investor-detail")
				.addObject("fieldLevel1List", fieldService.findFieldByLevel(1))
				.addObject("childListMapLevel1", fieldService.findChildFieldListByLevel(1));
	}

	@PostMapping("/insert")
	public String insert(
			@RequestParam("companyName") String companyName,
			@RequestParam("mainBusiness") String mainBusiness,
			@RequestParam("country") String country,
			@RequestParam(value = "province", required = false) String province,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam("address") String address,
			@RequestParam("zip") String zip,
			@RequestParam("registeredCapital") String registeredCapital,
			@RequestParam("legalRepresentative") String legalRepresentative,
			@RequestParam("establishmentDate") String establishmentDate,
			@RequestParam("employeeNumber") Integer employeeNumber,
			@RequestParam("url") String url,
			@RequestParam("tel") String tel,
			@RequestParam("email") String email,
			@RequestParam("introduction") String introduction) {
		Date parsedEstablishmentDate = establishmentDate.isEmpty() ? null : Date.valueOf(establishmentDate);
		investorService.insert(companyName, mainBusiness,
				country, province, city, address, zip,
				registeredCapital, legalRepresentative, parsedEstablishmentDate,
				employeeNumber, url, tel, email, introduction);
		return "redirect:/admin/investor/list";
	}

	@PostMapping("/update/{id}")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam("companyName") String companyName,
			@RequestParam("mainBusiness") String mainBusiness,
			@RequestParam("country") String country,
			@RequestParam(value = "province", required = false) String province,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam("address") String address,
			@RequestParam("zip") String zip,
			@RequestParam("registeredCapital") String registeredCapital,
			@RequestParam("legalRepresentative") String legalRepresentative,
			@RequestParam("establishmentDate") String establishmentDate,
			@RequestParam("employeeNumber") Integer employeeNumber,
			@RequestParam("url") String url,
			@RequestParam("tel") String tel,
			@RequestParam("email") String email,
			@RequestParam("introduction") String introduction) {
		Date parsedEstablishmentDate = establishmentDate.isEmpty() ? null : Date.valueOf(establishmentDate);
		investorService.update(id, companyName, mainBusiness,
				country, province, city, address, zip,
				registeredCapital, legalRepresentative, parsedEstablishmentDate,
				employeeNumber, url, tel, email, introduction);
		return "redirect:/admin/investor/list";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		investorService.delete(id);
		investService.deleteInvestByInvestor(id);
		investService.deleteEvidenceByInvestor(id);
		evaluateService.deleteIndexScoreByInvestor(id);
		evaluateService.deleteInvestScoreByInvestor(id);
		reportService.deleteReportByInvestor(id);
		return "redirect:/admin/investor/list";
	}

}

package com.zzy.investeval.controller;

import com.zzy.investeval.entity.Invest;
import com.zzy.investeval.entity.Project;
import com.zzy.investeval.service.EvaluateService;
import com.zzy.investeval.service.FieldService;
import com.zzy.investeval.service.InvestService;
import com.zzy.investeval.service.InvestorService;
import com.zzy.investeval.service.ProjectService;
import com.zzy.investeval.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

/**
 * 投资方功能模块
 *
 * @author 赵正阳
 */
@Controller
@RequestMapping("/investor")
public class InvestorController {
	private final InvestorService investorService;
	private final ProjectService projectService;
	private final FieldService fieldService;
	private final InvestService investService;
	private final EvaluateService evaluateService;
	private final ReportService reportService;

	@Autowired
	public InvestorController(
			InvestorService investorService,
			ProjectService projectService,
			FieldService fieldService,
			InvestService investService,
			EvaluateService evaluateService,
			ReportService reportService) {
		this.investorService = investorService;
		this.projectService = projectService;
		this.fieldService = fieldService;
		this.investService = investService;
		this.evaluateService = evaluateService;
		this.reportService = reportService;
	}

	@RequestMapping("/profile")
	public ModelAndView profile(HttpSession session) {
		String username = (String) session.getAttribute("username");
		return new ModelAndView("investor/profile")
				.addObject("investor", investorService.findByUsername(username))
				.addObject("fieldLevel1List", fieldService.findFieldByLevel(1))
				.addObject("childListMapLevel1", fieldService.findChildFieldListByLevel(1));
	}

	@PostMapping("/update-profile")
	public String updateProfile(
			HttpSession session,
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
		String username = (String) session.getAttribute("username");
		session.setAttribute("name", companyName);
		Date parsedEstablishmentDate = establishmentDate.isEmpty() ? null : Date.valueOf(establishmentDate);
		investorService.updateByUsername(username, companyName, mainBusiness,
				country, province, city, address, zip,
				registeredCapital, legalRepresentative, parsedEstablishmentDate,
				employeeNumber, url, tel, email, introduction);
		return "redirect:/index";
	}

	@RequestMapping("/project-list")
	public ModelAndView projectList(HttpSession session) {
		String username = (String) session.getAttribute("username");
		Integer investorId = investorService.findByUsername(username).getId();
		List<Integer> investedProjectIdList = investService.findInvestByInvestor(investorId).stream()
				.map(invest -> invest.getKey().getProjectId())
				.collect(Collectors.toList());
		List<Project> projectList = projectService.findAll().stream()
				.filter(project -> !investedProjectIdList.contains(project.getId()))
				.collect(Collectors.toList());
		return new ModelAndView("investor/project-list")
				.addObject("projectList", projectList)
				.addObject("fieldLevel1List", fieldService.findFieldByLevel(1))
				.addObject("childListMapLevel1", fieldService.findChildFieldListByLevel(1));
	}

	@RequestMapping("/invest-page/{id}")
	public ModelAndView investPage(HttpSession session, @PathVariable("id") Integer projectId) {
		String username = (String) session.getAttribute("username");
		Integer investorId = investorService.findByUsername(username).getId();
		return new ModelAndView("investor/invest")
				.addObject("project", projectService.findById(projectId))
				.addObject("industrial", projectService.findIndustrialFilenameById(projectId))
				.addObject("evidence", investService.findEvidenceFilenameById(projectId, investorId));
	}

	@RequestMapping("/view-invest/{id}")
	public ModelAndView viewInvest(HttpSession session, @PathVariable("id") Integer projectId) {
		String username = (String) session.getAttribute("username");
		Integer investorId = investorService.findByUsername(username).getId();
		return new ModelAndView("investor/invest")
				.addObject("view", true)
				.addObject("project", projectService.findById(projectId))
				.addObject("industrial", projectService.findIndustrialFilenameById(projectId))
				.addObject("evidence", investService.findEvidenceFilenameById(projectId, investorId));
	}

	@RequestMapping("/download-industrial/{id}")
	public ResponseEntity<byte[]> downloadIndustrial(@PathVariable("id") Integer id) {
		String filename = projectService.findIndustrialFilenameById(id);
		byte[] content = projectService.readIndustrialContentById(id);
		return ControllerUtils.downloadFile(filename, content);
	}

	@PostMapping("/check-expert")
	@ResponseBody
	public String checkExpert(@RequestParam("fields") String fields) {
		return investService.checkExpertOfFields(fields) ? "success" : "error";
	}

	@PostMapping("/invest/{id}")
	public String invest(
			HttpSession session,
			@PathVariable("id") Integer projectId,
			@RequestParam("evidence") MultipartFile evidence) {
		String username = (String) session.getAttribute("username");
		Integer investorId = investorService.findByUsername(username).getId();
		investService.saveInvest(projectId, investorId);
		uploadEvidence(projectId, investorId, evidence);
		return "redirect:/investor/project-list";
	}

	@RequestMapping("/invest-list")
	public ModelAndView investList(HttpSession session) {
		String username = (String) session.getAttribute("username");
		Integer investorId = investorService.findByUsername(username).getId();
		List<Invest> investList = investService.findInvestByInvestor(investorId);
		Map<Integer, Date> investDateMap = new HashMap<>();
		investList.forEach(invest -> investDateMap.put(invest.getKey().getProjectId(), invest.getInvestDate()));
		List<Integer> projectWithReport = reportService.findAllKeys().stream()
				.filter(key -> key.contains("-") && key.split("-")[1].equals(investorId.toString()))
				.map(key -> Integer.parseInt(key.split("-")[0]))
				.collect(Collectors.toList());
		return new ModelAndView("investor/invest-list")
				.addObject("investScoreList", evaluateService.findInvestScoreByInvestor(investorId))
				.addObject("investDateMap", investDateMap)
				.addObject("projectWithReport", projectWithReport);
	}

	@PostMapping("/update-invest/{id}")
	public String updateInvest(
			HttpSession session,
			@PathVariable("id") Integer projectId,
			@RequestParam("evidence") MultipartFile evidence) {
		String username = (String) session.getAttribute("username");
		Integer investorId = investorService.findByUsername(username).getId();
		uploadEvidence(projectId, investorId, evidence);
		return "redirect:/investor/invest-list";
	}

	@RequestMapping("/give-up/{id}")
	public String giveUp(HttpSession session, @PathVariable("id") Integer projectId) {
		String username = (String) session.getAttribute("username");
		Integer investorId = investorService.findByUsername(username).getId();
		investService.deleteInvest(projectId, investorId);
		investService.deleteEvidence(projectId, investorId);
		// 删除专家分配关联
		evaluateService.deleteIndexScoreByProjectAndInvestor(projectId, investorId);
		// 没有实际作用，若至少一个专家已评分则不可放弃投资
		evaluateService.deleteInvestScoreByProjectAndInvestor(projectId, investorId);
		return "redirect:/investor/invest-list";
	}

	private void uploadEvidence(Integer projectId, Integer investorId, MultipartFile evidence) {
		try {
			investService.saveEvidence(projectId, investorId, evidence);
		}
		catch (IOException e) {
			System.err.println("上传失败" + e.getMessage());
			e.printStackTrace();
		}
	}

	@RequestMapping("/download-evidence/{id}")
	public ResponseEntity<byte[]> downloadEvidence(HttpSession session, @PathVariable("id") Integer projectId) {
		String username = (String) session.getAttribute("username");
		Integer investorId = investorService.findByUsername(username).getId();
		String filename = investService.findEvidenceFilenameById(projectId, investorId);
		byte[] content = investService.readEvidenceContentById(projectId, investorId);
		return ControllerUtils.downloadFile(filename, content);
	}

	@RequestMapping("/delete-evidence/{id}")
	@ResponseBody
	public String deleteEvidence(HttpSession session, @PathVariable("id") Integer projectId) {
		String username = (String) session.getAttribute("username");
		Integer investorId = investorService.findByUsername(username).getId();
		investService.deleteEvidence(projectId, investorId);
		return "success";
	}

	@RequestMapping("/download-report/{id}")
	public ResponseEntity<byte[]> downloadReport(HttpSession session, @PathVariable("id") Integer projectId) {
		String username = (String) session.getAttribute("username");
		Integer investorId = investorService.findByUsername(username).getId();
		String filename = reportService.findReportFilenameById(projectId, investorId);
		byte[] content = reportService.readReportContentById(projectId, investorId);
		return ControllerUtils.downloadFile(filename, content);
	}

}

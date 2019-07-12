package com.zzy.investeval.controller;

import com.zzy.investeval.service.EvaluateService;
import com.zzy.investeval.service.FieldService;
import com.zzy.investeval.service.InvestService;
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

/**
 * 项目信息管理模块
 *
 * @author 赵正阳
 */
@Controller
@RequestMapping("/admin/project")
public class AdminProjectController {
	private final ProjectService projectService;
	private final FieldService fieldService;
	private final InvestService investService;
	private final EvaluateService evaluateService;
	private final ReportService reportService;

	@Autowired
	public AdminProjectController(
			ProjectService projectService,
			FieldService fieldService,
			InvestService investService,
			EvaluateService evaluateService,
			ReportService reportService) {
		this.projectService = projectService;
		this.fieldService = fieldService;
		this.investService = investService;
		this.evaluateService = evaluateService;
		this.reportService = reportService;
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		return new ModelAndView("admin/project-list")
				.addObject("projectList", projectService.findAllBrief());
	}

	@RequestMapping("/detail/{id}")
	public ModelAndView detail(@PathVariable("id") Integer id) {
		return new ModelAndView("admin/project-detail")
				.addObject("project", projectService.findById(id))
				.addObject("fieldLevel1List", fieldService.findFieldByLevel(1))
				.addObject("childListMapLevel1", fieldService.findChildFieldListByLevel(1))
				.addObject("industrial", projectService.findIndustrialFilenameById(id));
	}

	@RequestMapping("/insert-page")
	public ModelAndView insertPage() {
		return new ModelAndView("admin/project-detail")
				.addObject("fieldLevel1List", fieldService.findFieldByLevel(1))
				.addObject("childListMapLevel1", fieldService.findChildFieldListByLevel(1));
	}

	@PostMapping("/insert")
	public String insert(
			@RequestParam("title") String title,
			@RequestParam("type") String type,
			@RequestParam("investment") String investment,
			@RequestParam("beginDate") String beginDate,
			@RequestParam("country") String country,
			@RequestParam(value = "province", required = false) String province,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam("address") String address,
			@RequestParam("zip") String zip,
			@RequestParam("leader") String leader,
			@RequestParam("tel") String tel,
			@RequestParam("email") String email,
			@RequestParam("fields") String fields,
			@RequestParam("techStage") String techStage,
			@RequestParam("industry") String industry,
			@RequestParam("industryStage") String industryStage,
			@RequestParam("introduction") String introduction,
			@RequestParam("industrial") MultipartFile industrial) {
		Date parsedBeginDate = beginDate.isEmpty() ? null : Date.valueOf(beginDate);
		Integer projectId = projectService.insert(title, type, investment, parsedBeginDate,
				country, province, city, address, zip,
				leader, tel, email,
				fields, techStage, industry, industryStage, introduction);
		uploadIndustrial(projectId, industrial);
		return "redirect:/admin/project/list";
	}

	@PostMapping("/update/{id}")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam("title") String title,
			@RequestParam("type") String type,
			@RequestParam("investment") String investment,
			@RequestParam("beginDate") String beginDate,
			@RequestParam("country") String country,
			@RequestParam(value = "province", required = false) String province,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam("address") String address,
			@RequestParam("zip") String zip,
			@RequestParam("leader") String leader,
			@RequestParam("tel") String tel,
			@RequestParam("email") String email,
			@RequestParam("fields") String fields,
			@RequestParam("techStage") String techStage,
			@RequestParam("industry") String industry,
			@RequestParam("industryStage") String industryStage,
			@RequestParam("introduction") String introduction,
			@RequestParam("industrial") MultipartFile industrial) {
		Date parsedBeginDate = beginDate.isEmpty() ? null : Date.valueOf(beginDate);
		projectService.update(id, title, type, investment, parsedBeginDate,
				country, province, city, address, zip,
				leader, tel, email,
				fields, techStage, industry, industryStage, introduction);
		uploadIndustrial(id, industrial);
		return "redirect:/admin/project/list";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		projectService.delete(id);
		investService.deleteInvestByProject(id);
		investService.deleteEvidenceByProject(id);
		evaluateService.deleteIndexScoreByProject(id);
		evaluateService.deleteInvestScoreByProject(id);
		reportService.deleteReportByProject(id);
		return "redirect:/admin/project/list";
	}

	private void uploadIndustrial(Integer id, MultipartFile industrial) {
		try {
			projectService.saveIndustrial(id, industrial);
		}
		catch (IOException e) {
			System.err.println("上传失败" + e.getMessage());
			e.printStackTrace();
		}
	}

	// 直接转发到/expert/download-industrial/{id}会使LoginFilter的逻辑变得复杂
	@RequestMapping("/download-industrial/{id}")
	public ResponseEntity<byte[]> downloadIndustrial(@PathVariable("id") Integer id) {
		String filename = projectService.findIndustrialFilenameById(id);
		byte[] content = projectService.readIndustrialContentById(id);
		return ControllerUtils.downloadFile(filename, content);
	}

	@RequestMapping("/delete-industrial/{id}")
	@ResponseBody
	public String deleteIndustrial(@PathVariable("id") Integer id) {
		projectService.deleteIndustrial(id);
		return "success";
	}

}

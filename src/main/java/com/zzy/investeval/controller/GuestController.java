package com.zzy.investeval.controller;

import com.zzy.investeval.service.EvaluateService;
import com.zzy.investeval.service.ProjectService;
import com.zzy.investeval.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 普通用户功能模块
 *
 * @author 赵正阳
 */
@Controller
@RequestMapping("/guest")
public class GuestController {
	private final ProjectService projectService;
	private final EvaluateService evaluateService;
	private final ReportService reportService;

	@Autowired
	public GuestController(ProjectService projectService, EvaluateService evaluateService, ReportService reportService) {
		this.projectService = projectService;
		this.evaluateService = evaluateService;
		this.reportService = reportService;
	}

	@RequestMapping("/report-list")
	public ModelAndView reportList() {
		return new ModelAndView("guest/report-list")
				.addObject("investList", evaluateService.findAllInvestAverageScore())
				.addObject("reportKeyList", reportService.findAllKeys());
	}

	@RequestMapping("/project-detail/{id}")
	public ModelAndView projectDetail(@PathVariable("id") Integer id) {
		return new ModelAndView("guest/project-detail")
				.addObject("project", projectService.findById(id))
				.addObject("industrial", projectService.findIndustrialFilenameById(id));
	}

	@RequestMapping("/download-industrial/{id}")
	public ResponseEntity<byte[]> downloadIndustrial(@PathVariable("id") Integer id) {
		String filename = projectService.findIndustrialFilenameById(id);
		byte[] content = projectService.readIndustrialContentById(id);
		return ControllerUtils.downloadFile(filename, content);
	}

	@RequestMapping("/download-report")
	public ResponseEntity<byte[]> downloadReport(
			@RequestParam("projectId") Integer projectId,
			@RequestParam("investorId") Integer investorId) {
		String filename = reportService.findReportFilenameById(projectId, investorId);
		byte[] content = reportService.readReportContentById(projectId, investorId);
		return ControllerUtils.downloadFile(filename, content);
	}

}

package com.zzy.investeval.controller;

import com.zzy.investeval.service.EvaluateService;
import com.zzy.investeval.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * 项目评价报告管理模块
 *
 * @author 赵正阳
 */
@Controller
@RequestMapping("/admin/report")
public class AdminReportController {
	private final EvaluateService evaluateService;
	private final ReportService reportService;

	@Autowired
	public AdminReportController(EvaluateService evaluateService, ReportService reportService) {
		this.evaluateService = evaluateService;
		this.reportService = reportService;
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		return new ModelAndView("admin/report-list")
				.addObject("investList", evaluateService.findAllInvestAverageScore())
				.addObject("reportKeyList", reportService.findAllKeys());
	}

	@RequestMapping("/detail")
	public ModelAndView detail(
			@RequestParam("projectId") Integer projectId,
			@RequestParam("investorId") Integer investorId) {
		return new ModelAndView("admin/report-detail")
				.addObject("averageScore", evaluateService.findAverageScoreByProjectAndInvestor(projectId, investorId))
				.addObject("nameScoreMap", evaluateService.findInvestScoreByProjectAndInvestor(projectId, investorId))
				.addObject("report", reportService.findReportFilenameById(projectId, investorId));
	}

	@PostMapping("/upload")
	public String upload(
			@RequestParam("projectId") Integer projectId,
			@RequestParam("investorId") Integer investorId,
			@RequestParam("report") MultipartFile report) {
		try {
			reportService.saveReport(projectId, investorId, report);
		}
		catch (IOException e) {
			System.err.println("上传失败" + e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/admin/report/list";
	}

	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(
			@RequestParam("projectId") Integer projectId,
			@RequestParam("investorId") Integer investorId) {
		String filename = reportService.findReportFilenameById(projectId, investorId);
		byte[] content = reportService.readReportContentById(projectId, investorId);
		return ControllerUtils.downloadFile(filename, content);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(
			@RequestParam("projectId") Integer projectId,
			@RequestParam("investorId") Integer investorId) {
		reportService.deleteReport(projectId, investorId);
		return "success";
	}

	@RequestMapping("/delete-redirect")
	public String deleteRedirect(
			@RequestParam("projectId") Integer projectId,
			@RequestParam("investorId") Integer investorId) {
		reportService.deleteReport(projectId, investorId);
		return "redirect:/admin/report/list";
	}

}

package com.zzy.investeval.controller;

import com.zzy.investeval.entity.EvaluationIndex;
import com.zzy.investeval.service.EvaluateService;
import com.zzy.investeval.service.ExpertService;
import com.zzy.investeval.service.FieldService;
import com.zzy.investeval.service.InvestService;
import com.zzy.investeval.service.InvestorService;
import com.zzy.investeval.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 专家功能模块
 *
 * @author 赵正阳
 */
@Controller
@RequestMapping("/expert")
public class ExpertController {
	private final ExpertService expertService;
	private final ProjectService projectService;
	private final InvestorService investorService;
	private final FieldService fieldService;
	private final EvaluateService evaluateService;
	private final InvestService investService;

	@Autowired
	public ExpertController(
			ExpertService expertService,
			ProjectService projectService,
			InvestorService investorService,
			FieldService fieldService,
			EvaluateService evaluateService,
			InvestService investService) {
		this.expertService = expertService;
		this.projectService = projectService;
		this.investorService = investorService;
		this.fieldService = fieldService;
		this.evaluateService = evaluateService;
		this.investService = investService;
	}

	@RequestMapping("/profile")
	public ModelAndView profile(HttpSession session) {
		String username = (String) session.getAttribute("username");
		return new ModelAndView("expert/profile")
				.addObject("expert", expertService.findByUsername(username))
				.addObject("fieldLevel1List", fieldService.findFieldByLevel(1))
				.addObject("childListMapLevel1", fieldService.findChildFieldListByLevel(1));
	}

	@PostMapping("/update-profile")
	public String updateProfile(
			HttpSession session,
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
		String username = (String) session.getAttribute("username");
		session.setAttribute("name", name);
		expertService.updateByUsername(username, name, sex, birthYear, people,
				institution, title, duty,
				country, province, city, zip, address,
				degree, degreeDate, university,
				award, tel, email, fields);
		return "redirect:/index";
	}

	@RequestMapping("/evaluate-list")
	public ModelAndView evaluateList(HttpSession session) {
		String username = (String) session.getAttribute("username");
		Integer expertId = expertService.findByUsername(username).getId();
		return new ModelAndView("expert/evaluate-list")
				.addObject("investList", evaluateService.findInvestScoreByExpert(expertId));
	}

	@RequestMapping("/project-detail/{id}")
	public ModelAndView projectDetail(@PathVariable("id") Integer id) {
		return new ModelAndView("expert/project-detail")
				.addObject("project", projectService.findById(id))
				.addObject("industrial", projectService.findIndustrialFilenameById(id));
	}

	@RequestMapping("/evaluate-page")
	public ModelAndView evaluatePage(
			@RequestParam("projectId") Integer projectId,
			@RequestParam("investorId") Integer investorId) {
		return new ModelAndView("expert/evaluate")
				.addObject("project", projectService.findById(projectId))
				.addObject("investor", investorService.findById(investorId))
				.addObject("indexLevel1List", evaluateService.findIndexByLevel(1))
				.addObject("childListMapLevel1", evaluateService.findChildIndexListByLevel(1))
				.addObject("childListMapLevel2", evaluateService.findChildIndexListByLevel(2))
				.addObject("childCountMapLevel1", evaluateService.findChildCountOfLevel1())
				.addObject("childCountMapLevel2", evaluateService.findChildCountOfLevel2())
				.addObject("industrial", projectService.findIndustrialFilenameById(projectId))
				.addObject("evidence", investService.findEvidenceFilenameById(projectId, investorId));
	}

	@RequestMapping("/download-industrial/{id}")
	public ResponseEntity<byte[]> downloadIndustrial(@PathVariable("id") Integer id) {
		String filename = projectService.findIndustrialFilenameById(id);
		byte[] content = projectService.readIndustrialContentById(id);
		return ControllerUtils.downloadFile(filename, content);
	}

	@RequestMapping("/download-evidence")
	public ResponseEntity<byte[]> downloadEvidence(
			@RequestParam("projectId") Integer projectId,
			@RequestParam("investorId") Integer investorId) {
		String filename = investService.findEvidenceFilenameById(projectId, investorId);
		byte[] content = investService.readEvidenceContentById(projectId, investorId);
		return ControllerUtils.downloadFile(filename, content);
	}

	@PostMapping("/evaluate")
	public String evaluate(
			HttpSession session,
			HttpServletRequest request,
			@RequestParam("projectId") Integer projectId,
			@RequestParam("investorId") Integer investorId) {
		String username = (String) session.getAttribute("username");
		Integer expertId = expertService.findByUsername(username).getId();
		List<EvaluationIndex> indexList = evaluateService.findIndexByLevel(3);
		Map<Integer, Integer> indexScoreMap = new HashMap<>();
		for (EvaluationIndex index : indexList) {
			String score = request.getParameter(index.getId().toString());
			if (score != null)
				indexScoreMap.put(index.getId(), Integer.valueOf(score));
		}
		evaluateService.saveIndexScore(projectId, investorId, expertId, indexScoreMap);
		evaluateService.saveInvestScore(projectId, investorId, expertId, Double.valueOf(request.getParameter("total-score")));
		return "redirect:/expert/evaluate-list";
	}

	@RequestMapping("/view-evaluate")
	public ModelAndView viewEvaluate(
			HttpSession session,
			@RequestParam("projectId") Integer projectId,
			@RequestParam("investorId") Integer investorId) {
		String username = (String) session.getAttribute("username");
		Integer expertId = expertService.findByUsername(username).getId();
		return new ModelAndView("expert/evaluate")
				.addObject("view", true)
				.addObject("project", projectService.findById(projectId))
				.addObject("investor", investorService.findById(investorId))
				.addObject("indexLevel1List", evaluateService.findIndexByLevel(1))
				.addObject("childListMapLevel1", evaluateService.findChildIndexListByLevel(1))
				.addObject("childListMapLevel2", evaluateService.findChildIndexListByLevel(2))
				.addObject("childCountMapLevel1", evaluateService.findChildCountOfLevel1())
				.addObject("childCountMapLevel2", evaluateService.findChildCountOfLevel2())
				.addObject("industrial", projectService.findIndustrialFilenameById(projectId))
				.addObject("evidence", investService.findEvidenceFilenameById(projectId, investorId))
				.addObject("indexScoreMap", evaluateService.findIndexScoreByProjectAndInvestorAndExpert(projectId, investorId, expertId))
				.addObject("totalScore", evaluateService.findInvestScoreByProjectAndInvestorAndExpert(projectId, investorId, expertId));
	}

}

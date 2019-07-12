package com.zzy.investeval.service;

import com.zzy.investeval.entity.Expert;
import com.zzy.investeval.entity.Invest;
import com.zzy.investeval.entity.InvestScore;
import com.zzy.investeval.entity.Project;
import com.zzy.investeval.entity.key.InvestKey;
import com.zzy.investeval.repository.ExpertRepository;
import com.zzy.investeval.repository.FileRepository;
import com.zzy.investeval.repository.InvestRepository;
import com.zzy.investeval.repository.InvestScoreRepository;
import com.zzy.investeval.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hibernate.internal.util.StringHelper.isEmpty;

/**
 * 投资业务逻辑接口实现类
 *
 * @author 赵正阳
 */
@Service
public class InvestServiceImpl implements InvestService {
	private final InvestRepository investRepository;
	private final FileRepository evidenceFileRepository;
	private final InvestScoreRepository investScoreRepository;
	private final ProjectRepository projectRepository;
	private final ExpertRepository expertRepository;

	@Autowired
	public InvestServiceImpl(
			InvestRepository investRepository,
			@Qualifier("evidenceFileRepository") FileRepository evidenceFileRepository,
			InvestScoreRepository investScoreRepository,
			ProjectRepository projectRepository,
			ExpertRepository expertRepository) {
		this.investRepository = investRepository;
		this.evidenceFileRepository = evidenceFileRepository;
		this.investScoreRepository = investScoreRepository;
		this.projectRepository = projectRepository;
		this.expertRepository = expertRepository;
	}

	@Override
	public List<Invest> findInvestByInvestor(Integer investorId) {
		return investRepository.findByKeyInvestorId(investorId);
	}

	@Override
	public boolean checkExpertOfFields(String fields) {
		return expertRepository.findAll().stream()
				.anyMatch(expert -> !isIntersectionEmpty(fields, expert.getFields()));
	}

	@Override
	public void saveInvest(Integer projectId, Integer investorId) {
		investRepository.save(new Invest(projectId, investorId, new Date(System.currentTimeMillis())));
		// 分配评价专家，依据：技术领域与项目的技术领域交集不为空
		Project project = projectRepository.findById(projectId).orElse(null);
		assert project != null : "保存投资信息：项目id不存在";
		List<Expert> expertList = expertRepository.findAll();
		assert !expertList.isEmpty() : "保存投资信息：项目技术领域的专家不存在";
		Collections.shuffle(expertList);
		expertList.stream()
				.filter(expert -> !isIntersectionEmpty(project.getFields(), expert.getFields()))
				.limit(5)
				.forEach(expert -> investScoreRepository.save(new InvestScore(projectId, investorId, expert.getId())));
	}

	/** 判断两个领域列表交集是否为空 */
	private boolean isIntersectionEmpty(String fields1, String fields2) {
		if (fields1 == null || fields2 == null)
			return true;
		Set<String> fieldSet1 = new HashSet<>(Arrays.asList(fields1.split(",")));
		fieldSet1.retainAll(Arrays.asList(fields2.split(",")));
		return fieldSet1.isEmpty();
	}

	@Override
	public void deleteInvest(Integer projectId, Integer investorId) {
		investRepository.deleteById(new InvestKey(projectId, investorId));
	}

	@Override
	public void deleteInvestByProject(Integer projectId) {
		investRepository.deleteByKeyProjectId(projectId);
	}

	@Override
	public void deleteInvestByInvestor(Integer investorId) {
		investRepository.deleteByKeyInvestorId(investorId);
	}

	@Override
	public String findEvidenceFilenameById(Integer projectId, Integer investorId) {
		return evidenceFileRepository.findFilename(String.format("%d-%d", projectId, investorId));
	}

	@Override
	public byte[] readEvidenceContentById(Integer projectId, Integer investorId) {
		return evidenceFileRepository.readContent(String.format("%d-%d", projectId, investorId));
	}

	@Override
	public void saveEvidence(Integer projectId, Integer investorId, MultipartFile evidence) throws IOException {
		String key = String.format("%d-%d", projectId, investorId);
		if (evidence != null && !isEmpty(evidence.getOriginalFilename())) {
			evidenceFileRepository.delete(key);
			evidenceFileRepository.save(key, evidence.getOriginalFilename(), evidence.getInputStream());
		}
	}

	@Override
	public void deleteEvidence(Integer projectId, Integer investorId) {
		evidenceFileRepository.delete(String.format("%d-%d", projectId, investorId));
	}

	@Override
	public void deleteEvidenceByProject(Integer projectId) {
		evidenceFileRepository.findAllKeys().stream()
				.filter(key -> key.contains("-") && key.split("-")[0].equals(projectId.toString()))
				.forEach(evidenceFileRepository::delete);
	}

	@Override
	public void deleteEvidenceByInvestor(Integer investorId) {
		evidenceFileRepository.findAllKeys().stream()
				.filter(key -> key.contains("-") && key.split("-")[1].equals(investorId.toString()))
				.forEach(evidenceFileRepository::delete);
	}

}

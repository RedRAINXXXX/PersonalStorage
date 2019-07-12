package com.zzy.investeval.service;

import com.zzy.investeval.entity.EvaluationIndex;
import com.zzy.investeval.entity.IndexScore;
import com.zzy.investeval.entity.InvestScore;
import com.zzy.investeval.entity.dto.EvaluationIndexDTO;
import com.zzy.investeval.entity.dto.ExpertDTO;
import com.zzy.investeval.entity.dto.InvestScoreDTO;
import com.zzy.investeval.entity.key.InvestExpertKey;
import com.zzy.investeval.repository.EvaluationIndexRepository;
import com.zzy.investeval.repository.IndexScoreRepository;
import com.zzy.investeval.repository.ExpertRepository;
import com.zzy.investeval.repository.InvestScoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 专家综合评价业务逻辑接口实现类
 *
 * @author 赵正阳
 */
@Service
public class EvaluateServiceImpl implements EvaluateService {
	private final EvaluationIndexRepository evaluationIndexRepository;
	private final IndexScoreRepository indexScoreRepository;
	private final InvestScoreRepository investScoreRepository;
	private final ExpertRepository expertRepository;

	@Autowired
	public EvaluateServiceImpl(
			EvaluationIndexRepository evaluationIndexRepository,
			IndexScoreRepository indexScoreRepository,
			InvestScoreRepository investScoreRepository,
			ExpertRepository expertRepository) {
		this.evaluationIndexRepository = evaluationIndexRepository;
		this.indexScoreRepository = indexScoreRepository;
		this.investScoreRepository = investScoreRepository;
		this.expertRepository = expertRepository;
	}

	@Override
	public List<EvaluationIndex> findIndexByLevel(Integer level) {
		return evaluationIndexRepository.findByLevel(level);
	}

	@Override
	public Map<Integer, Integer> findChildCountOfLevel1() {
		List<EvaluationIndexDTO> indexDTOList = evaluationIndexRepository.findChildCountOfLevel1();
		Map<Integer, Integer> childCountMap = new HashMap<>();
		indexDTOList.forEach(index -> childCountMap.put(index.getId(), index.getChildCount()));
		return childCountMap;
	}

	@Override
	public Map<Integer, Integer> findChildCountOfLevel2() {
		List<EvaluationIndexDTO> indexDTOList = evaluationIndexRepository.findChildCountOfLevel2();
		Map<Integer, Integer> childCountMap = new HashMap<>();
		indexDTOList.forEach(index -> childCountMap.put(index.getId(), index.getChildCount()));
		return childCountMap;
	}

	@Override
	public Map<Integer, List<EvaluationIndex>> findChildIndexListByLevel(Integer level) {
		List<EvaluationIndex> parentIndexList = evaluationIndexRepository.findByLevel(level);
		Map<Integer, List<EvaluationIndex>> childListMap = new HashMap<>();
		parentIndexList.forEach(parentIndex -> childListMap.put(
				parentIndex.getId(), evaluationIndexRepository.findByParentId(parentIndex.getId())));
		return childListMap;
	}

	@Override
	public void saveIndexScore(Integer projectId, Integer investorId, Integer expertId, Map<Integer, Integer> indexScoreMap) {
		List<IndexScore> evaluationList = new ArrayList<>();
		indexScoreMap.forEach((indexId, score) -> evaluationList.add(new IndexScore(projectId, investorId, expertId, indexId, score)));
		indexScoreRepository.saveAll(evaluationList);
	}

	@Override
	public void saveInvestScore(Integer projectId, Integer investorId, Integer expertId, Double score) {
		investScoreRepository.save(new InvestScore(projectId, investorId, expertId, score));
	}

	@Override
	public Map<Integer, Integer> findIndexScoreByProjectAndInvestorAndExpert(Integer projectId, Integer investorId, Integer expertId) {
		List<IndexScore> scoreList = indexScoreRepository.findByKeyProjectIdAndKeyInvestorIdAndKeyExpertId(projectId, investorId, expertId);
		Map<Integer, Integer> indexScoreMap = new HashMap<>();
		scoreList.forEach(score -> indexScoreMap.put(score.getKey().getIndexId(), score.getScore()));
		return indexScoreMap;
	}

	@Override
	public Double findInvestScoreByProjectAndInvestorAndExpert(Integer projectId, Integer investorId, Integer expertId) {
		Optional<InvestScore> investScore = investScoreRepository.findById(new InvestExpertKey(projectId, investorId, expertId));
		return investScore.isPresent() ? investScore.get().getScore() : null;
	}

	@Override
	public List<InvestScoreDTO> findInvestScoreByExpert(Integer expertId) {
		return investScoreRepository.findByExpertId(expertId);
	}

	@Override
	public List<InvestScoreDTO> findInvestScoreByInvestor(Integer investorId) {
		return investScoreRepository.findAverageScoreByInvestorId(investorId);
	}

	@Override
	public InvestScoreDTO findAverageScoreByProjectAndInvestor(Integer projectId, Integer investorId) {
		return investScoreRepository.findAverageScoreByProjectIdAndInvestorId(projectId, investorId);
	}

	@Override
	public Map<String, Double> findInvestScoreByProjectAndInvestor(Integer projectId, Integer investorId) {
		List<InvestScore> scoreList = investScoreRepository.findByKeyProjectIdAndKeyInvestorId(projectId, investorId);
		List<ExpertDTO> expertList = expertRepository.findAllBrief();
		Map<Integer, String> idNameMap = new HashMap<>();
		expertList.forEach(expert -> idNameMap.put(expert.getId(), expert.getName()));

		Map<String, Double> nameScoreMap = new HashMap<>();
		scoreList.forEach(score -> nameScoreMap.put(idNameMap.get(score.getKey().getExpertId()), score.getScore()));
		return nameScoreMap;
	}

	@Override
	public List<InvestScoreDTO> findAllInvestAverageScore() {
		return investScoreRepository.findAllAverageScore();
	}

	@Override
	public void deleteIndexScoreByProject(Integer projectId) {
		indexScoreRepository.deleteByKeyProjectId(projectId);
	}

	@Override
	public void deleteIndexScoreByInvestor(Integer investorId) {
		indexScoreRepository.deleteByKeyInvestorId(investorId);
	}

	@Override
	public void deleteIndexScoreByExpert(Integer expertId) {
		indexScoreRepository.deleteByKeyExpertId(expertId);
	}

	@Override
	public void deleteIndexScoreByProjectAndInvestor(Integer projectId, Integer investorId) {
		indexScoreRepository.deleteByKeyProjectIdAndKeyInvestorId(projectId, investorId);
	}

	@Override
	public void deleteInvestScoreByProject(Integer projectId) {
		investScoreRepository.deleteByKeyProjectId(projectId);
	}

	@Override
	public void deleteInvestScoreByInvestor(Integer investorId) {
		investScoreRepository.deleteByKeyInvestorId(investorId);
	}

	@Override
	public void deleteInvestScoreByExpert(Integer expertId) {
		investScoreRepository.deleteByKeyExpertId(expertId);
	}

	@Override
	public void deleteInvestScoreByProjectAndInvestor(Integer projectId, Integer investorId) {
		investScoreRepository.deleteByKeyProjectIdAndKeyInvestorId(projectId, investorId);
	}

}

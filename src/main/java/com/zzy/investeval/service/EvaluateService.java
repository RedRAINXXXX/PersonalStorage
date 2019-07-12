package com.zzy.investeval.service;

import com.zzy.investeval.entity.EvaluationIndex;
import com.zzy.investeval.entity.dto.InvestScoreDTO;

import java.util.List;
import java.util.Map;

/**
 * 专家综合评价业务逻辑接口
 *
 * @author 赵正阳
 */
public interface EvaluateService {

	/** 根据级别查询指标列表 */
	List<EvaluationIndex> findIndexByLevel(Integer level);

	/** 查询1级指标下的3级指标个数，返回1级指标id到3级指标个数的映射 */
	Map<Integer, Integer> findChildCountOfLevel1();

	/** 查询2级指标下的3级指标个数，返回2级指标id到3级指标个数的映射 */
	Map<Integer, Integer> findChildCountOfLevel2();

	/** 根据级别查询子指标列表，返回该级别指标id到子指标列表的映射 */
	Map<Integer, List<EvaluationIndex>> findChildIndexListByLevel(Integer level);

	/** 保存专家对项目-投资方的指标评分结果 */
	void saveIndexScore(Integer projectId, Integer investorId, Integer expertId, Map<Integer, Integer> indexScoreMap);

	/** 保存专家对项目-投资方的评分结果 */
	void saveInvestScore(Integer projectId, Integer investorId, Integer expertId, Double totalScore);

	/** 查询专家对项目-投资方的指标评分结果，返回指标id到得分的映射 */
	Map<Integer, Integer> findIndexScoreByProjectAndInvestorAndExpert(Integer projectId, Integer investorId, Integer expertId);

	/** 查询专家对项目-投资方的评分结果 */
	Double findInvestScoreByProjectAndInvestorAndExpert(Integer projectId, Integer investorId, Integer expertId);

	/** 查询专家对所有项目-投资方的评分结果 */
	List<InvestScoreDTO> findInvestScoreByExpert(Integer expertId);

	/** 根据投资方id查询所有投资项目的平均得分 */
	List<InvestScoreDTO> findInvestScoreByInvestor(Integer investorId);

	/** 根据项目id和投资方id查询平均得分 */
	InvestScoreDTO findAverageScoreByProjectAndInvestor(Integer projectId, Integer investorId);

	/** 查询所有专家对项目-投资方的评分结果，返回专家姓名到得分的映射 */
	Map<String, Double> findInvestScoreByProjectAndInvestor(Integer projectId, Integer investorId);

	/** 查询所有项目-投资方的平均得分 */
	List<InvestScoreDTO> findAllInvestAverageScore();

	/** 根据项目id删除所有专家的指标评分 */
	void deleteIndexScoreByProject(Integer projectId);

	/** 根据投资方id删除所有专家的指标评分 */
	void deleteIndexScoreByInvestor(Integer investorId);

	/** 根据专家id删除所有指标评分 */
	void deleteIndexScoreByExpert(Integer expertId);

	/** 根据项目id和投资方id删除所有专家的指标评分 */
	void deleteIndexScoreByProjectAndInvestor(Integer projectId, Integer investorId);

	/** 根据项目id删除所有专家的评分 */
	void deleteInvestScoreByProject(Integer projectId);

	/** 根据和投资方id删除所有专家的评分 */
	void deleteInvestScoreByInvestor(Integer investorId);

	/** 根据专家id删除所有评分 */
	void deleteInvestScoreByExpert(Integer expertId);

	/** 根据项目id和投资方id删除所有专家的评分 */
	void deleteInvestScoreByProjectAndInvestor(Integer projectId, Integer investorId);

}

package com.zzy.investeval.service;

import com.zzy.investeval.entity.Invest;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 投资业务逻辑接口
 *
 * @author 赵正阳
 */
public interface InvestService {

	/** 根据投资方id查询投资列表 */
	List<Invest> findInvestByInvestor(Integer investorId);

	/** 检查指定技术领域的专家是否存在 */
	boolean checkExpertOfFields(String fields);

	/** 保存投资信息，建立投资关系并分配评价专家 */
	void saveInvest(Integer projectId, Integer investorId);

	/** 根据项目id和投资方id删除投资关系 */
	void deleteInvest(Integer projectId, Integer investorId);

	/** 根据项目id删除投资关系 */
	void deleteInvestByProject(Integer projectId);

	/** 根据投资方id删除投资关系 */
	void deleteInvestByInvestor(Integer investorId);

	/** 根据项目id和投资方id查询投资评估要件文件名 */
	String findEvidenceFilenameById(Integer projectId, Integer investorId);

	/** 根据项目id和投资方id读取投资评估要件文件内容 */
	byte[] readEvidenceContentById(Integer projectId, Integer investorId);

	/** 上传投资评估要件 */
	void saveEvidence(Integer projectId, Integer investorId, MultipartFile evidence) throws IOException;

	/** 根据项目id和投资方id删除投资评估要件 */
	void deleteEvidence(Integer projectId, Integer investorId);

	/** 根据项目id删除投资评估要件 */
	void deleteEvidenceByProject(Integer projectId);

	/** 根据投资方id删除投资评估要件 */
	void deleteEvidenceByInvestor(Integer investorId);

}

package com.zzy.investeval.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 评价报告业务逻辑接口
 *
 * @author 赵正阳
 */
public interface ReportService {

	/** 查询所有评价报告的key值 */
	List<String> findAllKeys();

	/** 根据项目id和投资方id查询评价报告文件名 */
	String findReportFilenameById(Integer projectId, Integer investorId);

	/** 根据项目id和投资方id读取评价报告文件内容 */
	byte[] readReportContentById(Integer projectId, Integer investorId);

	/** 上传评价报告 */
	void saveReport(Integer projectId, Integer investorId, MultipartFile report) throws IOException;

	/** 根据项目id和投资方id删除评价报告 */
	void deleteReport(Integer projectId, Integer investorId);

	/** 根据项目id删除评价报告 */
	void deleteReportByProject(Integer projectId);

	/** 根据投资方id删除评价报告 */
	void deleteReportByInvestor(Integer investorId);

}

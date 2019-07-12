package com.zzy.investeval.entity.dto;

/**
 * 投资得分DTO接口，用于查询项目-投资方的得分<br>
 * 包含字段：项目id、项目名称、投资方id、投资方公司名、得分
 *
 * @author 赵正阳
 */
public interface InvestScoreDTO {

	Integer getProjectId();

	String getTitle();

	Integer getInvestorId();

	String getCompanyName();

	Double getScore();

}

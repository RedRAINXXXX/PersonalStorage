package com.zzy.investeval.entity.dto;

import com.zzy.investeval.entity.Project;

import java.sql.Date;

/**
 * {@link Project}类DTO接口，用于投影查询<br>
 * 包含字段：id、项目名称、项目负责人、投资类型、投资总额、开始日期
 *
 * @author 赵正阳
 */
public interface ProjectDTO {

	Integer getId();

	String getTitle();

	String getLeader();

	String getType();

	String getInvestment();

	Date getBeginDate();

}

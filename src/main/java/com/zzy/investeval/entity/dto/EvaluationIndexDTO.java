package com.zzy.investeval.entity.dto;

import com.zzy.investeval.entity.EvaluationIndex;

/**
 * {@link EvaluationIndex}类DTO接口，用于包含子指标个数的查询结果
 *
 * @author 赵正阳
 */
public interface EvaluationIndexDTO {

	Integer getId();

	Integer getChildCount();

}

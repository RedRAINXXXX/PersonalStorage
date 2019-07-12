package com.zzy.investeval.entity.dto;

import com.zzy.investeval.entity.Investor;

/**
 * {@link Investor}类DTO接口，用于投影查询
 *
 * @author 赵正阳
 */
public interface InvestorDTO {

	Integer getId();

	String getCompanyName();

	String getLegalRepresentative();

}

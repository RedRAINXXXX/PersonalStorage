package com.zzy.investeval.entity;

import com.zzy.investeval.entity.key.InvestKey;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 项目-投资方关联实体类
 *
 * @author 赵正阳
 */
@Entity
@Table(name = "invest")
public class Invest {
	@EmbeddedId
	private InvestKey key;

	@Column(name = "invest_date")
	private Date investDate;

	public Invest() {}

	public Invest(Integer projectId, Integer investorId, Date investDate) {
		this.key = new InvestKey(projectId, investorId);
		this.investDate = investDate;
	}

	public InvestKey getKey() {
		return key;
	}

	public void setKey(InvestKey key) {
		this.key = key;
	}

	public Date getInvestDate() {
		return investDate;
	}

	public void setInvestDate(Date investDate) {
		this.investDate = investDate;
	}

}

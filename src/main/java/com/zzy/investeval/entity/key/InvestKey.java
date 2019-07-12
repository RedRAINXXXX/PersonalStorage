package com.zzy.investeval.entity.key;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 复合主键（项目id-投资方id）
 *
 * @author 赵正阳
 */
@Embeddable
public class InvestKey implements Serializable {
	@Column(name = "project_id")
	private Integer projectId;

	@Column(name = "investor_id")
	private Integer investorId;

	public InvestKey() {}

	public InvestKey(Integer projectId, Integer investorId) {
		this.projectId = projectId;
		this.investorId = investorId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getInvestorId() {
		return investorId;
	}

	public void setInvestorId(Integer investorId) {
		this.investorId = investorId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		InvestKey investKey = (InvestKey) o;
		return Objects.equals(projectId, investKey.projectId) &&
				Objects.equals(investorId, investKey.investorId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(projectId, investorId);
	}

}

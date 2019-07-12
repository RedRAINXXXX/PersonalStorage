package com.zzy.investeval.entity.key;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 复合主键（项目id-投资方id-专家id）
 *
 * @author 赵正阳
 */
@Embeddable
public class InvestExpertKey implements Serializable {
	@Column(name = "project_id")
	private Integer projectId;

	@Column(name = "investor_id")
	private Integer investorId;

	@Column(name = "expert_id")
	private Integer expertId;

	public InvestExpertKey() {}

	public InvestExpertKey(Integer projectId, Integer investorId, Integer expertId) {
		this.projectId = projectId;
		this.investorId = investorId;
		this.expertId = expertId;
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

	public Integer getExpertId() {
		return expertId;
	}

	public void setExpertId(Integer expertId) {
		this.expertId = expertId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		InvestExpertKey that = (InvestExpertKey) o;
		return Objects.equals(projectId, that.projectId) &&
				Objects.equals(investorId, that.investorId) &&
				Objects.equals(expertId, that.expertId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(projectId, investorId, expertId);
	}

}

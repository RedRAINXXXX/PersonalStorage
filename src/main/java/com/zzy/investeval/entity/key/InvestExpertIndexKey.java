package com.zzy.investeval.entity.key;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 复合主键（项目id-投资方id-专家id-评价指标id）
 *
 * @author 赵正阳
 */
@Embeddable
public class InvestExpertIndexKey implements Serializable {
	@Column(name = "project_id")
	private Integer projectId;

	@Column(name = "investor_id")
	private Integer investorId;

	@Column(name = "expert_id")
	private Integer expertId;

	@Column(name = "index_id")
	private Integer indexId;

	public InvestExpertIndexKey() {}

	public InvestExpertIndexKey(Integer projectId, Integer investorId, Integer expertId, Integer indexId) {
		this.projectId = projectId;
		this.investorId = investorId;
		this.expertId = expertId;
		this.indexId = indexId;
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

	public Integer getIndexId() {
		return indexId;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		InvestExpertIndexKey that = (InvestExpertIndexKey) o;
		return Objects.equals(projectId, that.projectId) &&
				Objects.equals(investorId, that.investorId) &&
				Objects.equals(expertId, that.expertId) &&
				Objects.equals(indexId, that.indexId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(projectId, investorId, expertId, indexId);
	}

}

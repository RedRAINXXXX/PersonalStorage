package com.zzy.investeval.entity;

import com.zzy.investeval.entity.key.InvestExpertKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 项目-投资方得分实体类
 *
 * @author 赵正阳
 */
@Entity
@Table(name = "invest_score")
public class InvestScore {
	@EmbeddedId
	private InvestExpertKey key;

	private Double score;

	public InvestScore() {}

	public InvestScore(Integer projectId, Integer investorId, Integer expertId) {
		this.key = new InvestExpertKey(projectId, investorId, expertId);
		this.score = null;
	}

	public InvestScore(Integer projectId, Integer investorId, Integer expertId, Double score) {
		this.key = new InvestExpertKey(projectId, investorId, expertId);
		this.score = score;
	}

	public InvestExpertKey getKey() {
		return key;
	}

	public void setKey(InvestExpertKey key) {
		this.key = key;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

}

package com.zzy.investeval.entity;

import com.zzy.investeval.entity.key.InvestExpertIndexKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 评价指标得分实体类
 *
 * @author 赵正阳
 */
@Entity
@Table(name = "index_score")
public class IndexScore {
	@EmbeddedId
	private InvestExpertIndexKey key;

	private Integer score;

	public IndexScore() {}

	public IndexScore(Integer projectId, Integer investorId, Integer expertId, Integer indexId, Integer score) {
		this.key = new InvestExpertIndexKey(projectId, investorId, expertId, indexId);
		this.score = score;
	}

	public InvestExpertIndexKey getKey() {
		return key;
	}

	public void setKey(InvestExpertIndexKey key) {
		this.key = key;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}

package com.zzy.investeval.repository;

import com.zzy.investeval.entity.IndexScore;
import com.zzy.investeval.entity.key.InvestExpertIndexKey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@link IndexScore}类DAO接口
 *
 * @author 赵正阳
 */
@Repository
public interface IndexScoreRepository extends JpaRepository<IndexScore, InvestExpertIndexKey> {

	List<IndexScore> findByKeyProjectIdAndKeyInvestorIdAndKeyExpertId(Integer projectId, Integer investorId, Integer expertId);

	@Transactional
	void deleteByKeyProjectId(Integer projectId);

	@Transactional
	void deleteByKeyInvestorId(Integer investorId);

	@Transactional
	void deleteByKeyExpertId(Integer expertId);

	@Transactional
	void deleteByKeyProjectIdAndKeyInvestorId(Integer projectId, Integer investorId);

}

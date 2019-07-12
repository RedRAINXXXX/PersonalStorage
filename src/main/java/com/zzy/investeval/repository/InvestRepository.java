package com.zzy.investeval.repository;

import com.zzy.investeval.entity.Invest;
import com.zzy.investeval.entity.key.InvestKey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@link Invest}类DAO接口
 *
 * @author 赵正阳
 */
@Repository
public interface InvestRepository extends JpaRepository<Invest, InvestKey> {

	List<Invest> findByKeyInvestorId(Integer investorId);

	@Transactional
	void deleteByKeyProjectId(Integer projectId);

	@Transactional
	void deleteByKeyInvestorId(Integer investorId);

}

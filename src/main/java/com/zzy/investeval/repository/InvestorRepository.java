package com.zzy.investeval.repository;

import com.zzy.investeval.entity.Investor;
import com.zzy.investeval.entity.dto.InvestorDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@link Investor}类DAO接口
 *
 * @author 赵正阳
 */
@Repository
public interface InvestorRepository extends JpaRepository<Investor, Integer> {

	List<Investor> findByCompanyNameAndUsernameNull(String companyName);

	List<Investor> findByUsername(String username);

	@Query("select investor.id as id, investor.companyName as companyName, " +
			"investor.legalRepresentative as legalRepresentative " +
			"from Investor investor")
	List<InvestorDTO> findAllBrief();

	@Transactional
	void deleteByUsername(String username);

}

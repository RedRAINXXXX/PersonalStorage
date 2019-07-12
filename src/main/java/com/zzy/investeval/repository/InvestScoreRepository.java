package com.zzy.investeval.repository;

import com.zzy.investeval.entity.InvestScore;
import com.zzy.investeval.entity.dto.InvestScoreDTO;
import com.zzy.investeval.entity.key.InvestExpertKey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@link InvestScore}类DAO接口
 *
 * @author 赵正阳
 */
@Repository
public interface InvestScoreRepository extends JpaRepository<InvestScore, InvestExpertKey> {

	List<InvestScore> findByKeyProjectIdAndKeyInvestorId(Integer projectId, Integer investorId);

	@Query("select investScore.key.projectId as projectId, project.title as title, " +
			"   investScore.key.investorId as investorId, investor.companyName as companyName, " +
			"   investScore.score as score " +
			"from InvestScore investScore, Project project, Investor investor " +
			"where investScore.key.projectId = project.id and investScore.key.investorId = investor.id " +
			"   and investScore.key.expertId = :expertId")
	List<InvestScoreDTO> findByExpertId(@Param("expertId") Integer expertId);

	@Query("select investScore.key.projectId as projectId, project.title as title, " +
			"   investScore.key.investorId as investorId, investor.companyName as companyName, " +
			"   avg(investScore.score) as score " +
			"from InvestScore investScore, Project project, Investor investor " +
			"where investScore.key.projectId = project.id and investScore.key.investorId = investor.id " +
			"   and investScore.key.investorId = :investorId " +
			"group by investScore.key.projectId")
	List<InvestScoreDTO> findAverageScoreByInvestorId(@Param("investorId") Integer investorId);

	@Query("select investScore.key.projectId as projectId, project.title as title, " +
			"   investScore.key.investorId as investorId, investor.companyName as companyName, " +
			"   avg(investScore.score) as score " +
			"from InvestScore investScore, Project project, Investor investor " +
			"where investScore.key.projectId = project.id and investScore.key.investorId = investor.id " +
			"   and investScore.key.projectId = :projectId and investScore.key.investorId = :investorId")
	InvestScoreDTO findAverageScoreByProjectIdAndInvestorId(@Param("projectId") Integer projectId, @Param("investorId") Integer investorId);

	@Query("select investScore.key.projectId as projectId, project.title as title, " +
			"   investScore.key.investorId as investorId, investor.companyName as companyName, " +
			"   avg(investScore.score) as score " +
			"from InvestScore investScore, Project project, Investor investor " +
			"where investScore.key.projectId = project.id and investScore.key.investorId = investor.id " +
			"group by investScore.key.projectId, investScore.key.investorId")
	List<InvestScoreDTO> findAllAverageScore();

	@Transactional
	void deleteByKeyProjectId(Integer projectId);

	@Transactional
	void deleteByKeyInvestorId(Integer investorId);

	@Transactional
	void deleteByKeyExpertId(Integer expertId);

	@Transactional
	void deleteByKeyProjectIdAndKeyInvestorId(Integer projectId, Integer investorId);

}

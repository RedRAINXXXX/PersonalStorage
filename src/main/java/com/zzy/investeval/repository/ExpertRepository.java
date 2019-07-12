package com.zzy.investeval.repository;

import com.zzy.investeval.entity.Expert;
import com.zzy.investeval.entity.dto.ExpertDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@link Expert}类DAO接口
 *
 * @author 赵正阳
 */
@Repository
public interface ExpertRepository extends JpaRepository<Expert, Integer> {

	List<Expert> findByNameAndUsernameNull(String name);

	List<Expert> findByUsername(String username);

	@Query("select expert.id as id, expert.name as name, expert.institution as institution from Expert expert")
	List<ExpertDTO> findAllBrief();

	List<Expert> findByFields(String fields);

	@Transactional
	void deleteByUsername(String username);

}

package com.zzy.investeval.repository;

import com.zzy.investeval.entity.EvaluationIndex;
import com.zzy.investeval.entity.dto.EvaluationIndexDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link EvaluationIndex}类DAO接口
 *
 * @author 赵正阳
 */
@Repository
public interface EvaluationIndexRepository extends JpaRepository<EvaluationIndex, Integer> {

	@Query("select indexLevel1.id as id, count(indexLevel3) as childCount " +
			"from EvaluationIndex indexLevel1, EvaluationIndex indexLevel2, EvaluationIndex indexLevel3 " +
			"where indexLevel1.id = indexLevel2.parentId and indexLevel2.id = indexLevel3.parentId " +
			"group by indexLevel1.id")
	List<EvaluationIndexDTO> findChildCountOfLevel1();

	@Query("select indexLevel2.id as id, count(indexLevel3) as childCount " +
			"from EvaluationIndex indexLevel2, EvaluationIndex indexLevel3 " +
			"where indexLevel2.level = 2 and indexLevel2.id = indexLevel3.parentId " +
			"group by indexLevel2.id")
	List<EvaluationIndexDTO> findChildCountOfLevel2();

	List<EvaluationIndex> findByLevel(Integer level);

	List<EvaluationIndex> findByParentId(Integer parentId);

}

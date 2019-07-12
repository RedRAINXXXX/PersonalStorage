package com.zzy.investeval.repository;

import com.zzy.investeval.entity.Project;
import com.zzy.investeval.entity.dto.ProjectDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link Project}类DAO接口
 *
 * @author 赵正阳
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

	@Query("select project.id as id, project.title as title, project.leader as leader, " +
			"project.type as type, project.investment as investment, project.beginDate as beginDate " +
			"from Project project")
	List<ProjectDTO> findAllBrief();

}

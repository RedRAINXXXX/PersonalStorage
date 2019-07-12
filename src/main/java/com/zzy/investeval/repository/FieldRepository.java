package com.zzy.investeval.repository;

import com.zzy.investeval.entity.Field;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Integer> {

	List<Field> findByLevel(Integer level);

	List<Field> findByParentId(Integer parentId);

}

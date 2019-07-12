package com.zzy.investeval.repository;

import com.zzy.investeval.entity.RegisterApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link RegisterApplication}类DAO接口
 *
 * @author 赵正阳
 */
@Repository
public interface RegisterApplicationRepository extends JpaRepository<RegisterApplication, String> {
}

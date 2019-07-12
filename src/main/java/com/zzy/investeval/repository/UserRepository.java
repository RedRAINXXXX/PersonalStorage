package com.zzy.investeval.repository;

import com.zzy.investeval.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link User}类DAO接口
 *
 * @author 赵正阳
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}

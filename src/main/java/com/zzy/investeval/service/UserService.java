package com.zzy.investeval.service;

import com.zzy.investeval.entity.RegisterApplication;
import com.zzy.investeval.entity.User;
import com.zzy.investeval.entity.UserRole;

import java.sql.Timestamp;
import java.util.List;

/**
 * 用户业务逻辑接口
 *
 * @author 赵正阳
 */
public interface UserService {

	/** 根据用户名查询用户，若用户名不存在则返回null */
	User findByUsername(String username);

	/** 检查用户名是否已存在 */
	boolean isUsernameExist(String username);

	/** 查询所有注册申请并排序：未审核的在前，并按时间倒排 */
	List<RegisterApplication> findAllRegisterApplication();

	/** 根据用户名查询注册申请，若不存在则返回null */
	RegisterApplication findRegisterApplicationByUsername(String username);

	/** 新增注册申请 */
	void insertRegisterApplication(String username, String name);

	/** 修改注册申请的审核日期和审核管理员 */
	void updateRegisterApplication(String username, Timestamp approvalTime, String approvalAdmin);

	/** 新增用户 */
	void insertUser(String username, String password, String name);

	/** 修改密码 */
	void updatePassword(String username, String password);

	/** 修改用户角色，并保证专家用户有对应的专家实体而普通用户和管理员没有 */
	void updateRole(String username, UserRole role);

}

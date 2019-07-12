package com.zzy.investeval.service;

import com.zzy.investeval.entity.Expert;
import com.zzy.investeval.entity.dto.ExpertDTO;

import java.util.List;

/**
 * 专家信息管理业务逻辑接口
 *
 * @author 赵正阳
 */
public interface ExpertService {

	/** 根据用户名查询专家 */
	Expert findByUsername(String username);

	/** 根据id查询专家 */
	Expert findById(Integer id);

	/** 查询所有专家的简要信息：id、姓名、机构 */
	List<ExpertDTO> findAllBrief();

	/** 根据技术领域查询专家 */
	List<Expert> findByFields(String fields);

	/** 新增专家，返回新增专家的id */
	Integer insert(
			String name, String sex, Integer birthYear, String people,
			String institution, String title, String duty,
			String country, String province, String city, String zip, String address,
			String degree, String degreeDate, String university,
			String award, String tel, String email, String fields);

	/** 根据用户名更新个人信息及用户信息 */
	void updateByUsername(
			String username, String name, String sex, Integer birthYear, String people,
			String institution, String title, String position,
			String country, String province, String city, String zip, String address,
			String degree, String degreeDate, String university,
			String award, String tel, String email, String fields);

	/** 根据id更新个人信息及用户信息（如果有） */
	void updateById(
			Integer id, String name, String sex, Integer birthYear, String people,
			String institution, String title, String position,
			String country, String province, String city, String zip, String address,
			String degree, String degreeDate, String university,
			String award, String tel, String email, String fields);

	/** 根据id删除专家及其关联的用户（如果有） */
	void delete(Integer id);

}

package com.zzy.investeval.service;

import com.zzy.investeval.entity.Investor;
import com.zzy.investeval.entity.dto.InvestorDTO;

import java.sql.Date;
import java.util.List;

/**
 * 投资方信息管理业务逻辑接口
 *
 * @author 赵正阳
 */
public interface InvestorService {

	/** 根据用户名查询投资方 */
	Investor findByUsername(String username);

	/** 根据id查询投资方 */
	Investor findById(Integer id);

	/** 查询所有投资方的简要信息：id、公司名称、法定代表人 */
	List<InvestorDTO> findAllBrief();

	/** 新增投资方，返回新增投资方的id */
	Integer insert(
			String companyName, String mainBusiness,
			String country, String province, String city, String address, String zip,
			String registeredCapital, String legalRepresentative, Date establishmentDate,
			Integer employeeNumber, String url, String tel, String email, String introduction);

	/** 根据用户名更新公司信息及用户信息 */
	void updateByUsername(
			String username, String companyName, String mainBusiness,
			String country, String province, String city, String address, String zip,
			String registeredCapital, String legalRepresentative, Date establishmentDate,
			Integer employeeNumber, String url, String tel, String email, String introduction);

	/** 根据id更新公司信息及用户信息（如果有） */
	void update(
			Integer id, String companyName, String mainBusiness,
			String country, String province, String city, String address, String zip,
			String registeredCapital, String legalRepresentative, Date establishmentDate,
			Integer employeeNumber, String url, String tel, String email, String introduction);

	/** 根据id删除投资方及其关联的用户（如果有） */
	void delete(Integer id);
}

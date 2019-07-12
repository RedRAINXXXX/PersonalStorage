package com.zzy.investeval.service;

import com.zzy.investeval.entity.Project;
import com.zzy.investeval.entity.dto.ProjectDTO;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * 项目信息管理业务逻辑接口
 *
 * @author 赵正阳
 */
public interface ProjectService {

	/** 根据id查询项目 */
	Project findById(Integer id);

	/** 查询所有项目 */
	List<Project> findAll();

	/** 查询所有项目的简要信息，包含字段：{@link ProjectDTO} */
	List<ProjectDTO> findAllBrief();

	/** 新增项目，返回新增项目的id */
	Integer insert(
			String title, String type, String investment, Date beginDate,
			String country, String province, String city, String address, String zip,
			String leader, String tel, String email,
			String fields, String techStage, String industry, String industryStage,
			String introduction);

	/** 根据id更新项目信息 */
	void update(
			Integer id, String title, String type, String investment, Date beginDate,
			String country, String province, String city, String address, String zip,
			String leader, String tel, String email,
			String fields, String techStage, String industry, String industryStage,
			String introduction);

	/** 根据id删除项目，同时删除产业化信息表和评价报告 */
	void delete(Integer id);

	/** 根据项目id查询产业化信息表文件名 */
	String findIndustrialFilenameById(Integer id);

	/** 根据项目id读取产业化信息表文件内容 */
	byte[] readIndustrialContentById(Integer id);

	/** 上传项目产业化信息表 */
	void saveIndustrial(Integer id, MultipartFile industrial) throws IOException;

	/** 根据项目id删除产业化信息表 */
	void deleteIndustrial(Integer id);

}

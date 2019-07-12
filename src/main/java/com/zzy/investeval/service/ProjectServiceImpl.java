package com.zzy.investeval.service;

import com.zzy.investeval.entity.Project;
import com.zzy.investeval.entity.dto.ProjectDTO;
import com.zzy.investeval.repository.FileRepository;
import com.zzy.investeval.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static org.hibernate.internal.util.StringHelper.isEmpty;
import static org.hibernate.internal.util.StringHelper.nullIfEmpty;

/**
 * 项目信息管理业务逻辑接口实现类
 *
 * @author 赵正阳
 */
@Service
public class ProjectServiceImpl implements ProjectService {
	private final ProjectRepository projectRepository;
	private final FileRepository industrialFileRepository;

	@Autowired
	public ProjectServiceImpl(
			ProjectRepository projectRepository,
			@Qualifier("industrialFileRepository") FileRepository industrialFileRepository) {
		this.projectRepository = projectRepository;
		this.industrialFileRepository = industrialFileRepository;
	}

	@Override
	public Project findById(Integer id) {
		return projectRepository.findById(id).orElse(null);
	}

	@Override
	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	@Override
	public List<ProjectDTO> findAllBrief() {
		return projectRepository.findAllBrief();
	}

	@Override
	public Integer insert(
			String title, String type, String investment, Date beginDate,
			String country, String province, String city, String address, String zip,
			String leader, String tel, String email,
			String fields, String techStage, String industry, String industryStage,
			String introduction) {
		Project project = makeProject(null, null, title, type, investment, beginDate,
				country, province, city, address, zip,
				leader, tel, email,
				fields, techStage, industry, industryStage, introduction);
		project = projectRepository.save(project);
		return project.getId();
	}

	@Override
	public void update(
			Integer id, String title, String type, String investment, Date beginDate,
			String country, String province, String city, String address, String zip,
			String leader, String tel, String email,
			String fields, String techStage, String industry, String industryStage,
			String introduction) {
		Project project = projectRepository.findById(id).orElse(null);
		assert project != null : "更新项目信息：id不存在";
		project = makeProject(project, id, title, type, investment, beginDate,
				country, province, city, address, zip,
				leader, tel, email,
				fields, techStage, industry, industryStage, introduction);
		projectRepository.save(project);
	}

	@Override
	public void delete(Integer id) {
		projectRepository.deleteById(id);
		industrialFileRepository.delete(id.toString());
	}

	@Override
	public String findIndustrialFilenameById(Integer id) {
		return industrialFileRepository.findFilename(id.toString());
	}

	@Override
	public byte[] readIndustrialContentById(Integer id) {
		return industrialFileRepository.readContent(id.toString());
	}

	@Override
	public void saveIndustrial(Integer id, MultipartFile industrial) throws IOException {
		if (industrial != null && !isEmpty(industrial.getOriginalFilename())) {
			industrialFileRepository.delete(id.toString());
			industrialFileRepository.save(id.toString(), industrial.getOriginalFilename(), industrial.getInputStream());
		}
	}

	@Override
	public void deleteIndustrial(Integer id) {
		industrialFileRepository.delete(id.toString());
	}

	private Project makeProject(
			Project project, Integer id, String title, String type, String investment, Date beginDate,
			String country, String province, String city, String address, String zip,
			String leader, String tel, String email,
			String fields, String techStage, String industry, String industryStage,
			String introduction) {
		if (project == null)
			project = new Project();
		project.setId(id);
		project.setTitle(nullIfEmpty(title));
		project.setType(nullIfEmpty(type));
		project.setInvestment(nullIfEmpty(investment));
		project.setBeginDate(beginDate);
		project.setCountry(nullIfEmpty(country));
		project.setProvince(nullIfEmpty(province));
		project.setCity(nullIfEmpty(city));
		project.setAddress(nullIfEmpty(address));
		project.setZip(nullIfEmpty(zip));
		project.setLeader(nullIfEmpty(leader));
		project.setTel(nullIfEmpty(tel));
		project.setEmail(nullIfEmpty(email));
		project.setFields(nullIfEmpty(fields));
		project.setTechStage(nullIfEmpty(techStage));
		project.setIndustry(nullIfEmpty(industry));
		project.setIndustryStage(nullIfEmpty(industryStage));
		project.setIntroduction(nullIfEmpty(introduction));
		return project;
	}

}

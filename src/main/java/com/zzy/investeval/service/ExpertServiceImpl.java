package com.zzy.investeval.service;

import com.zzy.investeval.entity.Expert;
import com.zzy.investeval.entity.User;
import com.zzy.investeval.entity.dto.ExpertDTO;
import com.zzy.investeval.repository.ExpertRepository;
import com.zzy.investeval.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.hibernate.internal.util.StringHelper.nullIfEmpty;

/**
 * 专家信息管理业务逻辑接口实现类
 *
 * @author 赵正阳
 */
@Service
public class ExpertServiceImpl implements ExpertService {
	private final ExpertRepository expertRepository;
	private final UserRepository userRepository;

	@Autowired
	public ExpertServiceImpl(ExpertRepository expertRepository, UserRepository userRepository) {
		this.expertRepository = expertRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Expert findByUsername(String username) {
		List<Expert> expertList = expertRepository.findByUsername(username);
		return expertList.isEmpty() ? null : expertList.get(0);
	}

	@Override
	public Expert findById(Integer id) {
		return expertRepository.findById(id).orElse(null);
	}

	@Override
	public List<ExpertDTO> findAllBrief() {
		return expertRepository.findAllBrief();
	}

	@Override
	public List<Expert> findByFields(String fields) {
		return expertRepository.findByFields(fields);
	}

	@Override
	public Integer insert(
			String name, String sex, Integer birthYear, String people,
			String institution, String title, String position,
			String country, String province, String city, String zip, String address,
			String degree, String degreeDate, String university,
			String award, String tel, String email, String fields) {
		Expert expert = makeExpert(null, null, name, null,
				sex, birthYear, people,
				institution, title, position,
				country, province, city, zip, address,
				degree, degreeDate, university,
				award, tel, email, fields);
		expert = expertRepository.save(expert);
		return expert.getId();
	}

	@Override
	public void updateByUsername(
			String username, String name, String sex, Integer birthYear, String people,
			String institution, String title, String position,
			String country, String province, String city, String zip, String address,
			String degree, String degreeDate, String university,
			String award, String tel, String email, String fields) {
		Expert expert = findByUsername(username);
		assert expert != null : "更新个人信息：此用户名的专家不存在";
		expert = makeExpert(expert, expert.getId(), name, username,
				sex, birthYear, people,
				institution, title, position,
				country, province, city, zip, address,
				degree, degreeDate, university,
				award, tel, email, fields);
		expertRepository.save(expert);

		User user = userRepository.findById(username).orElse(null);
		assert user != null : "更新个人信息：此用户名的用户不存在";
		user.setName(name);
		userRepository.save(user);
	}

	@Override
	public void updateById(
			Integer id, String name, String sex, Integer birthYear, String people,
			String institution, String title, String position,
			String country, String province, String city, String zip, String address,
			String degree, String degreeDate, String university,
			String award, String tel, String email, String fields) {
		Expert expert = expertRepository.findById(id).orElse(null);
		assert expert != null : "更新个人信息：id不存在";
		expert = makeExpert(expert, id, name, expert.getUsername(),
				sex, birthYear, people,
				institution, title, position,
				country, province, city, zip, address,
				degree, degreeDate, university,
				award, tel, email, fields);
		expertRepository.save(expert);

		if (expert.getUsername() != null) {
			User user = userRepository.findById(expert.getUsername()).orElse(null);
			assert user != null : "更新个人信息：用户名对应的用户不存在";
			user.setName(name);
			userRepository.save(user);
		}
	}

	@Override
	public void delete(Integer id) {
		Expert expert = expertRepository.findById(id).orElse(null);
		assert expert != null : "删除专家：id不存在";
		expertRepository.deleteById(id);
		if (expert.getUsername() != null)
			userRepository.deleteById(expert.getUsername());
	}

	private Expert makeExpert(
			Expert expert, Integer id, String name, String username,
			String sex, Integer birthYear, String people,
			String institution, String title, String position,
			String country, String province, String city, String zip, String address,
			String degree, String degreeDate, String university,
			String award, String tel, String email, String fields) {
		if (expert == null)
			expert = new Expert();
		expert.setId(id);
		expert.setName(nullIfEmpty(name));
		expert.setUsername(nullIfEmpty(username));
		expert.setSex(nullIfEmpty(sex));
		expert.setBirthYear(birthYear);
		expert.setPeople(nullIfEmpty(people));
		expert.setInstitution(nullIfEmpty(institution));
		expert.setTitle(nullIfEmpty(title));
		expert.setDuty(nullIfEmpty(position));
		expert.setCountry(nullIfEmpty(country));
		expert.setProvince(nullIfEmpty(province));
		expert.setCity(nullIfEmpty(city));
		expert.setZip(nullIfEmpty(zip));
		expert.setAddress(nullIfEmpty(address));
		expert.setDegree(nullIfEmpty(degree));
		expert.setDegreeDate(nullIfEmpty(degreeDate));
		expert.setUniversity(nullIfEmpty(university));
		expert.setAward(nullIfEmpty(award));
		expert.setTel(nullIfEmpty(tel));
		expert.setEmail(nullIfEmpty(email));
		expert.setFields(nullIfEmpty(fields));
		return expert;
	}

}

package com.zzy.investeval.service;

import com.zzy.investeval.entity.Investor;
import com.zzy.investeval.entity.User;
import com.zzy.investeval.entity.dto.InvestorDTO;
import com.zzy.investeval.repository.InvestorRepository;
import com.zzy.investeval.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import static org.hibernate.internal.util.StringHelper.nullIfEmpty;

/**
 * 投资方信息管理业务逻辑接口实现类
 *
 * @author 赵正阳
 */
@Service
public class InvestorServiceImpl implements InvestorService {
	private final InvestorRepository investorRepository;
	private final UserRepository userRepository;

	@Autowired
	public InvestorServiceImpl(InvestorRepository investorRepository, UserRepository userRepository) {
		this.investorRepository = investorRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Investor findByUsername(String username) {
		List<Investor> investorList = investorRepository.findByUsername(username);
		return investorList.isEmpty() ? null : investorList.get(0);
	}

	@Override
	public Investor findById(Integer id) {
		return investorRepository.findById(id).orElse(null);
	}

	@Override
	public List<InvestorDTO> findAllBrief() {
		return investorRepository.findAllBrief();
	}

	@Override
	public Integer insert(
			String companyName, String mainBusiness,
			String country, String province, String city, String address, String zip,
			String registeredCapital, String legalRepresentative, Date establishmentDate,
			Integer employeeNumber, String url, String tel, String email, String introduction) {
		Investor investor = makeInvestor(null, null, companyName, mainBusiness,
				country, province, city, address, zip,
				registeredCapital, legalRepresentative, establishmentDate,
				employeeNumber, url, tel, email, introduction);
		investor = investorRepository.save(investor);
		return investor.getId();
	}

	@Override
	public void updateByUsername(
			String username, String companyName, String mainBusiness,
			String country, String province, String city, String address, String zip,
			String registeredCapital, String legalRepresentative, Date establishmentDate,
			Integer employeeNumber, String url, String tel, String email, String introduction) {
		Investor investor = findByUsername(username);
		assert investor != null : "更新公司信息：此用户名的投资方不存在";
		investor = makeInvestor(investor, investor.getId(), companyName, mainBusiness,
				country, province, city, address, zip,
				registeredCapital, legalRepresentative, establishmentDate,
				employeeNumber, url, tel, email, introduction);
		investorRepository.save(investor);

		User user = userRepository.findById(username).orElse(null);
		assert user != null : "更新公司信息：此用户名的用户不存在";
		user.setName(companyName);
		userRepository.save(user);
	}

	@Override
	public void update(
			Integer id, String companyName, String mainBusiness,
			String country, String province, String city, String address, String zip,
			String registeredCapital, String legalRepresentative, Date establishmentDate,
			Integer employeeNumber, String url, String tel, String email, String introduction) {
		Investor investor = investorRepository.findById(id).orElse(null);
		assert investor != null : "更新投资方信息：id不存在";
		investor = makeInvestor(investor, id, companyName, mainBusiness,
				country, province, city, address, zip,
				registeredCapital, legalRepresentative, establishmentDate,
				employeeNumber, url, tel, email, introduction);
		investorRepository.save(investor);

		if (investor.getUsername() != null) {
			User user = userRepository.findById(investor.getUsername()).orElse(null);
			assert user != null : "更新公司信息：用户名对应的用户不存在";
			user.setName(companyName);
			userRepository.save(user);
		}
	}

	@Override
	public void delete(Integer id) {
		Investor investor = investorRepository.findById(id).orElse(null);
		assert investor != null : "删除投资方：id不存在";
		investorRepository.deleteById(id);
		if (investor.getUsername() != null)
			userRepository.deleteById(investor.getUsername());
	}

	private Investor makeInvestor(
			Investor investor, Integer id, String companyName, String mainBusiness,
			String country, String province, String city, String address, String zip,
			String registeredCapital, String legalRepresentative, Date establishmentDate,
			Integer employeeNumber, String url, String tel, String email, String introduction) {
		if (investor == null)
			investor = new Investor();
		investor.setId(id);
		investor.setCompanyName(nullIfEmpty(companyName));
		investor.setMainBusiness(nullIfEmpty(mainBusiness));
		investor.setCountry(nullIfEmpty(country));
		investor.setProvince(nullIfEmpty(province));
		investor.setCity(nullIfEmpty(city));
		investor.setAddress(nullIfEmpty(address));
		investor.setZip(nullIfEmpty(zip));
		investor.setRegisteredCapital(nullIfEmpty(registeredCapital));
		investor.setLegalRepresentative(nullIfEmpty(legalRepresentative));
		investor.setEstablishmentDate(establishmentDate);
		investor.setEmployeeNumber(employeeNumber);
		investor.setUrl(nullIfEmpty(url));
		investor.setTel(nullIfEmpty(tel));
		investor.setEmail(nullIfEmpty(email));
		investor.setIntroduction(nullIfEmpty(introduction));
		return investor;
	}

}

package com.zzy.investeval.service;

import com.zzy.investeval.entity.Expert;
import com.zzy.investeval.entity.Investor;
import com.zzy.investeval.entity.RegisterApplication;
import com.zzy.investeval.entity.User;
import com.zzy.investeval.entity.UserRole;
import com.zzy.investeval.repository.ExpertRepository;
import com.zzy.investeval.repository.InvestorRepository;
import com.zzy.investeval.repository.RegisterApplicationRepository;
import com.zzy.investeval.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * 用户业务逻辑接口实现类
 *
 * @author 赵正阳
 */
@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final RegisterApplicationRepository registerApplicationRepository;
	private final ExpertRepository expertRepository;
	private final InvestorRepository investorRepository;

	@Autowired
	public UserServiceImpl(
			UserRepository userRepository,
			RegisterApplicationRepository registerApplicationRepository,
			ExpertRepository expertRepository,
			InvestorRepository investorRepository) {
		this.userRepository = userRepository;
		this.registerApplicationRepository = registerApplicationRepository;
		this.expertRepository = expertRepository;
		this.investorRepository = investorRepository;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findById(username).orElse(null);
	}

	@Override
	public boolean isUsernameExist(String username) {
		return userRepository.existsById(username);
	}

	@Override
	public List<RegisterApplication> findAllRegisterApplication() {
		List<RegisterApplication> applicationList = registerApplicationRepository.findAll();
		applicationList.sort((x, y) -> {
			if (x.getApprovalTime() == null && y.getApprovalTime() != null)
				return -1;
			else if (x.getApprovalTime() != null && y.getApprovalTime() == null)
				return 1;
			else return -x.getApplicationTime().compareTo(y.getApplicationTime());
		});
		return applicationList;
	}

	@Override
	public RegisterApplication findRegisterApplicationByUsername(String username) {
		return registerApplicationRepository.findById(username).orElse(null);
	}

	@Override
	public void insertRegisterApplication(String username, String name) {
		registerApplicationRepository.save(new RegisterApplication(username, name));
	}

	@Override
	public void updateRegisterApplication(String username, Timestamp approvalTime, String approvalAdmin) {
		RegisterApplication application = registerApplicationRepository.findById(username).orElse(null);
		assert application != null : "审核注册申请：申请记录不存在";
		application.setApprovalTime(approvalTime);
		application.setApprovalAdmin(approvalAdmin);
		registerApplicationRepository.save(application);
	}

	@Override
	public void insertUser(String username, String password, String name) {
		userRepository.save(new User(username, password, name));
	}

	@Override
	public void updatePassword(String username, String password) {
		User user = findByUsername(username);
		assert user != null : "修改密码：用户名不存在";
		user.setPassword(password);
		userRepository.save(user);
	}

	@Override
	public void updateRole(String username, UserRole role) {
		User user = findByUsername(username);
		assert user != null : "修改用户角色：用户名不存在";
		UserRole originalRole = user.getRole();
		if (originalRole == role)
			return;
		user.setRole(role);
		userRepository.save(user);
		// 匹配专家或投资方，若未匹配到则新增
		if (role == UserRole.EXPERT) {
			// 根据姓名与专家表中username为NULL的匹配
			List<Expert> matchedExperts = expertRepository.findByNameAndUsernameNull(user.getName());
			if (matchedExperts.isEmpty()) {
				Expert expert = new Expert();
				expert.setName(user.getName());
				expert.setUsername(username);
				expertRepository.save(expert);
			}
			else {
				Expert expert = matchedExperts.get(0);
				expert.setUsername(username);
				expertRepository.save(expert);
			}
		}
		else if (role == UserRole.INVESTOR) {
			// 根据公司名称与投资方表中username为NULL的匹配
			List<Investor> matchedInvestors = investorRepository.findByCompanyNameAndUsernameNull(user.getName());
			if (matchedInvestors.isEmpty()) {
				Investor investor = new Investor();
				investor.setCompanyName(user.getName());
				investor.setUsername(username);
				investorRepository.save(investor);
			}
			else {
				Investor investor = matchedInvestors.get(0);
				investor.setUsername(username);
				investorRepository.save(investor);
			}
		}

		// 若从专家或投资方改为其他角色则删除对应实体
		if (originalRole == UserRole.EXPERT)
			expertRepository.deleteByUsername(username);
		else if (originalRole == UserRole.INVESTOR)
			investorRepository.deleteByUsername(username);
	}

}

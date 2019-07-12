package com.zzy.investeval.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 注册申请实体类
 *
 * @author 赵正阳
 */
@Entity
@Table(name = "register_application")
public class RegisterApplication {
	@Id
	private String username;

	private String name;

	@Column(name = "application_time")
	private Timestamp applicationTime;

	@Column(name = "approval_time")
	private Timestamp approvalTime;

	@Column(name = "approval_admin")
	private String approvalAdmin;

	public RegisterApplication() {}

	public RegisterApplication(String username, String name) {
		this.username = username;
		this.name = name;
		this.applicationTime = new Timestamp(System.currentTimeMillis());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(Timestamp applicationTime) {
		this.applicationTime = applicationTime;
	}

	public Timestamp getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(Timestamp approvalTime) {
		this.approvalTime = approvalTime;
	}

	public String getApprovalAdmin() {
		return approvalAdmin;
	}

	public void setApprovalAdmin(String approvalAdmin) {
		this.approvalAdmin = approvalAdmin;
	}

}

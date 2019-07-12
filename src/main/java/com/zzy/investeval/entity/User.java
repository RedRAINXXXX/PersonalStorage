package com.zzy.investeval.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户实体类
 *
 * @author 赵正阳
 */
@Entity
@Table(name = "user")
public class User {
	@Id
	private String username;

	private String password;

	private String name;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	public User() {}

	public User(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = UserRole.UNDEFINED;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

}

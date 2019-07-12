package com.zzy.investeval.entity;

/**
 * 用户角色枚举类
 *
 * @author 赵正阳
 */
public enum UserRole {
	/** 未定义（审核中） */
	UNDEFINED,

	/** 普通用户 */
	GUEST,

	/** 专家用户 */
	EXPERT,

	/** 投资方用户 */
	INVESTOR,

	/** 管理员 */
	ADMIN
}


package com.chinamobile.flow.entity;

/**
 * @Description: 用户表
 * @ClassName TbUser
 * @author: shosho
 * @Created 2016 2016年1月20日 下午8:39:46
 */
public class TbUser {
	// 用户帐号
	private String userId;
	// 用户姓名
	private String userName;
	// 用户密码
	private String userPass;

	public String getUserId() {

		return userId;
	}

	public String getUserName() {

		return userName;
	}

	public String getUserPass() {

		return userPass;
	}

	public void setUserId(String userId) {

		this.userId = userId;
	}

	public void setUserName(String userName) {

		this.userName = userName;
	}

	public void setUserPass(String userPass) {

		this.userPass = userPass;
	}
}

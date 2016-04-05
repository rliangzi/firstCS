package com.chinamobile.flow.service.tbuser;

import com.chinamobile.flow.entity.TbUser;
import com.chinamobile.flow.service.BaseServiceTest;
import com.chinamobile.flow.service.IUserService;
import com.chinamobile.flow.service.impl.UserServiceImpl;

/**
 * @Description: 用户测试类
 * @ClassName TbUserTest
 * @author: shosho
 * @Created 2016 2016年1月20日 下午8:25:09
 */
public class TbUserTest extends BaseServiceTest {

	private IUserService userService;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		userService = context.getBean(UserServiceImpl.class);
	}

	public void testGetUser() throws Exception {
		// 根据id获取对象
		TbUser tu = userService.getUserbyId("admin");
		System.out.println(tu);
	}
}

package com.chinamobile.flow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 登录页面
 * @ClassName LoginController
 * @author: zhangyongzx
 * @Created 2015 2015年12月23日 下午3:51:36
 */
@Controller
public class LoginController {
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
}

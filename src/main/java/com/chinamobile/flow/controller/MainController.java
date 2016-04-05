package com.chinamobile.flow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 首页控制器
 * @ClassName MainController
 * @author: zhangyongzx
 * @Created 2015 2015年12月23日 下午3:51:45
 */
@Controller
@RequestMapping("/main")
public class MainController {

	/**
	 * @Description: 主页面
	 * @return
	 * @ReturnType String
	 * @author: zhangyongzx
	 * @Created 2015 2015年12月23日 下午3:51:52
	 */
	@RequestMapping(value = "/index")
	public String index() {
		return "/main/index";
	}

	/**
	 * @Description: 欢迎页面
	 * @return
	 * @ReturnType String
	 * @author: zhangyongzx
	 * @Created 2015 2015年12月23日 下午3:51:58
	 */
	@RequestMapping(value = "/welcome")
	public String welcome() {
		return "/main/welcome";
	}

}

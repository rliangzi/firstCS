package com.chinamobile.flow.controller.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.chinamobile.flow.entity.TbUser;
import com.chinamobile.flow.service.IUserService;
import com.chinamobile.flow.util.EncryptionUtil;
import com.chinamobile.flow.util.UserUtil;

/**
 * @Description: 用户相关控制器
 * @ClassName UserController
 * @author: zhangyongzx
 * @Created 2015 2015年12月23日 下午4:33:56
 */
@Controller
@RequestMapping("/user")
public class UserController {

	/**
	 * @Description: 日志打印
	 * @field Logger logger
	 * @created 2015 2015年12月23日 下午5:05:07
	 */
	// 日志记录
	protected Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	/**
	 * @Description: 登录系统
	 * @param userName
	 * @param password
	 * @param session
	 * @return
	 * @ReturnType String
	 * @author: zhangyongzx
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @Created 2015 2015年12月23日 下午5:05:01
	 */
	@RequestMapping(value = "/logon")
	public String logon(@RequestParam("username") String userName, @RequestParam("password") String password, HttpSession session) throws IllegalArgumentException, Exception {
		logger.debug("logon request: {username={}, password={}}", userName, password);
		// 获取登录用户
		/*		TbUser tu = userService.getUserbyId(userName);
		
		// 检查通过不为空
		if (tu != null) {
			// 获取加密密码
			String decryPassword = new String(EncryptionUtil.decode(EncryptionUtil.hex2byte(tu.getUserPass()), "_manager".getBytes()));
			// 进行密码验证
			if (password.equals(decryPassword)) {
				// 放入session中
				UserUtil.saveUserToSession(session, tu);
				return "redirect:/main/index";
			} else {
				return "redirect:/login?error=true";
			}
		} else {
			return "redirect:/login?error=true";
		}*/
		
		UserUtil.saveUserToSession(session, new TbUser());
        return "redirect:/main/index";
	}

	/**
	 * @Description: 登出系统
	 * @param session
	 * @return
	 * @ReturnType String
	 * @author: zhangyongzx
	 * @Created 2015 2015年12月23日 下午5:04:54
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "/login";
	}

	/**
	 * @Description: 选择用户、组织机构
	 * @param type
	 * @param request
	 * @return
	 * @ReturnType ModelAndView
	 * @author: shosho
	 * @Created 2016 2016年1月25日 下午7:08:30
	 */
	@RequestMapping(value = "/select-user-group")
	public ModelAndView selectList(@RequestParam("type") String type, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("user/select-user-group");
		List<Map<String, Object>> list = userService.getUserGroup(type);
		mav.addObject("list", list);
		mav.addObject("type", type);
		return mav;
	}

}

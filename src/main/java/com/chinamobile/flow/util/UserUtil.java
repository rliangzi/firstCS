package com.chinamobile.flow.util;

import javax.servlet.http.HttpSession;

import com.chinamobile.flow.entity.TbUser;

/**
 * @Description: 用户工具类
 * @ClassName UserUtil
 * @author: zhangyongzx
 * @Created 2015 2015年12月23日 下午4:34:22
 */
public class UserUtil {

	/**
	 * @Description: 用户对象
	 * @field String USER
	 * @created 2015 2015年12月23日 上午9:15:32
	 */
	public static final String USER = "user";

	/**
	 * @Description: 获取当前系统登录人id
	 * @param session
	 * @return
	 * @ReturnType String
	 * @author: shosho
	 * @Created 2016 2016年1月21日 下午2:07:49
	 */
	public static String getLoginUserId(HttpSession session) {
		TbUser tu = getUserFromSession(session);
		if (tu != null) {
			return tu.getUserId();
		} else {
			return null;
		}
	}

	/**
	 * @Description: 从Session获取当前用户信息
	 * @param session
	 * @return
	 * @ReturnType Map
	 * @author: zhangyongzx
	 * @Created 2015 2015年12月23日 下午4:34:28
	 */
	public static TbUser getUserFromSession(HttpSession session) {
		Object attribute = session.getAttribute(USER);
		return attribute == null ? null : (TbUser) attribute;
	}

	/**
	 * @Description: 设置用户到session
	 * @param session
	 * @param user
	 * @ReturnType void
	 * @author: zhangyongzx
	 * @Created 2015 2015年12月23日 下午4:34:35
	 */
	public static void saveUserToSession(HttpSession session, TbUser user) {
		session.setAttribute(USER, user);
	}

}

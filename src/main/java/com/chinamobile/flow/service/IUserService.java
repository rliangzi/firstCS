
package com.chinamobile.flow.service;

import java.util.List;
import java.util.Map;

import com.chinamobile.flow.entity.TbUser;

/**
 * @Description: 用户接口
 * @ClassName IUserService
 * @author: shosho
 * @Created 2016 2016年1月28日 下午4:15:58
 */
public interface IUserService {

	/**
	 * @Description: 根据用户id获取用户
	 * @param userId
	 * @return
	 * @ReturnType TbUser
	 * @author: shosho
	 * @Created 2016 2016年1月28日 下午4:15:53
	 */
	TbUser getUserbyId(String userId);

	/**
	 * @Description: 选择用户、组织机构
	 * @param type
	 * @return
	 * @ReturnType List<Map<String,String>>
	 * @author: shosho
	 * @Created 2016 2016年1月25日 下午7:00:31
	 */
	List<Map<String, Object>> getUserGroup(String type);
}

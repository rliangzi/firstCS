
package com.chinamobile.flow.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.chinamobile.flow.dao.IBaseJdbcDao;
import com.chinamobile.flow.entity.TbUser;
import com.chinamobile.flow.service.IUserService;
import com.chinamobile.flow.util.ConvertUtils;

/**
 * @Description: 用户接口实现类
 * @ClassName UserServiceImpl
 * @author: shosho
 * @Created 2016 2016年1月20日 下午7:31:10
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

	/**
	 * @Description: 基础dao
	 * @field IBaseDao baseDao
	 * @created 2016 2016年1月20日 下午9:04:47
	 */
	@Autowired
	private IBaseJdbcDao baseJdbcDao;

	/**
	 * @Description: 根据用户id获取用户
	 * @param userId
	 * @return
	 * @ReturnType TbUser
	 * @author: shosho
	 * @Created 2016 2016年1月20日 下午7:31:43
	 */
	@Override
	public TbUser getUserbyId(String userId) {
		TbUser tu = null;
		String sql = "select * from tb_user where userId = ? ";
		List<Map<String, Object>> list = baseJdbcDao.queryForList(sql, new Object[] { userId });
		if (!CollectionUtils.isEmpty(list) && list.size() > 0) {
			Map<String, Object> mp = list.get(0);
			tu = map2TbUser(mp);
		}
		return tu;
	}

	/**
	 * @Description: 选择用户、组织机构
	 * @param type
	 * @return
	 * @ReturnType List<Map<String,String>>
	 * @author: shosho
	 * @Created 2016 2016年1月25日 下午7:00:31
	 */
	public List<Map<String, Object>> getUserGroup(String type) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "";
		// 如果类型不为空
		if (StringUtils.isNotEmpty(type)) {
			if (type.equals("user")) {
				sql = "select userId,userName from tb_user";
			} else if (type.equals("group")) {
				sql = "select groupId,groupName from tb_group";
			}
		}
		if (StringUtils.isNotEmpty(sql)) {
			list = baseJdbcDao.queryForList(sql);
		}
		return list;
	}

	/**
	 * @Description: 进行对象转换
	 * @param mp
	 * @return
	 * @ReturnType TbUser
	 * @author: shosho
	 * @Created 2016 2016年1月22日 下午5:18:06
	 */
	private TbUser map2TbUser(Map<String, Object> mp) {
		TbUser tu = new TbUser();
		if (mp != null) {
			tu.setUserId(ConvertUtils.toString(mp.get("userId")));
			tu.setUserName(ConvertUtils.toString(mp.get("userName")));
			tu.setUserPass(ConvertUtils.toString(mp.get("userPass")));
		}
		return tu;
	}

}

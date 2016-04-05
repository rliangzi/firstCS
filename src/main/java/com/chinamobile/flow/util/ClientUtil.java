package com.chinamobile.flow.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import javax.ws.rs.core.UriBuilder;

import com.cmos.esbclient.bean.EsbInObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * @Description: 用户工具类
 * @ClassName UserUtil
 * @author: zhangyongzx
 * @Created 2015 2015年12月23日 下午4:34:22
 */
public class ClientUtil {

	public static ClientConfig config = new DefaultClientConfig();
	public static Client client = Client.create(config);
	public static WebResource resources = client.resource(getBaseURI());
	public static EsbInObject esbInput = new EsbInObject();
	// 系统编码
	public static String systemId = "sys_1002";

	/**
	 * @Description: 格式化url
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 * @ReturnType String
	 * @author: shosho
	 * @Created 2016 2016年2月6日 上午9:00:20
	 */
	public static String formatUri(String loginStaffId, String url) throws UnsupportedEncodingException {
		esbInput.getParams().put("loginStaffId", loginStaffId);
		esbInput.getParams().put("systemId", systemId);
		return URLEncoder.encode(url, "UTF-8").replaceAll("\\+", "%20");
	}

	/**
	 * @Description: 获取基础url
	 * @return
	 * @ReturnType URI
	 * @author: zhangyongzx
	 * @Created 2016 2016年1月18日 下午1:55:52
	 */
	public static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:9901/workflow").build();
	}
}

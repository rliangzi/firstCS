package com.chinamobile.flow.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import javax.ws.rs.core.UriBuilder;

import com.chinamobile.flow.BaseTest;
import com.cmos.esbclient.bean.EsbInObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * @Description:基础service测试类
 * @ClassName BaseServiceTest
 * @author: shosho
 * @Created 2015 2015年7月6日 下午8:49:53
 */
public class BaseServiceTest extends BaseTest {
	protected ClientConfig config = new DefaultClientConfig();
	protected Client client = Client.create(config);
	protected WebResource resources = client.resource(getBaseURI());
	protected EsbInObject esbInput = new EsbInObject();

	// 系统编码
	protected String systemId = "sys_1001";
	// 系统登录人
	protected String loginStaffId = "admin";

	/**
	 * @Description: 格式化url
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 * @ReturnType String
	 * @author: shosho
	 * @Created 2016 2016年2月6日 上午9:00:20
	 */
	public String formatUri(String url) throws UnsupportedEncodingException {
		return URLEncoder.encode(url, "UTF-8").replaceAll("\\+", "%20");
	}

	/**
	 * @Description: 获取基础url
	 * @return
	 * @ReturnType URI
	 * @author: zhangyongzx
	 * @Created 2016 2016年1月18日 下午1:55:52
	 */
	public URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:9901/workflow").build();
	}

	protected void setUp() throws Exception {
		esbInput.getParams().put("systemId", systemId);
		esbInput.getParams().put("loginStaffId", loginStaffId);
		super.setUp();
	}
}

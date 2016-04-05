
package com.chinamobile.flow;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

/**
 * @Description: 基础测试类
 * @ClassName BaseTest
 * @author: shosho
 * @Created 2016 2016年1月20日 下午8:25:15
 */
public class BaseTest extends TestCase {
	protected ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	public void testContext() {
		assertNotNull("ApplicationContext is null!", context);
	}
}

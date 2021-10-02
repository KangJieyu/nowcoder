package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
// 启用正式项目的配置类
@ContextConfiguration(classes = CommunityApplication.class)
// 得到IoC容器
class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	// ApplicationContext 容器
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}


	/**
	 * 容器创建Bean
	 */
	@Test
	public void testApplicationContext() {
		System.out.println(applicationContext);
		// AlphaDao有两个实现类，为AlphaDaoHibernateImpl和AlphaDaoMyBatisImpl，注解@Primary后会优先访问
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());
		alphaDao = applicationContext.getBean("hibernate", AlphaDao.class);
		System.out.println(alphaDao.select());
	}

	/**
	 * 容器管理Bean
	 * 默认只被实例化一次，单例模式
	 *
	 * 每次访问都创建对象
	 * @Scope() param:singleton, prototype
	 */
	@Test
	public void testBeanManagement() {
		AlphaService alphaService = applicationContext.getBean("alpha", AlphaService.class);
		System.out.println(alphaService);
		alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
	}

	@Test
	public void testBeanConfig() {
		SimpleDateFormat simpleDateFormat =
				applicationContext.getBean("simpleDateFormat", SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	/**
	 * 依赖注入
	 * @Qualifier 指定注入的bean
	 */
	@Autowired
	@Qualifier("hibernate")
	private AlphaDao alphaDao;

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Autowired
	private AlphaService alphaService;

	@Test
	public void testDI() {
		System.out.println(alphaDao);
		System.out.println(simpleDateFormat);
		System.out.println(alphaService);
	}



}

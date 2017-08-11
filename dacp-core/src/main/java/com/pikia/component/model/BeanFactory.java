package com.pikia.component.model;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.XmlWebApplicationContext;

@Component
public class BeanFactory implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	private static Logger LOG = Logger.getLogger(BeanFactory.class);

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	public static <T> T getBean(Class<T> cls) {
		return (T) applicationContext.getBean(cls);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext1) throws BeansException {
		applicationContext = applicationContext1;

		// 载入系统配置
//		Configuration.getInstance().loadClasspathProperties(applicationContext1);

		boolean isWebApp = false;

		ConfigurableListableBeanFactory beanFactory = null;
		if ((applicationContext instanceof GenericApplicationContext)) {
			beanFactory = ((GenericApplicationContext) applicationContext).getBeanFactory();
		} else if ((applicationContext instanceof FileSystemXmlApplicationContext)) {
			beanFactory = ((FileSystemXmlApplicationContext) applicationContext).getBeanFactory();
		} else if ((applicationContext instanceof XmlWebApplicationContext)) {
			beanFactory = ((XmlWebApplicationContext) applicationContext).getBeanFactory();
			isWebApp = true;
		} else {
			LOG.warn("applicationContext的类型没有识别成功,为：" + applicationContext1.getClass().getName());
		}

		DefaultListableBeanFactory defaultListableBeanFactory = null;
		if ((beanFactory instanceof DefaultListableBeanFactory)) {
			defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
		} else {
			LOG.warn("beanFactory的类型没有识别成功，为：" + beanFactory.getClass().getName());
		}
		//if (defaultListableBeanFactory != null) {
			//initDynamicBeans(defaultListableBeanFactory, isWebApp);
		//} else {
		//	LOG.warn("beanFactory的类型没有识别成功，为：" + beanFactory.getClass().getName());
		//}
	}
//   初始化数据库DataSource
//	private void initDynamicBeans(DefaultListableBeanFactory defaultListableBeanFactory, boolean isWebApp) {
//		List<DataSourceDef> defs = getDataSourceDefs();
//		try {
//			for (int i = 0; (defs != null) && (i < defs.size()); i++) {
//				DataSourceDef def = (DataSourceDef) defs.get(i);
//				defaultListableBeanFactory.registerBeanDefinition(def.getId(), getBeanDefinition(def, isWebApp));
//				for (int j = 0; j < def.getAlias().length; j++) {
//					defaultListableBeanFactory.registerAlias(def.getId(), def.getAlias()[j]);
//				}
//				LOG.info("数据源：[" + def.getId() + ":" + def.getName() + "]注入成功");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}

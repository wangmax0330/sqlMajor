package com.pikia.component.web.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

// 城市初始化,没有找到合适的json城市数据
@Component
public class AppInitService implements InitializingBean, ApplicationContextAware {
	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public void afterPropertiesSet() throws Exception {
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		try {
			//String filePath = AppInitService.class.getResource("/").getPath();
			//CityUtils.initCities(filePath + "config/province_city_district.json");
		} catch (Exception e) {
			logger.error(e, e);
		}
	}

}

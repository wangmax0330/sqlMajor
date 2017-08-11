package com.pikia.component.web.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.pikia.component.web.filter.SpringContextHolder;

@Component
public class SystemInitService implements InitializingBean, ApplicationContextAware {
	public void afterPropertiesSet() throws Exception {
	}

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		SpringContextHolder.setApplicationContext(context);
	}
}
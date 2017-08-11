package com.pikia.component.web.filter;

import org.springframework.context.ApplicationContext;

public class SpringContextHolder {
	private static ApplicationContext context;

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public static void setApplicationContext(ApplicationContext context) {
		SpringContextHolder.context = context;
	}
}

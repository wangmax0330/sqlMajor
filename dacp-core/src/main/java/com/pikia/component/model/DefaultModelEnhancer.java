package com.pikia.component.model;

import org.springframework.beans.BeansException;

public class DefaultModelEnhancer implements ModelEnhancer {

	public <T> T makeModel(Class<T> paramClass) throws BeansException {
		return null;
	}

	@Override
	public <T> T enhanceModel(T paramT) throws BeansException {
		return null;
	}

	@Override
	public void injectDependencies(Object paramObject) throws BeansException {

	}

}

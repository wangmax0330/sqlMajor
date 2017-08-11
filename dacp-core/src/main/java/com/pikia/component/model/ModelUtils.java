package com.pikia.component.model;

import org.springframework.util.Assert;

import com.pikia.component.anonation.Model;

public class ModelUtils {

	public static ModelKey asModelKey(Class<?> modelClass, Object identifier) {
		Assert.notNull(modelClass, "Parameter 'modelClass' must not be null");
		Assert.notNull(identifier, "Parameter 'identifier' must not be null");

		return new ModelKey(modelClass, identifier);
	}

	public static boolean isModel(Object model) {
		if (model == null) {
			return false;
		}
		return isModel(model.getClass());
	}

	public static boolean isModel(Class<?> clazz) {
		if (clazz.isAnnotationPresent(Model.class)) {
			return true;
		}
		return false;
	}

//	public static boolean isModified(Object model) {
//		if (model == null) {
//			return false;
//		}
//		if ((Modifiable.class.isAssignableFrom(model.getClass()))
//				&& (((Modifiable) model).isModified())) {
//			return true;
//		}
//
//		return false;
//	}
}

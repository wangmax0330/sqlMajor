package com.pikia.component.model;

import java.util.List;

public abstract interface ModelContainer extends ModelEnhancer {

	public abstract Object addModel(ModelKey paramModelKey, Object paramObject);

	public abstract Object getModel(ModelKey paramModelKey);

	public abstract Object getModel(ModelKey modelKey, ModelLoader modelLoader);

	public abstract Object removeModel(ModelKey paramModelKey);

	public abstract boolean containsModel(ModelKey paramModelKey);

	/**
	 * 批量对象 封装到缓存中
	 * 
	 * @param paramList
	 * @param paramClass
	 * @param paramModelLoader
	 * @return
	 */
	public abstract <T> List<T> identifiersToModels(List<? extends Object> paramList,
			Class<T> paramClass, ModelLoader paramModelLoader);
}

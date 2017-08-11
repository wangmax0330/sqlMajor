package com.pikia.component.controller;

import com.pikia.component.pagination.PaginationQueryContext;
import com.pikia.component.pagination.SortPagedList;
import com.pikia.component.service.ModelCrudService;

public abstract class ModelCrudOperator {
	public SortPagedList<?> doList(PaginationQueryContext queryContext, Class<?> modelClass) {
		return getModelCrudService().list(queryContext, modelClass);
	}

	// 根据id更新缓存
	public Object doUpdate(Object model, Object id) {
		return onUpdate(model);
	}

	// 直接更新缓存
	public Object onUpdate(Object model) {
		return getModelCrudService().saveOrUpdate(model);
	}

	// 根据id 删除
	public void doDelete(Class<?> modelClass, Long[] ids) {
		onDelete(ids);
	}

	protected void onDelete(Long[] ids) {
		getModelCrudService().delete(ids);
	}

	/**
	 * 展示没用
	 * 
	 * @return
	 */
	protected abstract ModelCrudService getModelCrudService();
}

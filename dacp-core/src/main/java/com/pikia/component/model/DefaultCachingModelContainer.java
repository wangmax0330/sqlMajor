package com.pikia.component.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class DefaultCachingModelContainer extends DefaultModelEnhancer implements ModelContainer {
	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public <T> List<T> identifiersToModels(List<? extends Object> identifiers, Class<T> modelClass,
			ModelLoader modelLoader) {
		List models = new ArrayList(identifiers.size());
		for (Iterator i$ = identifiers.iterator(); i$.hasNext();) {
			Object identifier = i$.next();
			Object model = getModel(ModelUtils.asModelKey(modelClass, identifier), modelLoader);
			models.add(model);
		}
		return models;
	}

	@Override
	public Object addModel(ModelKey paramModelKey, Object paramObject) {
		return null;
	}

	@Override
	public Object getModel(ModelKey paramModelKey) {
		return null;
	}

	@Override
	public Object getModel(ModelKey modelKey, ModelLoader modelLoader) {
		if ((modelKey == null) || (modelKey.getIdentifier() == null)) {
			return null;
		}
		if (modelLoader == null) {
			throw new IllegalArgumentException("Parameter 'modelLoader' must not be null");
		}
		Object model = null;
		/**
		 * 先从缓存中查询信息.判断缓存中的对象是否需要修改
		 */
		model = getModelFromCache(modelKey);
		if (model != null) {
		}
		if (model != null) {
			return model;
		}
		model = modelLoader.loadModel(modelKey.getIdentifier());
		return model;
	}

	@Override
	public Object removeModel(ModelKey paramModelKey) {
		return null;
	}

	@Override
	public boolean containsModel(ModelKey paramModelKey) {
		return false;
	}

	protected Object getModelFromCache(ModelKey modelKey) {
		return null;
	}
}

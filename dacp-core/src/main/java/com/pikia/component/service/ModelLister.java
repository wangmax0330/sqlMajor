package com.pikia.component.service;

import java.util.List;

import com.pikia.component.pagination.PaginationQueryContext;
import com.pikia.component.pagination.SortPagedList;

public abstract interface ModelLister {
	public abstract SortPagedList<?> list(PaginationQueryContext paramPaginationQueryContext,
			Class<?> paramClass);

	public abstract SortPagedList<?> list(PaginationQueryContext paramPaginationQueryContext,
			Class<?> paramClass, List paramList, Integer paramInteger);
}

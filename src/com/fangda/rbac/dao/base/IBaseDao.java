package com.fangda.rbac.dao.base;

import java.io.Serializable;
import java.util.List;

import com.fangda.rbac.utils.PageBean;

public interface IBaseDao<T> {
	void add(T entity);
	void delete(T entity);
	void updata(T entity);
	List<T> findAll();
	T findById(Serializable serializable);
	void editPassword(String queryname, Object... object);
	void saveOrUpdata(T entity);

	void pageQuery(PageBean pageBean);
}

package com.fangda.rbac.service;

import java.util.List;

import com.fangda.rbac.domain.Function;
import com.fangda.rbac.utils.PageBean;

public interface IFunctionService {
	void pageQuery(PageBean pageBean);
	List<Function> findAll();
	void sava(Function model);
	List<Function> findFunctionByUserId(String id);
	List<Function> findMenu();
	void delete(String ids);
	Function findFunctionById(String id);
	void saveOrUpdate(Function model);

}

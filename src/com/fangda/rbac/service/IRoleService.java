package com.fangda.rbac.service;

import java.util.List;

import com.fangda.rbac.domain.Role;
import com.fangda.rbac.utils.PageBean;

public interface IRoleService {

	void save(Role model, String ids);

	void pageQuery(PageBean pageBean);

	List<Role> listajax();

	Role findById(String string);

	void saveOrUpdate(Role model, String ids,String name);

	void delete(String ids);

	List<Role> findAll();

	List<Role> findRoleByUserId(String userId);

}

package com.fangda.rbac.service;

import java.util.List;

import com.fangda.rbac.domain.User;
import com.fangda.rbac.utils.PageBean;

public interface IUserService {
	User findByUsernameAndPassword(User user);

	void editPassword(String password, String id);


	void sava(User model, String[] roleIds);

	void delet(String ids);

	void edit(User model);

	void pageQuery(PageBean pageBean);

	User findUserByUsername(String username);

	List<User> findUserByRoleid(String roleid);

	User findUserById(String userId);

	void editUser(User model, String[] roleIds,String rolename);

}

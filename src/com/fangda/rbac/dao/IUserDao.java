package com.fangda.rbac.dao;

import java.util.List;

import com.fangda.rbac.dao.base.IBaseDao;
import com.fangda.rbac.domain.User;
import com.fangda.rbac.utils.PageBean;

public interface IUserDao extends IBaseDao<User> {

	User findByUsernameAndPassword(String username, String password);

	User findUserByUsername(String username);

	List<User> findUserByRoleid(String roleid);
	
}

package com.fangda.rbac.dao;

import java.util.List;

import com.fangda.rbac.dao.base.IBaseDao;
import com.fangda.rbac.domain.Role;

public interface IRoleDao extends IBaseDao<Role>{

	List<Role> findByUserId(String userId);

}

package com.fangda.rbac.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fangda.rbac.dao.IRoleDao;
import com.fangda.rbac.dao.base.impl.IBaseDaoImpl;
import com.fangda.rbac.domain.Role;

@Repository
public class RoleDaoImpl extends IBaseDaoImpl<Role> implements IRoleDao {

	public List<Role> findByUserId(String userId) {
		String hql = "select r From Role r left outer join r.users u where u.id=? ";
		List<Role> list = this.getHibernateTemplate().find(hql , userId);
		return list;
	}

}

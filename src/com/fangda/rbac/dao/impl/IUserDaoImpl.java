package com.fangda.rbac.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fangda.rbac.dao.IUserDao;
import com.fangda.rbac.dao.base.impl.IBaseDaoImpl;
import com.fangda.rbac.domain.User;

@Repository
public class IUserDaoImpl extends IBaseDaoImpl<User> implements IUserDao {

	public User findByUsernameAndPassword(String username, String password) {

		String hql = "from User u where u.username=? AND u.password=?";
		List<User> list = this.getHibernateTemplate().find(hql, username,
				password);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public User findUserByUsername(String username) {
		String queryString = "From User u where u.username =?";
		List<User> list = this.getHibernateTemplate().find(queryString,username);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}

	public List<User> findUserByRoleid(String roleid) {
		String hql = "select u From User u left outer join u.roles r where r.id =?";
		return this.getHibernateTemplate().find(hql, roleid);
	}

}

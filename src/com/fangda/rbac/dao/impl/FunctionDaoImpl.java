package com.fangda.rbac.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fangda.rbac.dao.IFunctionDao;
import com.fangda.rbac.dao.base.impl.IBaseDaoImpl;
import com.fangda.rbac.domain.Function;

@Repository
public class FunctionDaoImpl extends IBaseDaoImpl<Function> implements IFunctionDao{

	public List<Function> findListByUserid(String id) {
		String hql ="SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles r" +
				" LEFT OUTER JOIN r.users u WHERE u.id = ?";
		return this.getHibernateTemplate().find(hql, id);
	}

	public List<Function> findMenuByUserId(String id) {
		String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles r" +
				" LEFT OUTER JOIN r.users u WHERE u.id = ? AND f.generatemenu = '1' ORDER BY f.zindex DESC ";
		return this.getHibernateTemplate().find(hql, id);
	}

	public List<Function> findAllMenu() {
		String hql = "FROM Function f WHERE f.generatemenu = '1'  ORDER BY f.zindex DESC";
		return this.getHibernateTemplate().find(hql);
	}
	
}

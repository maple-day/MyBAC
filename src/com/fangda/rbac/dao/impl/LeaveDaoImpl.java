package com.fangda.rbac.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.fangda.rbac.dao.ILeaveDao;
import com.fangda.rbac.dao.base.impl.IBaseDaoImpl;
import com.fangda.rbac.domain.Leave;
import com.fangda.rbac.utils.PageBean;

@Repository
public class LeaveDaoImpl extends IBaseDaoImpl<Leave> implements ILeaveDao {

	public void pageQueryDsc(PageBean pageBean) {
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		DetachedCriteria criteria = pageBean.getDetachedCriteria();
		criteria.setProjection(Projections.rowCount());
		List<Long> list = this.getHibernateTemplate().findByCriteria(criteria);
		Long taotl = list.get(0);
		pageBean.setTotal(taotl.intValue());
		criteria.setProjection(null);
		//重置表和类的映射关系
		criteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		criteria.addOrder(Order.desc("start"));
		int firstResult = (currentPage-1)*pageSize;
		int maxResults = currentPage*pageSize;
		List rows = this.getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
		pageBean.setRows(rows);
	}

}

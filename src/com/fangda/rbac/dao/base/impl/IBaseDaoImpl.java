package com.fangda.rbac.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fangda.rbac.dao.base.IBaseDao;
import com.fangda.rbac.utils.PageBean;

public class IBaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	Class<T> type;
	public IBaseDaoImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		 type = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
		
	}
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	public void add(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	public void updata(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	public List<T> findAll() {
		String hql = "from "+ type.getSimpleName();
		List<T> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public T findById(Serializable serializable) {
		
		return this.getHibernateTemplate().get(type, serializable);
	}
	public void editPassword(String queryname, Object... objects) {
		Session session = getSession();//从本地线程去除session对象
		//使用命令查询语句获得一个查询对象
		Query query = session.getNamedQuery(queryname);
		//为hql语句赋值
		int i= 0;
		for (Object arg : objects) {
			query.setParameter(i++, arg);
		}
		query.executeUpdate();
	}
	public void pageQuery(PageBean pageBean) {
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
		int firstResult = (currentPage-1)*pageSize;
		int maxResults = currentPage*pageSize;
		List rows = this.getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
		pageBean.setRows(rows);
	}
	public void saveOrUpdata(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}
	public void pageQueryDes(PageBean pageBean) {
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
		criteria.addOrder(Order.desc("date"));
		int firstResult = (currentPage-1)*pageSize;
		int maxResults = currentPage*pageSize;
		List rows = this.getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
		pageBean.setRows(rows);
	}
}

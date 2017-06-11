package com.fangda.rbac.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class PageBean {

	private int currentPage  ; //当前页面
	private int pageSize  ; //每页的记录数
	private DetachedCriteria detachedCriteria;//离线条件查询对象，包装查询语句
	private int total ;//总记录数
	private List rows;//当前页面需要展示的数组集合
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}
	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
}

package com.fangda.rbac.dao;

import com.fangda.rbac.dao.base.IBaseDao;
import com.fangda.rbac.domain.MySystem;
import com.fangda.rbac.utils.PageBean;

public interface ISystemDao extends IBaseDao<MySystem> {
	public void pageQueryDes(PageBean pageBean);
}

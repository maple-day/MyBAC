package com.fangda.rbac.dao;

import com.fangda.rbac.dao.base.IBaseDao;
import com.fangda.rbac.domain.Leave;
import com.fangda.rbac.utils.PageBean;

public interface ILeaveDao extends IBaseDao<Leave>{

	void pageQueryDsc(PageBean pageBean);

}

package com.fangda.rbac.service;

import com.fangda.rbac.domain.Leave;
import com.fangda.rbac.utils.PageBean;

public interface ILeaveService {

	void save(Leave model);

	void pageQueryDsc(PageBean pageBean);

	void mangerAuditing(Integer check, String leaveid, String taskId);

	void HrAuditing(Integer check, String leaveid, String taskId);

}

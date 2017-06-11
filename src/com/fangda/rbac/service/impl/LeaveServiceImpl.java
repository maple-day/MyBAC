package com.fangda.rbac.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fangda.rbac.dao.ILeaveDao;
import com.fangda.rbac.domain.Leave;
import com.fangda.rbac.domain.User;
import com.fangda.rbac.service.ILeaveService;
import com.fangda.rbac.service.IUserService;
import com.fangda.rbac.utils.PageBean;

@Service
@Transactional
public class LeaveServiceImpl implements ILeaveService {

	@Autowired
	ILeaveDao leaveDao;
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;
	@Autowired
	HistoryService historyService;
	
	@Autowired
	IUserService userService;
	
	public void save(Leave model) {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		model.setUser(user);
		leaveDao.add(model);
		String businessKey = model.getId();//业务主键----等于业务表主键值---让工作框架找到业务数据
		String processDefinitionKey ="qjlc";//流程定义key
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("请假说明", model);
		runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
	}
	public void pageQueryDsc(PageBean pageBean) {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		String username = user.getUsername();
		user = userService.findUserByUsername(username);
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		detachedCriteria.createAlias("user", "u");
		detachedCriteria.add(Restrictions.eq("u.id", user.getId()));
		leaveDao.pageQueryDsc(pageBean);
	}
	public void mangerAuditing(Integer check, String leaveid, String taskId) {
		Leave leave = leaveDao.findById(leaveid);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("check", check);
		String processInstanceId = task.getProcessInstanceId();
		taskService.complete(taskId,variables);
		if(check==0){
			leave.setCheckprocess("0");
			historyService.deleteHistoricProcessInstance(processInstanceId);
		}
	}
	public void HrAuditing(Integer check, String leaveid, String taskId) {
		Leave leave = leaveDao.findById(leaveid);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("check", check);
		String processInstanceId = task.getProcessInstanceId();
		taskService.complete(taskId,variables);
		leave.setMangercheck("1");
		if(check==0){
			leave.setCheckprocess("0");
			historyService.deleteHistoricProcessInstance(processInstanceId);
		}
	}
}

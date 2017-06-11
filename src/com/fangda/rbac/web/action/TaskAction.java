package com.fangda.rbac.web.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fangda.rbac.domain.Leave;
import com.fangda.rbac.domain.Role;
import com.fangda.rbac.domain.User;
import com.fangda.rbac.service.ILeaveService;
import com.fangda.rbac.service.IRoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class TaskAction extends ActionSupport {

	@Autowired
	TaskService taskService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	ILeaveService leaveService;
	@Autowired
	IRoleService roleService;
	
	private Integer check;
	public void setCheck(Integer check) {
		this.check = check;
	}

	private String taskId;
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	
	@RequiresPermissions(value="grouptask")
	public String findGroupTask(){
		TaskQuery taskQuery = taskService.createTaskQuery();
		String candidateUser = user.getId();
		taskQuery.taskCandidateUser(candidateUser);
		List<Task> list = taskQuery.list();
		ActionContext.getContext().getValueStack().set("list", list);
		return "grouptasklist";
	}
	
	public String showData() throws IOException{
		Map<String, Object> variables = taskService.getVariables(taskId);
		Leave object = (Leave) variables.get("请假说明");
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(object.toString());
		return NONE;
	}
	
	//拾取任务
	public String takeTask(){
		taskService.claim(taskId, user.getId());
		return "togrouptasklist";
	}
	
	@RequiresPermissions(value="personaltask")
	public String findPersonalTask(){
		TaskQuery taskQuery = taskService.createTaskQuery();
		String assignee = user.getId();
		taskQuery.taskAssignee(assignee );//过滤个人任务
		List<Task> list = taskQuery.list();
		ActionContext.getContext().getValueStack().set("list", list);
		return "personaltasklist";
	}
	
	//办理任务
	public String mangerAuditing(){
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();//根据任务id查询任务对象
		String processInstanceId = task.getProcessInstanceId();//根据任务对象查询流程实例id
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		String leaveid = processInstance.getBusinessKey();
		leaveService.mangerAuditing(check,leaveid,taskId);
		return "auditing";
	}
	
	public String HrAuditing(){
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();//根据任务id查询任务对象
		String processInstanceId = task.getProcessInstanceId();//根据任务对象查询流程实例id
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		String leaveid = processInstance.getBusinessKey();
		leaveService.HrAuditing(check,leaveid,taskId);
		return "auditing";
	}
}

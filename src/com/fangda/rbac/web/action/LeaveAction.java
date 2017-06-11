package com.fangda.rbac.web.action;

import java.io.IOException;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fangda.rbac.domain.Leave;
import com.fangda.rbac.service.ILeaveService;
import com.fangda.rbac.web.action.Base.BaseAction;

@Controller
@Scope("prototype")
public class LeaveAction extends BaseAction<Leave> {

	@Autowired
	ILeaveService leaveService;
	@Autowired
	RuntimeService runtimeService;
	
	
	//启动请假流程  ---  Service中启动流程
	public String start(){
		model.setCheckprocess("1");//启动流程
		leaveService.save(model);
		return "list";
	}
	
	public String pageQuery() throws IOException{
		leaveService.pageQueryDsc(pageBean);
		writePagebean2Json(pageBean, new String[]{"detachedCriteria","pageSize","currentPage","user"});
		return NONE;
	}
}

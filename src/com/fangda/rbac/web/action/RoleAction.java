package com.fangda.rbac.web.action;

import java.io.IOException;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fangda.rbac.domain.Role;
import com.fangda.rbac.service.IRoleService;
import com.fangda.rbac.web.action.Base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	@Autowired
	IRoleService roleService;
	@Autowired
	IdentityService identityService;
	
	public String ids;
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String oldname;
	
	public void setOldname(String oldname) {
		this.oldname = oldname;
	}

	//把角色和acticiti的角色关联
	public String add(){
		roleService.save(model,ids);
		return "list";
	}
	
	public String pageQuery() throws IOException{
		roleService.pageQuery(pageBean);
		writePagebean2Json(pageBean, new String[]{"users","functions"});
		return NONE;
	}
	
	public String listajax() throws IOException{
		List<Role>  list=  roleService.listajax();
		writeList2Json(list, new String[]{"users","functions"});
		return NONE;
	}
	
	public String edit(){
		String id = model.getId();
		Role role = roleService.findById(id);
		ActionContext.getContext().getValueStack().set("role", role);
		return "edit";
	}
	public String roleedit(){
		roleService.saveOrUpdate(model,ids,oldname);
		return "list";
	}
	public String delete() throws IOException{
		String flag = "1";
		try {
			roleService.delete(ids);
		} catch (Exception e) {
			flag = "0";
		}
		ServletActionContext.getResponse().setContentType("text/text;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}
}

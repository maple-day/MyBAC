package com.fangda.rbac.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fangda.rbac.domain.Function;
import com.fangda.rbac.service.IFunctionService;
import com.fangda.rbac.web.action.Base.BaseAction;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {

	@Autowired
	IFunctionService functionService;
	
	String ids;
	public void setIds(String ids) {
		this.ids = ids;
	}
	public void setFunctionService(IFunctionService functionService) {
		this.functionService = functionService;
	}
	public String pageQuery() throws IOException{
		String page = model.getPage();
		pageBean.setCurrentPage(Integer.parseInt(page));
		functionService.pageQuery(pageBean);
		writePagebean2Json(pageBean, new String[]{"detachedCriteria","pageSize","currentPage","roles","functions","function"});
		return NONE;
	}
	public String listajax() throws IOException{
		List<Function> list = functionService.findAll();
		writeList2Json(list, new String[]{"function","functions","roles"});
		return NONE;
	}
	
	public String add(){
		functionService.sava(model);
		return "list";
	}
	public String findMenu() throws IOException{
		List<Function> list = functionService.findMenu();
		String[] excludes = new String[]{"functions","function","roles"};
		this.writeList2Json(list, excludes);
		return NONE;
	}
	public String delete() throws IOException{
		String flag = "1";
		try {
			functionService.delete(ids);
		} catch (Exception e) {
			flag = "0";
		}
		ServletActionContext.getResponse().setContentType("text/text;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}

	public String edit(){
		String id = model.getId();
		Function function = functionService.findFunctionById(id);
		ActionContext.getContext().getValueStack().set("functions", function);
		return "edit";
	}
	
	public String editsave(){
		if(model.getId().length()>30){
			functionService.saveOrUpdate(model);
			return "list";
		}else{
			String id = model.getId();
			Function function = functionService.findFunctionById(id);
			ActionContext.getContext().getValueStack().set("functions", function);
			ActionContext.getContext().getValueStack().set("flag", "基础权限不能修改!");
			return "edit";
		}
	}
}

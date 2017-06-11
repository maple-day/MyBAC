package com.fangda.rbac.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.IdentityService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fangda.rbac.domain.Role;
import com.fangda.rbac.domain.User;
import com.fangda.rbac.service.IRoleService;
import com.fangda.rbac.service.ISystemService;
import com.fangda.rbac.service.IUserService;
import com.fangda.rbac.utils.IpUtils;
import com.fangda.rbac.utils.MD5Utils;
import com.fangda.rbac.web.action.Base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	@Autowired
	IUserService iUserService;
	@Autowired
	ISystemService systemService;
	@Autowired 
	IdentityService identityService;
	@Autowired
	IRoleService iRoleService;
	@Autowired
	IRoleService roleService;
	
	//获取验证码
	public String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String[] roleIds;
	
	
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	public String ids;
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String login(){
		String code = (String) ServletActionContext.getRequest().getSession().getAttribute("key");//获取seesion中的验证码
		//对用户验证码进行校验
		if(StringUtils.isNotBlank(code)&&code.equalsIgnoreCase(checkcode)){
			Subject subject = SecurityUtils.getSubject();
			String password = model.getPassword();//对密码进行md5加密
			password = MD5Utils.md5(password);
			AuthenticationToken token = new UsernamePasswordToken(model.getUsername(),password);
			try {
				subject.login(token);//使用shrio进行校验
			} catch (UnknownAccountException e) {
				this.addActionError(this.getText("usernamenotfound"));//用户名不存在
				return "login";
			}catch (Exception e) {
				this.addActionError(this.getText("loginError"));//用户名或者密码错误
				return "login";
			}
			User user = (User) subject.getPrincipal();
			HttpServletRequest request = ServletActionContext.getRequest();
			ActionContext.getContext().getValueStack().set("username", user.getUsername());
			systemService.save(user.getId(),user.getUsername(),IpUtils.getIpAddr(request));//保存用户登录系统的信息、系统日志管理
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);//把用户信息保存到session中
			return "home";
		}else{
			this.addActionError(this.getText("validateCodeError"));//验证码错误
			return "login";
		}
	}
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}
	public String editPassword() throws IOException{
		User u = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		String password = model.getPassword();
		password = MD5Utils.md5(password);
		int flag = 1;
		try {
			iUserService.editPassword(password,u.getId());
		} catch (Exception e) {
			e.printStackTrace();
			//密码修改失败
			flag = 0;
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}
	
	public String pageQuery() throws IOException{
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		String username = model.getUsername();
		String gender = model.getGender();
		if(StringUtils.isNotBlank(username)){
			detachedCriteria.add(Restrictions.like("username", "%"+username+"%"));
		}
		if(StringUtils.isNotBlank(gender)){
			detachedCriteria.add(Restrictions.like("gender", "%"+gender+"%"));
		}
		iUserService.pageQuery(pageBean);
		writePagebean2Json(pageBean, new String[]{"detachedCriteria","pageSize","currentPage","roles","leaves"});
		return NONE;
	}
	
	//保存用户时将把用户和Activiti中的用户关联-----service 中执行
	public String add(){
		String password = model.getPassword();
		model.setPassword(MD5Utils.md5(password));
		iUserService.sava(model,roleIds);
		return "list";
	}
	
	public String delete() throws IOException{
		String flag = "1";
		try {
			iUserService.delet(ids);
		} catch (Exception e) {
			flag = "0";
		}
		ServletActionContext.getResponse().setContentType("text/text;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}
	
	public String edit() throws IOException{
		String password = model.getPassword();
		model.setPassword(MD5Utils.md5(password));
		iUserService.edit(model);
		return "list";
	}
	
	public String select(){
		return NONE;
	}
	
	public String search() {
		String roleid = model.getId();
		List<User> list = iUserService.findUserByRoleid(roleid);
		ActionContext.getContext().getValueStack().set("list", list);
		return SUCCESS;
	}
	
	
	public String editUser(){
		String userId = model.getId();
		User user = iUserService.findUserById(userId);
		List<Role> list = roleService.findRoleByUserId(userId);
		ActionContext.getContext().getValueStack().set("user",user);
		ActionContext.getContext().getValueStack().set("list",list);
		return "edit";
	}
	public String editSave(){
		iUserService.editUser(model,roleIds,rolenames);
		return "list";
	}
	private String rolenames;
	public void setRolenames(String rolenames) {
		this.rolenames = rolenames;
	}
}

package com.fangda.rbac.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.fangda.rbac.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class RbacUserInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if(user==null){
			return "login";
		}
		return arg0.invoke();
	}

}

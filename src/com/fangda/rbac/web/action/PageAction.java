package com.fangda.rbac.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.util.TokenHelper;
import org.junit.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import freemarker.template.SimpleDate;

@Controller
@Scope("prototype")
public class PageAction extends ActionSupport {
	
	@RequiresPermissions(value="usermanger")
	public String userindex(){
		return SUCCESS;
	};
	
	@RequiresPermissions(value="leave")
	public String leave(){
		return SUCCESS;
	};
	
	@RequiresPermissions(value="functionmanger")
	public String function(){
		return SUCCESS;
	};
	
	@RequiresPermissions(value="rolemanger")
	public String role(){
		return SUCCESS;
	};
	
	@RequiresPermissions(value="systemmanger")
	public String system(){
		return SUCCESS;
	};
	public String home(){
		return SUCCESS;
	}
	public String msg(){
		return SUCCESS;
	}
	public String userlist(){
		return SUCCESS;
	}
	public String leave_reson(){
		return SUCCESS;
	}
	public String role_add(){
		return SUCCESS;
	}
	public String function_add(){
		return SUCCESS;
	}
	
	public String processdefinition_deploy(){
		return SUCCESS;
	}
	public String gonggao(){
		return SUCCESS;
	}
	public String yujing(){
		return SUCCESS;
	}
	public String bug(){
		return SUCCESS;
	}
	public String daiban(){
		return SUCCESS;
	}
	public String userinfo(){
		return SUCCESS;
	}
	public String role_search(){
		return SUCCESS;
	}

}

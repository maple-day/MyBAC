package com.fangda.rbac.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.fangda.rbac.domain.Function;
import com.fangda.rbac.domain.User;
import com.fangda.rbac.service.IFunctionService;
import com.fangda.rbac.service.IUserService;

public class MyRBACRealm extends AuthorizingRealm {

	@Autowired
	IUserService iUserService;
	@Autowired
	IFunctionService functionService;

	
	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken uptoken = (UsernamePasswordToken) token;
		String username = uptoken.getUsername();//获取用户登录的用户名
		User user = iUserService.findUserByUsername(username);//根据用户名查找用户信息
		if(user!=null){
			String password = user.getPassword();
			AuthenticationInfo info = new SimpleAuthenticationInfo(user, password, this.getClass().getSimpleName());
			return info;//返回用户信息让shiro框架j
		}else{
			return null;
		}
	}
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		User user = (User) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<Function> list = null;
		if(user.getUsername().equals("admin")){
			list = functionService.findAll();
		}else {
			list = functionService.findFunctionByUserId(user.getId());
		}
		for (Function function : list) {
			info.addStringPermission(function.getCode());
		}
		return info;
	}


}

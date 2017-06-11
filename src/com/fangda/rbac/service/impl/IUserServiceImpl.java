package com.fangda.rbac.service.impl;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fangda.rbac.dao.IUserDao;
import com.fangda.rbac.domain.Role;
import com.fangda.rbac.domain.User;
import com.fangda.rbac.service.IRoleService;
import com.fangda.rbac.service.IUserService;
import com.fangda.rbac.utils.MD5Utils;
import com.fangda.rbac.utils.PageBean;
@Service
@Transactional
public class IUserServiceImpl implements IUserService{
	
	@Autowired
	IUserDao iUserDao;
	@Autowired 
	IdentityService identityService;
	@Autowired
	IRoleService roleService;
	
	
	public User findByUsernameAndPassword(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		password = MD5Utils.md5(password);
		return iUserDao.findByUsernameAndPassword(username,password);
	}
	public void editPassword(String password, String id) {
		iUserDao.editPassword("editPassword",password,id);
	}
	public void pageQuery(PageBean pageBean) {
		iUserDao.pageQuery(pageBean);
	}
	public void sava(User model, String[] roleIds) {
		iUserDao.add(model);
		org.activiti.engine.identity.User atuser = new UserEntity(model.getId());
		identityService.saveUser(atuser);
		if(roleIds!=null){
			for (String id : roleIds) {
				Role role = roleService.findById(id);
				//Role role = new Role(id);
				model.getRoles().add(role);
				identityService.createMembership(atuser.getId(), role.getName());
			}
		}
	}
	public void delet(String ids) {
		String[] idss = ids.split(",");
		for (String id : idss) {
			User user = iUserDao.findById(id);
			iUserDao.delete(user);
		}
	}
	public void edit(User model) {
		iUserDao.saveOrUpdata(model);
	}
	public User findUserByUsername(String username) {
		return iUserDao.findUserByUsername(username);
	}
	public List<User> findUserByRoleid(String roleid) {
		
		return iUserDao.findUserByRoleid(roleid);
	}
	public User findUserById(String userId) {
		return iUserDao.findById(userId);
	}
	public void editUser(User model, String[] roleIds,String rolename) {
		iUserDao.saveOrUpdata(model);
		if(StringUtils.isNotBlank(rolename)){
			Role r = roleService.findById(rolename);
			identityService.deleteMembership(model.getId(), r.getName());
			model.getRoles().remove(r);
		}
		for (String id : roleIds) {
			Role role = roleService.findById(id);
			model.getRoles().add(role);
			identityService.createMembership(model.getId(), role.getName());
		}
	}
	
}

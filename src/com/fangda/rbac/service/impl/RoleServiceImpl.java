package com.fangda.rbac.service.impl;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fangda.rbac.dao.IRoleDao;
import com.fangda.rbac.domain.Function;
import com.fangda.rbac.domain.Role;
import com.fangda.rbac.service.IRoleService;
import com.fangda.rbac.utils.PageBean;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Autowired
	IRoleDao roleDao;
	@Autowired
	IdentityService identityService;
	
	
	public void save(Role model, String ids) {
		roleDao.add(model);
		Group group = new GroupEntity(model.getName());
		identityService.saveGroup(group );
		if(ids!=null){
			String[] idss = ids.split(",");
			for (String id : idss) {
				Function function = new Function(id);
				model.getFunctions().add(function);
			}
		}
	}

	public void pageQuery(PageBean pageBean) {
		roleDao.pageQuery(pageBean);
	}

	public List<Role> listajax() {
		return roleDao.findAll();
	}

	public Role findById(String string) {
		return roleDao.findById(string);
	}

	public void saveOrUpdate(Role model, String ids,String name) {
		roleDao.saveOrUpdata(model);
		identityService.deleteGroup(name);
		Group group = new GroupEntity(model.getName());
		identityService.saveGroup(group);
		if(ids!=null){
			String[] idss = ids.split(",");
			for (String id : idss) {
				Function function = new Function(id);
				model.getFunctions().add(function);
			}
		}
	}

	public void delete(String ids) {
		Role role = roleDao.findById(ids);
		roleDao.delete(role);
	}

	public List<Role> findAll() {
		
		return roleDao.findAll();
	}

	public List<Role> findRoleByUserId(String userId) {
		
		return roleDao.findByUserId(userId);
	}

}

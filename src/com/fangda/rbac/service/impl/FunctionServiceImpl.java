package com.fangda.rbac.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fangda.rbac.dao.IFunctionDao;
import com.fangda.rbac.domain.Function;
import com.fangda.rbac.domain.User;
import com.fangda.rbac.service.IFunctionService;
import com.fangda.rbac.utils.PageBean;

@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

	@Autowired 
	IFunctionDao functionDao;
	
	public void pageQuery(PageBean pageBean) {
		functionDao.pageQuery(pageBean);
	}
	public List<Function> findAll() {
		return functionDao.findAll();
	}
	public void sava(Function model) {
		Function function = model.getFunction();
		String id = function.getId();
		if(function!=null&&id.equals("")){
			model.setFunction(null);
		}
		functionDao.add(model);
	}
	public List<Function> findFunctionByUserId(String id) {
		return functionDao.findListByUserid(id);
	}
	public List<Function> findMenu() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		List<Function> list = null;
		if(user.getUsername().equals("admin")){
			list = functionDao.findAllMenu();
		}else {
			list = functionDao.findMenuByUserId(user.getId());
		}
		return list;
	}
	public void delete(String ids) {
		if(StringUtils.isNotBlank(ids)){
			String[] split = ids.split(",");
			for (String id : split) {
				Function function = functionDao.findById(id);
				functionDao.delete(function);
			}
		
		}
	}
	public Function findFunctionById(String id) {
		
		return functionDao.findById(id);
	}
	
	public void saveOrUpdate(Function model) {
		
		functionDao.saveOrUpdata(model);
		
	}

}

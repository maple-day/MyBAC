package com.fangda.rbac.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fangda.rbac.dao.ISystemDao;
import com.fangda.rbac.domain.MySystem;
import com.fangda.rbac.service.ISystemService;
import com.fangda.rbac.utils.PageBean;

@Service
@Transactional
public class SystemServiceImpl implements ISystemService {

	@Autowired
	ISystemDao systemDao;
	public void pageQuery(PageBean pageBean) {
		systemDao.pageQueryDes(pageBean);
	}
	public void save(String userid, String username, String ipAddr) {
		Timestamp date = new Timestamp(System.currentTimeMillis());   
		MySystem system = new MySystem(ipAddr, date, username, userid);
		systemDao.add(system);
	
	}
	public List<MySystem> findAllList() {
		
		return systemDao.findAll();
	}
	
}

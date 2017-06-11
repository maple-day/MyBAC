package com.fangda.rbac.service;

import java.util.List;

import com.fangda.rbac.domain.MySystem;
import com.fangda.rbac.utils.PageBean;

public interface ISystemService {

	void pageQuery(PageBean pageBean);

	void save(String id, String username, String ipAddr);

	List<MySystem> findAllList();


}

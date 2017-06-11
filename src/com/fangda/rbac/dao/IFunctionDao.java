package com.fangda.rbac.dao;

import java.util.List;

import com.fangda.rbac.dao.base.IBaseDao;
import com.fangda.rbac.domain.Function;

public interface IFunctionDao extends IBaseDao<Function> {

	List<Function> findListByUserid(String id);

	List<Function> findMenuByUserId(String id);

	List<Function> findAllMenu();


}

package com.fangda.rbac.web.action.Base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.fangda.rbac.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	

	protected PageBean pageBean = new PageBean();
	DetachedCriteria criteria =null;
	
	
	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}

	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}
	
	protected T model;

	public BaseAction() {
		ParameterizedType genericSuperclass = null;
		if(this.getClass().getGenericSuperclass() instanceof ParameterizedType)	{
			genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		}else {
			genericSuperclass = (ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass();
		}
		 Class<T> type = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
		 criteria = DetachedCriteria.forClass(type);
		 pageBean.setDetachedCriteria(criteria);
		 try {
			 model = type.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public T getModel() {
		return model;
	}
	
	public void writePagebean2Json(PageBean pageBean,String[] excludes) throws IOException{
		JsonConfig config = new JsonConfig();
		config.setExcludes(excludes);
		JSONObject jsonObject = JSONObject.fromObject(pageBean, config);
		String json = jsonObject.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
	public void writeList2Json(List list,String[] excludes) throws IOException{
		JsonConfig config = new JsonConfig();
		config.setExcludes(excludes);
		 JSONArray jsonArray = JSONArray.fromObject(list, config);
		String json = jsonArray.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
}

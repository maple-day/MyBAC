package com.fangda.rbac.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class ProcessDefinitionAction extends ActionSupport {

	@Autowired
	RepositoryService repositoryService;
	
	public File zipFile;
	public void setZipFile(File zipFile) {
		this.zipFile = zipFile;
	}
	public String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	/*
	 * 查询最新版本流程定义列表数据
	 */
	@RequiresPermissions(value="processdefinitionumanger")
	public String list(){
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		ProcessEngine processEngine = (ProcessEngine) context.getBean("processEngine");
//		RepositoryService repositoryService = processEngine.getRepositoryService();
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		query.latestVersion();//查询最新版本
		query.orderByProcessDefinitionName().desc();//排序
		List<ProcessDefinition> list = query.list();
		ActionContext.getContext().getValueStack().set("list", list);
		return "list";
	}
	
	public String deploy() throws FileNotFoundException{
		try {
			DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
			deploymentBuilder.addZipInputStream(new ZipInputStream(new FileInputStream(zipFile)));
			deploymentBuilder.deploy();
			return "toList";
		} catch (Exception e) {
			ActionContext.getContext().getValueStack().set("error", "请选择正确的文件！！！");
			return "error";
		}
	}
	
	//展示流程图片
	public String showpng(){
		InputStream pngStream = repositoryService.getProcessDiagram(id);
		ActionContext.getContext().getValueStack().set("pngStream", pngStream);
		return "showpng";
	}
	//删除定义
	public String delete(){
		String deltag = "0";
		//根据流程定义id查询部署id
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		query.processDefinitionId(id);//过滤
		ProcessDefinition result = query.singleResult();
		String deploymentId = result.getDeploymentId();
		try {
			repositoryService.deleteDeployment(deploymentId);
		} catch (Exception e) {
			//当前要删除的流程定义正在使用
			deltag = "1";
			ActionContext.getContext().getValueStack().set("deltag", deltag);
			ProcessDefinitionQuery query2 = repositoryService
					.createProcessDefinitionQuery();
			query2.latestVersion();// 查询最新版本
			query2.orderByProcessDefinitionName().desc();// 排序
			List<ProcessDefinition> list = query2.list();// 执行查询
			// 压栈
			ActionContext.getContext().getValueStack().set("list", list);
			return "list";
		}
		return "toList";
	}

	
}

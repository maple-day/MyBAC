package com.fangda.rbac.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fangda.rbac.domain.MySystem;
import com.fangda.rbac.service.ISystemService;
import com.fangda.rbac.utils.FileUtils;
import com.fangda.rbac.web.action.Base.BaseAction;
@Controller
@Scope("prototype")
public class SystemAction extends BaseAction<MySystem> {

	@Autowired
	ISystemService systemService;
	
	public String pageQuery() throws IOException{
		systemService.pageQuery(pageBean);
		writePagebean2Json(pageBean, new String[]{"detachedCriteria","pageSize","currentPage"});
		return NONE;
	}
	public String exportXls() throws IOException{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("系统日志");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("用户id");
		row.createCell(1).setCellValue("用户名称");
		row.createCell(2).setCellValue("登陆ip");
		row.createCell(3).setCellValue("登陆时间");
		List<MySystem> list = systemService.findAllList();
		for (MySystem mySystem : list) {
			HSSFRow createRow = sheet.createRow(sheet.getLastRowNum()+1);
			createRow.createCell(0).setCellValue(mySystem.getUserid());
			createRow.createCell(1).setCellValue(mySystem.getUser());
			createRow.createCell(2).setCellValue(mySystem.getIp());
			createRow.createCell(3).setCellValue(mySystem.getLoginDate());
		}
		String filename = "系统日志.xls";
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
		ServletActionContext.getResponse().setContentType(contentType);
		ServletActionContext.getResponse().setHeader("content-disposition", "attchment;filename="+filename);
		workbook.write(out);
		return NONE;
	}
	
}

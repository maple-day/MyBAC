<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$("#grid").datagrid({
			toolbar : [
				{
					id : 'add',
					text : '申请请假',
					iconCls : 'icon-add',
					handler : function(){
						location.href='${pageContext.request.contextPath}/page_leave_leave_reson.action';
					}
				} ,
				<shiro:hasPermission name="processinstancemanger">
					{
						id : 'add',
						text : '测试按钮权限',
						iconCls : 'icon-add',
					}   
				</shiro:hasPermission>       
			],
			fit:'true',
			pageList: [5,8,15],
			pagination : true,
			url : '${pageContext.request.contextPath}/leaveAction_pageQuery.action',
			columns : [[
			  {
				  field : 'id',
				  checkbox:true,
			  },
			  {
				  field : 'day',
				  title : '请假天数',
				  width : 200
			  },  
			  {
				  field : 'startDate',
				  title : '开始时间',
				  width : 200
			  },  
			  {
				  field : 'endDate',
				  title : '结束时间',
				  width : 200
			  }, 
			  {
				  field : 'mangercheck',
				  title : '是否准假',
				  width : 200,
				  formatter:function(data){
				  	if(data=="1"){
				  		return "准假";
				  	}else{
				  		return "不批假";
				  	}
				  }
			  },  
			]]
		});
	});
</script>	
</head>
<body class="easyui-layout">
<div data-options="region:'center'">
	<table id="grid"></table>
</div>

</body>
</html>
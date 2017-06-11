<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
					text : '添加权限',
					iconCls : 'icon-add',
					handler : function(){
						location.href='${pageContext.request.contextPath}/page_admin_function_add.action';
					}
				},           
				{
					id : 'delete',
					text : '删除权限',
					iconCls : 'icon-cancel',
					handler : function(){
						var items = $('#grid').datagrid('getSelections');
						var array = new Array();
						for(var i = 0 ; i<items.length;i++){
							var id = items[i].id;
							array.push(id);
						}
						var ids = array.join(",");
						//location.href='${pageContext.request.contextPath}/functionAction_delete.action?ids='+ids;
						var url = '${pageContext.request.contextPath}/functionAction_delete.action';
						$.post(url,{ids:ids},function(data){
						if(data=="1"){
							$.messager.alert("提示","删除成功。","info");
						}else{
							$.messager.alert("提示","哎,删除失败！！","info");
						}
						$("#grid").datagrid("reload");
					});
					}
				}  ,         
				{
					id : 'edit',
					text : '修改权限',
					iconCls : 'icon-edit',
					handler : function(){
						var items = $('#grid').datagrid('getSelections');
						if(items.length ==1){
							item = $('#grid').datagrid('getSelected');
							location.href="${pageContext.request.contextPath}/functionAction_edit.action?id="+item.id;
						}else{
							$.messager.alert("消息提示","请选择一个,不要多选和不选！！","info");
						}
					}
				}         
			],
			fit:'true',
			pageList: [5,8,15],
			pagination : true,
			url : '${pageContext.request.contextPath}/functionAction_pageQuery.action',
			columns : [[
			  {
				  field : 'id',
				  checkbox:true,
				  width : 100
			  },
			  {
				  field : 'name',
				  title : '名称',
				  width : 200
			  },  
			  {
				  field : 'description',
				  title : '描述',
				  width : 200
			  },  
			  {
				  field : 'generatemenu',
				  title : '是否生成菜单',
				  width : 200,
				  formatter:function(data){
				  	if(data=="1"){
				  		return "生成菜单";
				  	}else{
				  		return "不生成菜单";
				  	}
				  }
			  },  
			  {
				  field : 'zindex',
				  title : '优先级',
				  width : 100
			  },  
			  {
				  field : 'page',
				  title : '路径',
				  width : 300
			  }
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
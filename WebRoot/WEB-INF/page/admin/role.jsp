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
<!-- 导入ztree类库 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css"
	type="text/css" />
<script
	src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"
	type="text/javascript"></script>	
<script type="text/javascript">
	$(function(){
		var item = new Object();
		// 数据表格属性
		$("#grid").datagrid({
			toolbar : [
				{
					id : 'button-view',	
					text : '修改',
					iconCls : 'icon-edit',
					handler : doView
				}, {
					id : 'add',
					text : '添加角色',
					iconCls : 'icon-add',
					handler : function(){
						location.href='${pageContext.request.contextPath}/page_admin_role_add.action';
					}
				}, {
					id : 'button-delete',
					text : '删除',
					iconCls : 'icon-cancel',
					handler : doDelete
				},{
					id : 'search',
					text : '查看',
					iconCls : 'icon-search',
					handler : function(){
						var items = $('#grid').datagrid('getSelections');
						if(items.length ==1){
							var item = $('#grid').datagrid('getSelected');
							//$("#editUserWindow").window("open");
							//$("#editUserForm").form('load',item);
							location.href='${pageContext.request.contextPath}/userAction_search.action?id='+item.id;
						}else{
							$.messager.alert("消息提示","请选择一个,不要多选和不选！！","info");
						}
					}
				
				}
			],
			fit:true,
			pageList: [8,10,17],
			pagination : true,
			url : '${pageContext.request.contextPath}/roleAction_pageQuery.action',
			columns : [[
				{
					field : 'id',
					checkbox:true,
					title : '编号',
					width : 300
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
				} 
			]]
		});
		
		function doView() {
			var items = $('#grid').datagrid('getSelections');
			if(items.length ==1){
				item = $('#grid').datagrid('getSelected');
				location.href="${pageContext.request.contextPath}/roleAction_edit.action?id="+item.id;
			}else{
				$.messager.alert("消息提示","请选择一个,不要多选和不选！！","info");
			}
		}
		
		function doDelete() {
				var items = $('#grid').datagrid('getSelections');
				if(items.length>0){
					var array = new Array();
					for(var i=0; i<items.length; i++){
					    array.push(items[i].id);	    
					}
					var ids = array.join(",");
					var url = "${pageContext.request.contextPath}/roleAction_delete.action";
					$.post(url,{ids:ids},function(data){
						if(data=="1"){
							$.messager.alert("提示","删除成功。","info");
						}else{
							$.messager.alert("提示","哎,删除失败！！","info");
						}
						$("#grid").datagrid("reload");
					});
				}else{
					$.messager.alert("提示","哎,请选择行删除呀！！","info");
				}
		}
	
	});
</script>	
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table id="grid"></table>
	</div>
</body>
</html>
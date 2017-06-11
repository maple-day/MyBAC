<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
<script
	src="${pageContext.request.contextPath }/js/outOfBounds.js"
	type="text/javascript"></script>
<script type="text/javascript">
	// 工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查看',
		iconCls : 'icon-search',
		handler : function () {
			$.messager.alert("消息提示","查看按钮！","info");
		}
	},{
		id : 'button-view',	
		text : '点击',
		iconCls : 'icon-search',
		handler : function () {
			$.messager.alert("消息提示","点击按钮！","info");
		}
	},
	<shiro:hasPermission name="import">
		 {
			id : 'button-add',
			text : '导出',
			iconCls : 'icon-add',
			handler : exportXls
		}
	</shiro:hasPermission>
	];
	//定义冻结列
	var frozenColumns = [ [ {
							field : 'id',
							checkbox : true,
						}, {
							field : 'user',
							title : '用户名',
							width : 80,
						} ] ];

// 定义标题栏
	var columns = [ [ {
					field : 'loginDate',
					title : '登陆时间',
					width : 150,
					align : 'center'
				}, {
					field : 'ip',
					title : 'ip地址',
					width : 120,
					align : 'center'
				}, {
					field : 'userid',
					title : '用户id',
					width : 280,
					align : 'center'
				} ] ];
	$(function(){
		// 初始化 datagrid
		// 创建grid
		
		$('#grid').datagrid( {
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/systemAction_pageQuery.action",
			pageList: [8,10,17],
			pagination : true,
			frozenColumns : frozenColumns,
			idField : 'id', 
			columns : columns,
			onClickRow : onClickRow,
			onDblClickRow : doDblClickRow
		});
		
		$("body").css({visibility:"visible"});
		
		$('#editUserWindow').window({
	        title: '修改取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
	});
	// 双击
	function doDblClickRow(rowIndex, rowData) {
		//var items = $('#grid').datagrid('selectRow',rowIndex);
		//doView();
		$("#editUserWindow").window("open");
		$("#editUserForm").form('load',rowData);
	}
	// 单击
	function onClickRow(rowIndex){

	}
	
	function exportXls() {
		window.location.href = "${pageContext.request.contextPath}/systemAction_exportXls.action";
	}

	function doView() {
		var items = $('#grid').datagrid('getSelections');
		if(items.length ==1){
			var item = $('#grid').datagrid('getSelected');
			$("#editUserWindow").window("open");
			$("#editUserForm").form('load',item);
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
			var url = "${pageContext.request.contextPath}/systemAction_delete.action";
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
	
</script>		
</head>
<body class="easyui-layout" style="visibility:hidden;">
    <div region="center" border="false">
    	<table id="grid"></table>
	</div>
</body>
</html>
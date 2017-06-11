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
<script
	src="${pageContext.request.contextPath }/js/outOfBounds.js"
	type="text/javascript"></script>
<script type="text/javascript">
	// 工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '修改',
		iconCls : 'icon-edit',
		handler : doView
	},  {
		id : 'button-view',	
		text : '修改用户',
		iconCls : 'icon-edit',
		handler : doEdit
	}, {
		id : 'button-add',
		text : '新增',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	}];
	//定义冻结列
	var frozenColumns = [ [ {
							field : 'id',
							checkbox : true,
						}, {
							field : 'username',
							title : '名称',
							width : 80,
						} ] ];

// 定义标题栏
	var columns = [ [ {
					field : 'gender',
					title : '性别',
					width : 60,
					align : 'center'
				}, {
					field : 'myBirthday',
					title : '生日',
					width : 120,
					align : 'center'
				}, {
					field : 'salary',
					title : '工资',
					width : 80,
					align : 'center'
				} , {
					field : 'telephone',
					title : '电话',
					width : 100,
				} , {
					field : 'role',
					title : '角色',
					width : 100,
				} , {
					field : 'remark',
					title : '描述',
					width : 800,
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
			url : "${pageContext.request.contextPath}/userAction_pageQuery.action",
			pageList: [3,5,10],
			pagination : true,
			frozenColumns : frozenColumns,
			idField : 'id', 
			columns : columns,
			onClickRow : onClickRow,
			onDblClickRow : doDblClickRow
		});
		
		$("body").css({visibility:"visible"});
		
		 // 修改用户窗口
		$('#editUserWindow').window({
	        title: '修改用户',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		var reg = /^1[3|4|5|7|8|9][0-9]{9}$/;
		$.extend($.fn.validatebox.defaults.rules, {    
		    tellnumber: {    
		        validator: function(value, param){    
		            return reg.test(value);    
		        },    
		        message: '请输入正确的手机号码！'   
		    }    
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
	
	function doAdd() {
		location.href="${pageContext.request.contextPath}/page_admin_userinfo.action";
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
	function doEdit() {
		var items = $('#grid').datagrid('getSelections');
		if(items.length ==1){
			var item = $('#grid').datagrid('getSelected');
			//$("#editUserWindow").window("open");
			//$("#editUserForm").form('load',item);
			location.href="${pageContext.request.contextPath}/userAction_editUser.action?id="+item.id;
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
			var url = "${pageContext.request.contextPath}/userAction_delete.action";
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
	<!-- 修改 窗口-->	
	<div class="easyui-window" title="对用户进行添加或者修改" id="editUserWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
				<script type="text/javascript">
					$(function(){
						$("#edit").click(function(){
							var t = $("#editUserForm").form("validate");
							if(t){
								$("#editUserForm").submit();
								$("#editUserWindow").window("close");
								$("#grid").datagrid("reload");
							}
						});
					});
				</script>
			</div>
		</div>
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editUserForm"  method="post" action="${pageContext.request.contextPath}/userAction_edit.action">
				<input type="hidden" name="id" class="easyui-validatebox" required="true"/>
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">用户信息</td>
					</tr>
					<tr>
						<td>用户名</td>
						<td><input type="text" name="username" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>口令:</td>
						<td>
							<input type="password" name="password" id="password" class="easyui-validatebox" required="true" validType="minLength[5]" />
						</td>
					</tr>
					<tr>
				 		<td>性别:</td>
	           			<td>
			           		<select name="gender" id="gender" class="easyui-combobox" style="width: 150px;">
			           			<option value="">请选择</option>
			           			<option value="男">男</option>
			           			<option value="女">女</option>
			           		</select>
		           		</td> 
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" name="telephone" data-options="validType:'tellnumber'" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>工资:</td><td><input type="text" name="salary" id="salary" class="easyui-numberbox" /></td>
					</tr>
					<tr>
						<td>生日:</td>
						<td><input type="text" name="myBirthday" id="birthday" class="easyui-datebox" /></td>
					</tr> 
					<tr>
						<td>描述</td>
						<td>
							<input type="text" name="remark" class="easyui-validatebox" />  
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</body>
</html>
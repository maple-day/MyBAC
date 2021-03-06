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
		// 点击保存
		$('#save').click(function(){
			var t = $("#reamke").val();
			if(t){
				var t = $("#functionForm").form("validate");
				if(t){
					$("#functionForm").submit();
				}			
			}else{
				$.messager.alert("温馨提示","请假原因必须写哦...","info");
			}
			
		});
	});
</script>	
</head>
<body class="easyui-layout">
<div data-options="region:'north'">
	<div class="datagrid-toolbar">
		<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >请假</a>
	</div>
</div>
<div data-options="region:'center'">
	<form id="functionForm" method="post" action="${pageContext.request.contextPath }/leaveAction_start.action">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">请假条</td>
					</tr>
					<tr>
						<td width="200">请假天数</td>
						<td>
							<input type="text" name="day" class="easyui-validatebox" data-options="required:true" required="true"/>						
						</td>
					</tr>
					<tr>
						<td>开始时间:</td>
						<td><input type="text" name="start" id="birthday" class="easyui-datebox" required="true" /></td>
					</tr>
					<tr>
						<td>结束时间:</td>
						<td><input type="text" name="end" id="birthday" class="easyui-datebox" required="true"/></td>
					</tr>
					<tr>
						<td>请假原因</td>
						<td>
							<textarea id="reamke" name="reason" rows="4" cols="60" required="true"></textarea>
						</td>
					</tr>
					</table>
			</form>
</div>
</body>
</html>
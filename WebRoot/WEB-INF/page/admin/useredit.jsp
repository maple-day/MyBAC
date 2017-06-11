<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
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
		$("body").css({visibility:"visible"});
		$('#save').click(function(){
			var t = $("#form").form("validate");
			if(t){
				var array = new Array();
				var r=$('.rolename').val();
				
				$("input[name=rolenames]").val(r);
				$('#form').submit();
			}
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
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
		<div class="datagrid-toolbar">
			<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存修改</a>
		</div>
	</div>
    <div region="center" style="overflow:auto;padding:5px;" border="false">
    <s:iterator value="list" var="old">
    	 <input  class="rolename" name="rolename" type="hidden" value="<s:property value="id"/>" >
    </s:iterator>
    <form id="form" method="post" action="${pageContext.request.contextPath }/userAction_editSave.action">
  	  <s:iterator value="user" var="user">
       	   <input name="id" value="<s:property value="id"/>" type="hidden">
       	   <input name="rolenames" type="hidden">
           <table class="table-edit"  width="95%" align="center">
           		<tr class="title"><td colspan="4">基本信息</td></tr>
	           	<tr><td>用户名:</td><td><input type="text" name="username" value="<s:property value="username" />"  id="username" class="easyui-validatebox" required="true" /></td>
					<td>口令:</td><td><input type="password" name="password"  id="password" class="easyui-validatebox" required="true" validType="minLength[5]" /></td></tr>
				<tr class="title"><td colspan="4">其他信息</td></tr>
	           	<tr><td>工资:</td><td><input type="text" value="<s:property value="salary" />"  name="salary" id="salary" class="easyui-numberbox" /></td>
					<td>生日:</td><td><input type="text" name="birthday" id="birthday" class="easyui-datebox" /></td></tr>
	           	<tr>
	           		<td>性别:</td>
	           		<td>
		           		<select name="gender" id="gender" class="easyui-combobox" style="width: 150px;">
		           			<option value="">请选择</option>
		           			<option value="男">男</option>
		           			<option value="女">女</option>
		           		</select>
		           	</td>
					<td>联系电话</td>
					<td colspan="3">
						<input type="text" name="telephone" value="<s:property value="telephone"/> "  id="telephone" data-options="validType:'tellnumber'"  class="easyui-validatebox" required="true" />
					</td>
				</tr>
	           	<tr><td>备注:</td>
	           		<td colspan="3"><textarea name="remark" id="text" style="width:80%"></textarea>
	           		<script type="text/javascript">
							$("#text").text("<s:property value="remark"/>");
	           		</script>
	           		</td>
	           	</tr>
	           	<tr><td>选择角色:</td>
		           	<td colspan="3" id="roleTD">
		           		<script type="text/javascript">
		           			$(function(){
		           				var url = "${pageContext.request.contextPath }/roleAction_listajax.action";
		           				$.post(url,{},function(data){
	           						for(var i=0;i<data.length;i++){
	           							var id = data[i].id;
	           							var name = data[i].name;
	           							$("#roleTD").append('<input type="checkbox" value="'+id+'" name="roleIds">'+name);
	           						}
		           				});
		           			});
		           		</script>
		           	</td>
	           	</tr>
           </table>
  		  </s:iterator>
       </form>
	</div>
</body>
</html>
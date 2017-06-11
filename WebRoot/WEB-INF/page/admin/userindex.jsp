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
		$("body").css({visibility:"visible"});
		// 注册按钮事件
		$('#reset').click(function() {
			$('#form').form("clear");
		});
		// 注册所有下拉控件
		$("select").combobox( {
			width : 155,
			listWidth : 180,
			editable : true
		});
	});
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
    <div region="east" title="查询条件" icon="icon-forward" style="width:180px;overflow:auto;" split="false" border="true" >
		<div class="datagrid-toolbar">	
			<a id="reset" href="#" class="easyui-linkbutton" plain="true" icon="icon-reload">重置</a>
		</div>
		<form id="form" method="post" action="">
			<table class="table-edit" width="100%" >				
				<tr><td>
					<b>用户名</b><span class="operator"><a name="username-opt" opt="all"></a></span>
					<input type="text" id="username" name="username"/>
				</td></tr>
				<tr><td>
					<b>性别</b><span class="operator"><a name="gender-opt" opt="all"></a></span>
					<select id="gender" name="gender" >
					    <option value=""></option>
					    <option value="女">女</option>
					    <option value="男">男</option>
					</select>
				</td></tr>
			</table>
		</form>
		<div class="datagrid-toolbar">	
			<a id="ajax" href="javascript:void(0);" class="easyui-linkbutton" plain="true" icon="icon-search">查询</a>
			<script type="text/javascript">
				$(function(){
						$.fn.serializeJson=function(){  
				            var serializeObj={};  
				            var array=this.serializeArray();
				            $(array).each(function(){  
				                if(serializeObj[this.name]){  
				                    if($.isArray(serializeObj[this.name])){  
				                        serializeObj[this.name].push(this.value);  
				                    }else{  
				                        serializeObj[this.name]=[serializeObj[this.name],this.value];  
				                    }  
				                }else{  
				                    serializeObj[this.name]=this.value;   
				                }  
				            });  
				            return serializeObj;  
				        }; 
					$("#ajax").click(function(){
						var p = $("#form").serializeJson();
						console.log("hh");
						var elWin = $("#list").get(0).contentWindow;
							elWin.$('#grid').datagrid("reload",p);
					});
				});
			</script>
		</div>
    </div>
    <div region="center" style="overflow:hidden;" border="false">
		<iframe id="list" src="${pageContext.request.contextPath }/page_admin_userlist.action" scrolling="no" style="width:100%;height:100%;border:0;"></iframe>
    </div>
</body>
</html>
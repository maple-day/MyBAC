<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/default.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css">
<style type="text/css">
	*{
		margin:0;
		padding:0;
	}
	.top{
		position: relative;
	}
	.top img{
		position:absolute;
		left:0;
		top:0;
		width:100%;
		height:100px;
	}
</style>
<script type="text/javascript">
	
	$(function() {
		window.setTimeout(function(){
			$.messager.show({
				title:"消息提示",
				msg:'欢迎登录，管理员！ <a href="javascript:void" onclick="top.showAbout();">联系管理员</a>',
				timeout:5000
			});
		},3000);
		
		//自定义校验规则
		 $.extend($.fn.validatebox.defaults.rules, {     
                equals: {     
                    validator: function(value,param){     
                        return value == $(param[0]).val();     
                    },     
                    message: '俩次密码不相同.'    
                }     
            });    

	});
		/* 修改主题  */
		changeTheme = function(theme){
		   var $easyuiTheme = 	$("#easyuiTheme");
		   var url =  $easyuiTheme.attr('href');
		   var href = url.substring(0,url.indexOf('themes'))+'themes/'+theme+'/easyui.css';
		   $easyuiTheme.attr('href',href);
		   if ($iframe.length > 0) {
				for ( var i = 0; i < $iframe.length; i++) {
					var ifr = $iframe[i];
					$(ifr).contents().find('#easyuiTheme').attr('href', href);
				}
			}
		};
		/* 退出系统 */
		function logoutFun(){
			$.messager.confirm('系统提示','你确定要退出本次登陆吗？',function(islogout){
					if(islogout){
						location.href = '${pageContext.request.contextPath }/userAction_logout.action';
					}			
			});
		};
		
		/*修改密码  */
		function editPassword(){
			$('#editPwdWindow').window('open');  // open a window
		};
		
		function showAbout(){
			$.messager.alert("基于角色权限管理系统 v1.0","管理员邮箱:896757055@qq.com");
		};
	

</script>
<title>主页面</title>
<script type="text/javascript">
		// 初始化ztree菜单
	$(function() {
		var setting = {
			data : {
				simpleData : { // 简单数据 
					enable : true
				}
			},
			callback : {
				onClick : onClick
			}
		};
		
		// 基本功能菜单加载
		$.ajax({
			url : '${pageContext.request.contextPath}/functionAction_findMenu.action',
			type : 'POST',
			dataType : 'text',
			success : function(data) {
				var zNodes = eval("(" + data + ")");
				$.fn.zTree.init($("#treeMenu"), setting, zNodes);
			},
			error : function(msg) {
				alert('菜单加载异常!');
			}
		});
		
	});

	function onClick(event, treeId, treeNode, clickFlag) {
		// 判断树菜单节点是否含有 page属性
				// 判断树菜单节点是否含有 page属性
		if (treeNode.page!=undefined && treeNode.page!= "") {
			var content = '<div style="width:100%;height:100%;overflow:hidden;">'
					+ '<iframe src="'
					+ treeNode.page
					+ '" scrolling="auto" style="width:100%;height:100%;border:0;" ></iframe></div>';
			if ($("#tabs").tabs('exists', treeNode.name)) {// 判断tab是否存在
				$('#tabs').tabs('select', treeNode.name); // 切换tab
				var tab = $('#tabs').tabs('getSelected'); 
				$('#tabs').tabs('update', {
				    tab: tab,
				    options: {
				        title: treeNode.name,
				        content: content
				    }
				});
			} else {
				// 开启一个新的tab页面
				$('#tabs').tabs('add', {
					title : treeNode.name,
					content : content,
					closable : true
				});
			}
		}
	}
</script>
</head>
<body class="easyui-layout">
	<div class="top" data-options="region:'north' ,border:false" collapsible="false" style="height:100px;">
		<p style="font-size:30px;position: absolute;left:100px;top:10px;z-index:100;font-family:楷书;">基于RBAC权限管理系统</p>	
		<img alt="" src="./images/header_bg.png">
		<div id="sessionInfoDiv"
			style="position: absolute;right: 5px;top:10px;">
			<s:property value="username"/>
			[<strong>管理员</strong>]，欢迎你！ 
		</div>
		<div style="position: absolute; right: 5px; bottom: 10px; ">
			<a href="javascript:void(0)" id="mb" class="easyui-menubutton" data-options="menu:'#mm',iconCls:'icon-ok'">更换皮肤</a> 
			<div id="mm" style="width:120px;">   
			    <div onclick="changeTheme('default');">default</div>   
			    <div onclick="changeTheme('gray');">gray</div>   
			    <div onclick="changeTheme('black');">black</div>   
			    <div onclick="changeTheme('bootstrap');">bootstrap</div>   
			    <div onclick="changeTheme('metro');">metro</div>   
			</div> 
			<a href="javascript:void(0)" id="mb" class="easyui-menubutton" data-options="menu:'#m',iconCls:'icon-help'">控制面板</a> 
			<div id="m" style="width:150px;">   
			    <div onclick="editPassword();">修改密码</div> 
			    <div onclick="showAbout()">联系管理员</div> 
			    <div onclick="logoutFun();">退出系统</div> 
			</div> 
		</div>
		
	</div>
	<div title="系统导航"  data-options="region:'west',split:true" style="width:200px">
		<div class="easyui-accordion" data-options="fit:'true'" border="false">
			<div title="系统功能"  data-options="iconCls:'icon-mini-add'" style="overflow:auto">
				<ul id="treeMenu" class="ztree"></ul>
			</div>
		</div>
	</div>
	<div  data-options="region:'center'" style="width:100%">
		<div id="tabs" class="easyui-tabs" data-options="fit:'true'">
			<div title="消息中心" id="subWarp"
				style="width:100%;height:100%;overflow:hidden">
				<iframe src="${pageContext.request.contextPath }/page_common_msg.action"
					style="width:100%;height:100%;border:0;"></iframe>
				<%--				这里显示公告栏、预警信息和代办事宜--%>
			</div>
		</div>
	</div>
	<!-- 修改密码窗口 -->
  	<div id="editPwdWindow" class="easyui-window" title="修改密码" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
       maximizable="false" icon="icon-save"  style="width: 300px; height: 160px; padding: 5px;background: #fafafa">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
		        <form  id="editPasswordForm">
	                <table cellpadding=3>
	                    <tr>
	                        <td>新密码：</td>
	                        <td><input id="txtNewPass" type="Password" data-options="required:true,validType:'length[4,8]'" class="txt01 easyui-validatebox" /></td>
	                    </tr>
	                    <tr>
	                        <td>确认密码：</td>
	                        <td><input id="txtRePass" type="Password" data-options="required:true,validType:['length[4,8]']" validType="equals['#txtNewPass']" class="txt01 easyui-validatebox" /></td>
	                    </tr>
	                </table>
		        </form>
            </div>
      <!--       <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >确定</a> 
                <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div> -->
       </div>
   	</div>
    <!-- 修改密码 -->
	<script type="text/javascript">
		$("#btnEp").click(function(){
			//进行表单校验
			var v = $("#editPasswordForm").form("validate");//对应表单中的所有输入框进行校验
			if(v){
				var password1 = $("#txtNewPass").val();
				var password2 = $("#txtRePass").val();
				if(password1==password2){
					var url="${pageContext.request.contextPath}/userAction_editPassword.action";
					$.post(url,{"password":password1},function(data){
							if(data == '1'){
							//修改密码成功
							$.messager.alert("提示信息","密码修改成功！","info");
						}else{
							//修改失败
							$.messager.alert("提示信息","密码修改失败！","warning");
						}
						//关闭修改密码的窗口 
						$("#editPwdWindow").window("close");
					});
				}else{
					//输入不一致，提示用户输入不一致
						$.messager.alert("提示信息","两次输入密码不一致！","warning");
				}
			}
		});
		$("#btnCancel").Click(function(){
				$("#editPwdWindow").window("close");
		});
	
	</script>
</body>
</html>
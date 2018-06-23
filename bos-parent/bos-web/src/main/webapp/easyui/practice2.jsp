<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>

<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		 var setting={
				 data: {
						simpleData: {
							enable: true
						}
					} ,
			 callback: {
					onClick: function zTreeOnClick(event, treeId, treeNode) {
					           if(treeNode.page!=undefined){
					        	  var flag=$("#tabs").tabs("exists",treeNode.name);
					        	  if(flag){
					        		  $("#tabs").tabs("select",treeNode.name); 
					        	  }
					        	  else{
					        		  $("#tabs").tabs("add",{
					        			  title:treeNode.name,
					        			  closable:true,
									         content:' <iframe frameborder="0" width="100%" height="100%" src="'+treeNode.page+'"></iframe>'
					        		  })
					        	  }
					           }
					}
				}
		 };
		 var url="${pageContext.request.contextPath}/json/menu.json";
		   $.post(url,
				   function(data){
			          $.fn.zTree.init($("#basic"), setting, data);
				   },"json");
		   
		   
		   
		// 系统管理菜单加载
			$.ajax({
				url : '${pageContext.request.contextPath}/json/admin.json',
				type : 'POST',
				dataType : 'text',
				success : function(data) {
					var zNodes = eval("(" + data + ")");
					$.fn.zTree.init($("#adminMenu"), setting, zNodes);
				},
				error : function(msg) {
					alert('菜单加载异常!');
				}
			});
		   
		// 页面加载后 右下角 弹出窗口
			/**************/
			window.setTimeout(function(){
				$.messager.show({
					title:"消息提示",
					msg:'欢迎登录，超级管理员！ <a href="javascript:void" onclick="top.showAbout();">联系管理员</a>',
					timeout:5000
				});
			},3000);
	});
	function logoutFun(){
		$.messager.confirm('系统提示', '您确定退出本系统么', function(isConfirm){
			if (isConfirm){
				location.href = '${pageContext.request.contextPath }/UserAction_loginOut';
			} 
		});  
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height:80px;padding:10px;background:url('${pageContext.request.contextPath}/images/header_bg.png') no-repeat right;">
	   <div id="sessionInfoDiv" style="right: 5px;position: absolute;top: 10px">
	      <strong>[超级管理员]，欢迎您</strong>
	   </div>
	  <div style="position: absolute;right: 5px;bottom: 10px">
	      <a a href="javascript:void(0)"  class="easyui-menubutton"  
	         data-options="menu:'#mm',iconCls:'icon-ok'">更换主题</a>
	      <a a href="javascript:void(0)"  class="easyui-menubutton"  
	         data-options="menu:'#layout_north_kzmbMenu',iconCls:'icon-help'">控制面板</a>
	  </div>
	   <div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
			<div onclick="editPassword();"  data-options="iconCls:'icon-edit'">修改密码</div>
			<div onclick="showAbout();" >联系管理员</div>
			<div class="menu-sep"></div>
			<div onclick="logoutFun();" >退出系统</div>
		</div>
	   
	</div>
	<div region="west" split="true" title="菜单导航" style="width:220px">
	   <div class="easyui-accordion" data-options="fit:true">
	      <div data-options="iconCls:'icon-mini-add'"  title="基本功能"> 
	         <ul id="basic" class="ztree"></ul>
	      </div>
	      <div data-options="iconCls:'icon-mini-add'"  title="系统管理">
	         <ul id="adminMenu" class="ztree"></ul>
	       </div>
	   
	   </div>
	</div>
	<div region="south" border="false" 
	    style="height:50px;background:url('${pageContext.request.contextPath}/images/header_bg.png') no-repeat right;;padding:10px;url">
	    <div style="position: relative;left: 5px">
	              传智播客
	    </div>
	</div>
	<div region="center" >
		<div id="tabs" fit="true" class="easyui-tabs" border="false">
			<div title="消息中心" id="subWarp"
					style="width:100%;height:100%;overflow:hidden">
					<iframe src="${pageContext.request.contextPath }/page_common_home.action"
						style="width:100%;height:100%;border:0;"></iframe>
					<%--				这里显示公告栏、预警信息和代办事宜--%>
			</div>
		</div>
	</div>
</body>
</html>
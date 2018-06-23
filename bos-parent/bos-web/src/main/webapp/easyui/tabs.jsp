<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<title>Insert title here</title>
</head>
<script type="text/javascript">
   $(function(){
	   $("#bt1").click(function(){
		  var flag= $("#tabs").tabs('exists','系统管理');
		  if(flag){
			  $("#tabs").tabs('select','系统管理');
		  }
		  else{
		   $("#tabs").tabs('add',{
				  title:'系统管理',
				  content:'  <iframe frameborder="0" width="100%" height="100%" src="http://baidu.com"></iframe>',
				  iconCls:'icon-edit',
				  closable:true
			  } );	
		  }
	   });
   });
</script>
<body class="easyui-layout" >

   <div data-options="region:'north'" title="欢迎光临" style="height:100px;background:#B3DFDA;padding:10px"> 
        <button class="easyui-linkbutton" id="bt1" onclick="add()">Add</button>
    </div>
   <div data-options="region:'west'"style="width: 200px" title="accordion">
      	<div class="easyui-accordion" data-options="fit:true">
                          <!-- 	fit:true  自适应容器（也就是自动填充父区域）-->
			<!-- 使用子div表示每个面板 -->
			<div data-options="iconCls:'icon-cut'" title="面板一">1111</div>
			<div data-options="closable:true" title="面板二">2222</div>
			<div title="面板三" >3333</div>
		</div>
   </div>
    <div data-options="region:'center'">
        <div class="easyui-tabs"  id="tabs" data-options="fit:true">
          <div  data-options="iconCls:'icon-cut'" title="剪切">111</div>
          <div data-options="iconCls:'icon-add'"  title="添加" >222</div>
          <div data-options="closable:true" title="关闭">222</div>
        </div>
    </div>
   <div data-options="region:'east'"style="width: 150px">东部区域 </div>
   <div data-options="region:'south'" style="height: 50px">南部区域 </div>
    
</body>
</html>
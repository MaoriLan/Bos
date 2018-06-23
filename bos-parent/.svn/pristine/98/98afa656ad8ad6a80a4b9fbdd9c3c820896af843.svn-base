<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
</head>
<script type="text/javascript">
   $(function(){
	   $("#bt1").click(
		   function(){
			var flag= $("#tabs").tabs('exists','系统管理');
			if(flag){
				$("#tabs").tabs('select','系统管理');
			}
			else{
				 $("#tabs").tabs('add',{
					   title:'系统管理',
					   content:' <iframe frameborder="0" width="100%" height="100%" src="http://baidu.com"></iframe>  ',
					   iconCls:'icon-add',
					   closable:true
				   });
			}
		   });
   });
</script>
<body class="easyui-layout">     
         
   <div data-options="region:'north'" style="height: 100px; color: purple; background: #B3DFDA" >北部区域</div>
   <div data-options="region:'west'" style="width: 250px" title="accordion">
       <div class="easyui-accordion" data-options="fit:true">
          <div title="面板一">
             <a href="#" id="bt1" class="easyui-linkbutton">添加</a>
          </div>
          <div title="面板二"></div>
          <div title="面板三"></div>
       </div>
   </div>
   <div data-options="region:'center'">
        <div class="easyui-tabs" id="tabs" data-options="fit:true">
          <div title="面板一" data-options="closable:true"></div>
          <div title="面板二" data-options="iconCls:'icon-edit'"></div>
          <div title="面板三"></div>
       </div>
   </div>
   <div data-options="region:'east'" style="width: 190px">东部区域</div>
   <div data-options="region:'south'" style="height: 50px">南部区域</div>
</body>             
</html>
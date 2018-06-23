<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
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
 
   
   $(function(){
	   setting = {
				view: {
					selectedMulti: false
				}
			},
			zTreeNodes = [
				{"name":"网站导航", open:false, children: [
					{ "name":"google", "url":"http://g.cn", "target":"_blank"},
					{ "name":"baidu", "url":"http://baidu.com", "target":"_blank"},
					{ "name":"sina", "url":"http://www.sina.com.cn", "target":"_blank"}
					]
				}
			];
	  $.fn.zTree.init($("#tree1"), setting, zTreeNodes);
   });
   
   $(function(){
	   setting2 = {
			   data: {
					simpleData: {
						enable: true
					}
				}
			},
			zTreeNodes2= [
				{"id":"1","pId":"0","name":"网站导航", open:false},
				{"id":"2","pId":"1","name":"谷歌", "url":"http://g.cn", "target":"_blank"},
				{"id":"3","pId":"1","name":"百度", "url":"http://baidu.com", "target":"_blank"},
			    {"id":"4","pId":"0","name":"文件夹", open:false},
				{"id":"5","pId":"4","name":"文件一"},
				{"id":"6","pId":"4","name":"文件二"}
			];       
	  $.fn.zTree.init($("#tree2"), setting2, zTreeNodes2);
   });
   $(function(){
	   setting3 = {
			   data: {
					simpleData: {
						enable: true
					}
				},
		       callback: {
							onClick:function(event, treeId, treeNode) {
								 var flag=$("#tabs").tabs('exists',treeNode.name);
								 if(treeNode.page!=undefined){
									 if(flag){
										 $("#tabs").tabs('select',treeNode.name);
									 }
									 else{
										  $("#tabs").tabs('add',
												   { title:treeNode.name,
											         closable:true,
											         content:' <iframe frameborder="0" width="100%" height="100%" src="'+treeNode.page+'"></iframe>'
												   });
									  }
								 }
							  }
						 }
					
			};
	   var url="${pageContext.request.contextPath }/json/menu.json";
	   $.post(url,
			   function(data){
		          $.fn.zTree.init($("#tree3"), setting3, data);
			   },"json");
			    
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
			<div data-options="closable:true" title="面板二">
			   <ul id="tree1" class="ztree" style="width:230px; overflow:auto;"></ul>
			</div>
			<div title="面板三" >
			     <ul id="tree2" class="ztree" style="width:230px; overflow:auto;"></ul>
			 </div>
			 <div title="面板四" >
			     <ul id="tree3" class="ztree" style="width:230px; overflow:auto;"></ul>
			 </div>
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
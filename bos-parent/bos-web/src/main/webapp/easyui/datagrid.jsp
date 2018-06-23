<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
  var columns=[[ 
	  {field:'id',title:'id',checkbox:true}, 
	  {field:'name',title:'姓名'}, 
	  {field:'age',title:'年龄',} ,
	  {field:'sex',title:'性别',}
      ]] ;
  var toolbar=[{
	  iconCls: 'icon-edit',  
	  text:'编辑',
	 
	  handler: function(){alert('edit')} 
  }];

  $(function(){
	  $("#grid").datagrid({
			  columns:columns,
			  toolbar:toolbar,
			  pageList: [30,50,100],
			  pagination : true,
			  rownumbers:true,
			  fit : true
	  });
  });
 
</script>
<body>
   <table class="easyui-datagrid" data-options="url:'${pageContext.request.contextPath}/json/datagrid_data.json'"  style="width:300px;height:250px">
      <thead>
          <tr>
             <th data-options="field:'id',width:100">id</th>
             <th data-options="field:'name',width:100">姓名</th>
             <th data-options="field:'age',width:100">年龄</th>
          </tr>
      </thead>
   </table>
   
   <table id="grid" class="easyui-datagrid" data-options="url:'${pageContext.request.contextPath}/json/datagrid_data.json'"  style="width:300px;height:250px">

     
   </table>
</body>
</html>
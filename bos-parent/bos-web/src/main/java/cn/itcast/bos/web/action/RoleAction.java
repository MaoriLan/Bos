package cn.itcast.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.entity.Role;
import cn.itcast.bos.service.RoleService;


@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
   
	 @Autowired
	 private RoleService rs;
	 
	 private String functionIds;
	 public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	 }
	 
	 public String save(){
		 rs.save(model,functionIds);
		 return "list";
	 }
	 public String pageQuery(){
	     rs.pageQuery(pageBean);
	     this.java2Json(pageBean, new String[]{"functions","users"});
		 return null;
	 }
	 public String listAjax(){
		 List<Role> list=rs.findAll();
		 java2Json(list, new String[]{"functions","users"});
		 return null;
	 }
	 
}

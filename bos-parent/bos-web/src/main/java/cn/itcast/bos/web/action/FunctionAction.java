package cn.itcast.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.entity.Function;
import cn.itcast.bos.service.FunctionService;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {
	@Autowired
	 private  FunctionService fs;
    
	
	 public String listAjax(){
		 List<Function> list=fs.findAll();
		 this.java2Json(list, new String[]{"roles","parentFunction"});
		 return null;
	 }
	 
	 public String saveFunction() {
		 fs.save(model);
		 return "list";
		
	 }
	 public String pageQuery() {
		 pageBean.setCurrentPage(Integer.parseInt(model.getPage()));
		 fs.pageQuery(pageBean);
		 this.java2Json(pageBean, new String[]{"roles","children","parentFunction"});
		 return null;
		
	}
	 public String loadMenu(){
		 List<Function> list = fs.loadMenu();
		 this.java2Json(list, new String[]{"parentFunction","roles","children"});
		 return null;
	 }
}

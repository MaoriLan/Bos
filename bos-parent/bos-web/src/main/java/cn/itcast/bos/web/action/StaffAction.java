package cn.itcast.bos.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.entity.Staff;
import cn.itcast.bos.service.StaffService;

@Controller("staffAction")
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	@Resource(name="staffService")
	private StaffService ss;
	public String addSatff() throws Exception{
		ss.saveStaff(model);
		return "list";
	}
    public String pageQuery() throws Exception{
    	   String name=model.getName();
           String station=model.getStation();
           
           if(StringUtils.isNotBlank(name))
        	   detachedCriteria.add(Restrictions.like("name", "%"+name+"%"));
           if(StringUtils.isNotBlank(station))
        	   detachedCriteria.add(Restrictions.like("station", "%"+station+"%"));
        
    	
    	ss.pageQuery(pageBean);
    	String[] excludes=new String[]{"currentPage","pageSize","criteria","decidedzones"};
        this.java2Json(pageBean, excludes);
		return null;
	}
    private String staff;
    public void setStaff(String staff) {
		this.staff = staff;
	}
    @RequiresPermissions("staff-delete")//执行这个方法，需要当前用户具有staff-delete这个权限
	public String deleteBantch() throws Exception{
		ss.deleteBantch(staff);
		return "list";
	}
	public String restore()throws Exception{
		ss.restore(staff);
		return "list";
	}
	public String editStaff()throws Exception{
		ss.editStaff(model);
		return "list";
	}
	public String staffAjax()throws Exception{
		List<Staff> list=ss.fingByCriteria();
		String[] excludes=new String[]{"decidedzones"};
		this.java2Json(list, excludes);
		return null;
	}
}

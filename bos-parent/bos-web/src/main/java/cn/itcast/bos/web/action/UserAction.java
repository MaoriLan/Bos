package cn.itcast.bos.web.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.bos.entity.User;
import cn.itcast.bos.service.UserService;
import cn.itcast.bos.utils.BosUtils;
import cn.itcast.bos.utils.ICustomerService;
import cn.itcast.bos.utils.MD5Utils;


@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
    private String checkcode;
     
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	@Autowired
	private ICustomerService proxy;
	
    @Resource(name="userService")
	private UserService us;
	public String login() throws Exception {
		String code=(String) ActionContext.getContext().getSession().get("key");
		ActionContext.getContext().put("username", model.getUsername());
	    if(StringUtils.isNotBlank(checkcode)&&checkcode.equalsIgnoreCase(code)){
	    	//获得当前“对象”，并不是user对象，而是获得框架提供的接口对象
	    	Subject subject=SecurityUtils.getSubject();
	    	AuthenticationToken token=new UsernamePasswordToken(model.getUsername(), MD5Utils.md5(model.getPassword()));
	    	try {
				subject.login(token);
			} catch(IncorrectCredentialsException e2){
				addActionError("密码错误,登录失败");
				e2.printStackTrace();
				return LOGIN;
			}catch (UnknownAccountException e) {
				addActionError("该帐号不存在");
				e.printStackTrace();
				return LOGIN;
			}
	    	User user = (User) subject.getPrincipal();
	    	/*获得放入SimpleAuthenticationInfo中的user
	    	 * AuthenticationInfo info=new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
	    	 * */
	    	ActionContext.getContext().getSession().put("user", user);
	    	return HOME;
	    }else{
	    	addActionError("验证码错误,登录失败");
	    	return LOGIN;
	    }
	    
	}
	public String login_bak() throws Exception {
		String code=(String) ActionContext.getContext().getSession().get("key");
		ActionContext.getContext().put("username", model.getUsername());
	    if(StringUtils.isNotBlank(checkcode)&&checkcode.equalsIgnoreCase(code)){
	    	User loginUser=us.getUserByNameAndPassword(model);
	    	if(loginUser!=null){
	    		ActionContext.getContext().getSession().put("user", loginUser);
	    		return HOME;
	    	}
	    	else{
	    		addActionError("账号或密码错误");
	    	    return LOGIN;
	    	}
	    }else{
	    	addActionError("验证码错误,登录失败");
	    	return LOGIN;
	    }
	    
	}
	public String loginOut()throws Exception{
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}
    public String editPassword() throws IOException{
    	User u=BosUtils.getLoginUser();
    	String flag="1";
    	try{
	    	us.editPassword(u.getId(),model.getPassword());
	    }catch(Exception e){
	    	flag="0";
    		e.printStackTrace();
    	}
    	HttpServletResponse response=ServletActionContext.getResponse();
    	response.setContentType("text/json;charset:utf-8");
    	response.getWriter().println(flag);
    	return NONE;
    }
/*	当在页面需要取出来的时候才会用到get方法*/
/*    public String[] getRoleIds() {
    	return roleIds;
    }
*/   
    private String[] roleIds;

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
    
	public String save(){
		us.save(model,roleIds);
		return "list";
	}
	public String pageQuery(){
		us.pageQuery(pageBean);
		java2Json(pageBean, new String[]{"noticebills","roles"});
		return null;
	}
    
}

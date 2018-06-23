package cn.itcast.bos.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.itcast.bos.entity.User;

public class BosUtils {
   public static HttpSession getHttpSession(){
	   return  ServletActionContext.getRequest().getSession();
   }
   public static User getLoginUser(){
	   return (User) getHttpSession().getAttribute("user");
   }
}

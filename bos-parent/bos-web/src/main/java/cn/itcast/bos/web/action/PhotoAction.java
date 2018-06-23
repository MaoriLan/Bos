package cn.itcast.bos.web.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class PhotoAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public File photo;
	public String photoFileName;

	public String test() throws IOException {
		String root =ServletActionContext.getServletContext().getContextPath();
		File file=new File(root,photoFileName);
		FileUtils.copyFile(file, photo);
		System.out.println(root);
	
		return null;
	}
}

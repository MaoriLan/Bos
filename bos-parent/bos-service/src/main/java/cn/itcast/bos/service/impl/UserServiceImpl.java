package cn.itcast.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.UserDao;
import cn.itcast.bos.entity.Role;
import cn.itcast.bos.entity.User;
import cn.itcast.bos.service.UserService;
import cn.itcast.bos.utils.MD5Utils;
import cn.itcast.bos.utils.PageBean;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Resource(name="userDao")
    private UserDao ud;

	@Override
	public User getUserByNameAndPassword(User model) {
		String password=MD5Utils.md5(model.getPassword());
		return ud.getUser(model.getUsername(),password);
	}

	@Override
	public void editPassword(String id, String password) {
		// TODO Auto-generated method stub
		String pw=MD5Utils.md5(password);
		ud.executeUpdate("user.editPassword", pw,id);
	}

	@Override
	public User findByUsername(String username) {
		
		return ud.findByUsername(username);
	}

	@Override
	public void save(User model, String[] roleIds) {
		String password=model.getPassword();
		password=MD5Utils.md5(password);
		model.setPassword(password);
		ud.save(model);
		
		if(roleIds!=null&&roleIds.length>0){
			for(String id:roleIds){
				Role  role=new Role(id);
			model.getRoles().add(role);
			}
		}
		
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		ud.pageQuery(pageBean);
	}
}

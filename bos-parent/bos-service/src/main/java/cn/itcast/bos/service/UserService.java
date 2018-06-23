package cn.itcast.bos.service;

import cn.itcast.bos.entity.User;
import cn.itcast.bos.utils.PageBean;

public interface UserService {

	User getUserByNameAndPassword(User model);

	void editPassword(String id, String password);

	User findByUsername(String username);

	void save(User model, String[] roleIds);

	void pageQuery(PageBean pageBean);

}

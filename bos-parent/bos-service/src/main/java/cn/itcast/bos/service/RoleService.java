package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.entity.Role;
import cn.itcast.bos.utils.PageBean;

public interface RoleService {

	void save(Role model, String functionIds);

	void pageQuery(PageBean pageBean);

	List<Role> findAll();

}

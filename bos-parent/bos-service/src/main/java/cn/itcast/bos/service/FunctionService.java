package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.entity.Function;
import cn.itcast.bos.utils.PageBean;

public interface FunctionService {

	List<Function> findAll();

	void pageQuery(PageBean pageBean);

	void save(Function model);

	List<Function> loadMenu();

}

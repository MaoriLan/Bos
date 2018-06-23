package cn.itcast.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.FunctionDao;
import cn.itcast.bos.entity.Function;
import cn.itcast.bos.entity.User;
import cn.itcast.bos.service.FunctionService;
import cn.itcast.bos.utils.BosUtils;
import cn.itcast.bos.utils.PageBean;

@Service
@Transactional
public class FunctionServiceImpl implements FunctionService {
	@Autowired
      private FunctionDao fd;

	@Override
	public List<Function> findAll() {
		
		return fd.findAll();
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		fd.pageQuery(pageBean);
		
	}

	@Override
	public void save(Function model) {
		Function function = model.getParentFunction();
		if(function!=null&&function.getId().equals(""))
			model.setParentFunction(null);
		fd.save(model);
			
	}

	@Override
	public List<Function> loadMenu() {
		User user=BosUtils.getLoginUser();
		List<Function> list = null;
		if(user.getUsername().equals("admin")){
			list=fd.findAllFunctions();
		}else{
			list=fd.findAllFunctionsByUserId(user.getId());
		}
		return list;
	}
}

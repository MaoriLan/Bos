package cn.itcast.bos.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.RoleDao;
import cn.itcast.bos.entity.Function;
import cn.itcast.bos.entity.Role;
import cn.itcast.bos.service.RoleService;
import cn.itcast.bos.utils.PageBean;


@Transactional
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao rd;

	@Override
	public void save(Role model, String functionIds) {
		rd.save(model);
		if(StringUtils.isNotBlank(functionIds)){
				String[] ids=functionIds.split(",");
				for(String id:ids){
					//利用Function的构造方法创造一个托管状态的Function对象
					Function fun=new Function(id);
					model.getFunctions().add(fun);
				}
		}
		
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		rd.pageQuery(pageBean);
		
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return rd.findAll();
	}
}

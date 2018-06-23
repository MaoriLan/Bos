package cn.itcast.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.SubareaDao;
import cn.itcast.bos.entity.Region;
import cn.itcast.bos.entity.Subarea;
import cn.itcast.bos.utils.PageBean;

@Repository
public class SubareaDaoImpl extends BaseDaoImpl<Subarea> implements SubareaDao {

	@Override
	public List<Object> showHighcharts() {
		String hql="select r.province,count(*)  from Subarea s left outer join s.region r group by r.province";
		List<Object> list = (List<Object>) getHibernateTemplate().find(hql);
		return list;
	}

	

	
}

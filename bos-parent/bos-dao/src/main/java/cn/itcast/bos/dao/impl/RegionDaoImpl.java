package cn.itcast.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.RegionDao;
import cn.itcast.bos.entity.Region;
import cn.itcast.bos.utils.PageBean;

@Repository("regionDao")
public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao {
	@Override
	public List<Region> findByString(String q) {
		// TODO Auto-generated method stub
		String hql="from Region where province like ? or city like ? or district like ?"
				+ "or shortcode like ? or citycode like ?";
		List<Region> list = (List<Region>) getHibernateTemplate().find(hql, "%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
	    return list;
	}
}

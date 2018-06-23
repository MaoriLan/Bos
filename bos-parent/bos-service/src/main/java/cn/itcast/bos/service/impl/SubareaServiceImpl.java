package cn.itcast.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.RegionDao;
import cn.itcast.bos.dao.SubareaDao;
import cn.itcast.bos.entity.Region;
import cn.itcast.bos.entity.Subarea;
import cn.itcast.bos.service.SubareaService;
import cn.itcast.bos.utils.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements SubareaService {
	@Autowired
	private SubareaDao sd;

	@Override
	public void addSubarea(Subarea model) {
		// TODO Auto-generated method stub
		sd.save(model);
	}

	@Override
	public void queryPage(PageBean pb) {
		// TODO Auto-generated method stub
		sd.pageQuery(pb);
	}

	@Override
	public List<Subarea> findAll() {
		// TODO Auto-generated method stub
		return sd.findAll();
	}

	@Override
	public List<Subarea> findByCriteria() {
		DetachedCriteria dc=DetachedCriteria.forClass(Subarea.class);
		dc.add(Restrictions.isNull("decidedzone"));
		return sd.findByCriteria(dc);
	}

	@Override
	public List<Subarea> findByDecidedzoneId(String id) {
		DetachedCriteria dc=DetachedCriteria.forClass(Subarea.class);
		dc.add(Restrictions.eq("decidedzone.id", id));
		return sd.findByCriteria(dc);
	}

	@Override
	public List<Object> showHighcharts() {
		// TODO Auto-generated method stub
		return sd.showHighcharts();
	}
    
	

}

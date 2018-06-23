package cn.itcast.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.DecidedZoneDao;
import cn.itcast.bos.dao.SubareaDao;
import cn.itcast.bos.entity.Decidedzone;
import cn.itcast.bos.entity.Subarea;
import cn.itcast.bos.service.DecidedZoneService;
import cn.itcast.bos.utils.PageBean;



@Service
@Transactional
public class DecidedZoneServiceImpl implements DecidedZoneService {
   @Autowired
   private DecidedZoneDao dzd;
   
   @Autowired
   private SubareaDao sd;
	@Override
	public void save(Decidedzone model, String[] subareaId) {
		// TODO Auto-generated method stub
		dzd.save(model);
		for(String id:subareaId){
			Subarea s=sd.findById(id);
			s.setDecidedzone(model);
		}
		
	}
	@Override
	public void pqgeQuery(PageBean pb) {
		// TODO Auto-generated method stub
		dzd.pageQuery(pb);
	}
}

package cn.itcast.bos.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.wsdl.util.IOUtils;

import cn.itcast.bos.dao.RegionDao;
import cn.itcast.bos.entity.Region;
import cn.itcast.bos.service.RegionService;
import cn.itcast.bos.utils.PageBean;

@Service("regionService")
@Transactional
public class RegionServiceImpl implements RegionService {
	@Resource(name="regionDao")
    private RegionDao rd;

	@Override
	public void saveBatch(List<Region> list) {
		// TODO Auto-generated method stub
		for(Region r:list)
			rd.saveOrUpdate(r);
	}

	@Override
	public void queryRegion(PageBean pb) {
		
		rd.pageQuery(pb);
	}

	@Override
	public List<Region> findByString(String q) {
		// TODO Auto-generated method stub
		return rd.findByString(q);
	}

	@Override
	public List<Region> findAll() {
		// TODO Auto-generated method stub
		return rd.findAll();
	}

	@Override
	public void saveRefion(Region model) {
		// TODO Auto-generated method stub
		UUID.randomUUID().toString();
		rd.save(model);
	}

	@Override
	public void deleteBantch(String ids) {
		if(StringUtils.isNotBlank(ids)){
			if(ids.contains(",")){
				String[] id=ids.split(",");
				for(String i:id)
					rd.deleteByID(i);
			}
			rd.deleteByID(ids);
		}
		
	}
	
}

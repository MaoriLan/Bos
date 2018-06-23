package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.StaffDao;
import cn.itcast.bos.entity.Decidedzone;
import cn.itcast.bos.entity.Staff;
import cn.itcast.bos.service.StaffService;
import cn.itcast.bos.utils.PageBean;

@Service("staffService")
@Transactional
public class StaffServiceImpl implements StaffService{
	
	@Resource(name="staffDao")
	private StaffDao sd;

	@Override
	public void saveStaff(Staff model) {
		sd.save(model);
	}

	@Override
	public void pageQuery(PageBean pb) {
		// TODO Auto-generated method stub
		sd.pageQuery(pb);
	}

	@Override
	public void deleteBantch(String ids) {
        if(StringUtils.isNotBlank(ids)){
        	if(ids.contains(",")){
        		String[] id=ids.split(",");
                for(String i:id)
            	    sd.executeUpdate("staff.delete", i);
        	}
        	sd.executeUpdate("staff.delete", ids);
        }
	}

	@Override
	public void editStaff(Staff model) {
		// TODO Auto-generated method stub
		Staff staff=sd.findById(model.getId());
		
		staff.setStation(model.getStation());
		staff.setStandard(model.getStandard());
		staff.setName(model.getName());
		staff.setHaspda(model.getHaspda());
		staff.setTelephone(model.getTelephone());
		
		sd.update(staff);
		
	}

	@Override
	public List<Staff> fingByCriteria() {
		DetachedCriteria dc=DetachedCriteria.forClass(Staff.class);
		dc.add(Restrictions.eq("deltag", "0"));
		
		return sd.findByCriteria(dc);
	}

	@Override
	public void restore(String staff) {
		if(StringUtils.isNotBlank(staff)){
			if(!staff.contains(","))
				sd.executeUpdate("staff.restore", staff);
			else{
				String[] id=staff.split(",");
                for(String i:id)
            	    sd.executeUpdate("staff.delete", i);
			}
		}
		
	}

}

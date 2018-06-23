package cn.itcast.bos.service.impl;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.DecidedZoneDao;
import cn.itcast.bos.dao.NoticeBillDao;
import cn.itcast.bos.dao.WorkBillDao;
import cn.itcast.bos.entity.Noticebill;
import cn.itcast.bos.entity.Staff;
import cn.itcast.bos.entity.User;
import cn.itcast.bos.entity.Workbill;
import cn.itcast.bos.service.NoticeBillService;
import cn.itcast.bos.utils.BosUtils;


@Service
@Transactional
public class NoticeBillServiceImpl implements NoticeBillService {

	@Autowired
	private NoticeBillDao nd;
	@Autowired
	private DecidedZoneDao dd;
	@Autowired
	private WorkBillDao wd;

	//保存业务通知单
	@Override
	public void saveNoticebill(Noticebill model, String decidezoneId) {
		// TODO Auto-generated method stub
		//获得当前登录的userid
		User user=BosUtils.getLoginUser();
		model.setUser(user);
		nd.save(model);
		//如果当前用户被关联了，则将此工单设置为自动分单
		if(StringUtils.isNotBlank(decidezoneId)){
			//根据从前台传来的用户所关联的定区ID来查询定区
			Staff staff=dd.findById(decidezoneId).getStaff();
			model.setStaff(staff); 
			model.setOrdertype(model.ORDERTYPE_AUTO);
			//为取派员生成一个工单
			Workbill workbill=new Workbill();
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
			workbill.setAttachbilltimes(0);
			workbill.setNoticebill(model);
			workbill.setPickstate(Workbill.PICKSTATE_NO);
			workbill.setRemark(model.getRemark());
			workbill.setType(Workbill.TYPE_1);
			workbill.setStaff(staff);
			wd.save(workbill);
		}else{
			//该用户未关联定区，则需设置成手动分单
			model.setOrdertype(model.ORDERTYPE_MAN);
		}
	}
}

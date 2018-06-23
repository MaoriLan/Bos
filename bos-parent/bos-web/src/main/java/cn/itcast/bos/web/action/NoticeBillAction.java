package cn.itcast.bos.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.entity.Noticebill;
import cn.itcast.bos.service.NoticeBillService;
import cn.itcast.bos.utils.Customer;
import cn.itcast.bos.utils.ICustomerService;


@Controller
@Scope("prototype")
public class NoticeBillAction extends BaseAction<Noticebill> {
   
	@Autowired
	private ICustomerService cs;
	
	@Autowired
	private NoticeBillService ns;
    
	private String decidedzoneId;

	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}

	public String findCustomerByTelephone() throws Exception {
		// TODO Auto-generated method stub
		Customer c=cs.findByCustomerTel(model.getTelephone());
		java2JsonForObject(c, new String[]{});
		return null;
	}

	public String addNoticebill() throws Exception{
		ns.saveNoticebill(model,decidedzoneId);
		return "toNoticebill";
		
	}
}

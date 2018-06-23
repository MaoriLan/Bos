package cn.itcast.bos.web.action;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.entity.Decidedzone;
import cn.itcast.bos.service.DecidedZoneService;
import cn.itcast.bos.utils.Customer;
import cn.itcast.bos.utils.ICustomerService;


@Controller
@Scope("prototype")
public class DecidedZoneAction extends BaseAction<Decidedzone> {
	@Autowired
	private DecidedZoneService dzs;
	@Autowired
	private ICustomerService proxy;
	
	private List<Integer> customerIds;
	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}
	
    private String[] subareaId;
    public void setSubareaId(String[] subareaId) {
		this.subareaId = subareaId;
	}


	public String save() throws Exception{
		dzs.save(model,subareaId);
    	return "list";
    }
	public String pageQuery()throws Exception{
		dzs.pqgeQuery(pageBean);
		String[] excludes=new String[]{"decidedzones","subareas"};
		this.java2Json(pageBean, excludes);
		return null;
	}
	public String findListNotAssociation(){
		List<Customer> list = proxy.findListNotAssociation();
		this.java2Json(list, new String[]{});
		return NONE;
	}
	
	/**
	 * 远程调用crm服务，获取已经关联到指定的定区的客户
	 */
	public String findListHasAssociation(){
		String id = model.getId();
		List<Customer> list = proxy.findListHasAssociation(id);
		this.java2Json(list, new String[]{});
		return NONE;
	}
	public String AssosociationDecidedzone(){
		proxy.assosociationDecidedzone(model.getId(), customerIds);
		return "list";
	}
	public String associationCustomer(){
		List<Customer> list2 = proxy.findByDecidedId(model.getId());
		this.java2Json(list2, null);
		return null;
	}
    
}

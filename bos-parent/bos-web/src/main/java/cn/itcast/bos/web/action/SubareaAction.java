package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.entity.Region;
import cn.itcast.bos.entity.Subarea;
import cn.itcast.bos.service.SubareaService;
import cn.itcast.bos.utils.FileUtils;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
    @Autowired
    private SubareaService ss;
  
    public String addSubarea() throws Exception {
    	ss.addSubarea(model);
    	return "list";
    }
    public String queryPage()throws Exception{
    		String addresskey=model.getAddresskey();
    		if(StringUtils.isNotBlank(addresskey))
    			detachedCriteria.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
    		Region region=model.getRegion();
    		
        	if(region!=null){
        		String province=model.getRegion().getProvince();
            	String city=model.getRegion().getCity();
            	String district=model.getRegion().getDistrict();
            	if(StringUtils.isNotBlank(province)){
            		detachedCriteria.createAlias("region", "r");
            		detachedCriteria.add(Restrictions.like("r.province", "%"+province+"%"));
            	}
            	if(StringUtils.isNotBlank(city)){
            		detachedCriteria.createAlias("region", "r");
            		detachedCriteria.add(Restrictions.like("r.city", "%"+city+"%"));
            	}
            	if(StringUtils.isNotBlank(district)){
            		detachedCriteria.createAlias("region", "r");
            		detachedCriteria.add(Restrictions.like("r.district","%"+ district+"%"));
            	}
        	}
    	
    	String[] excludes=new String[]{"currentPage","pageSize","criteria","decidedzone","subareas"};
    	ss.queryPage(pageBean);
        this.java2Json(pageBean, excludes);
    	return null;
    }
    public String exportXLS() throws Exception{
    	List<Subarea> list=ss.findAll();
    	HSSFWorkbook workbook=new HSSFWorkbook();
    	HSSFSheet sheet = workbook.createSheet("分区数据");
    	HSSFRow headRow = sheet.createRow(0);
    	headRow.createCell(0).setCellValue("分拣编号");
    	headRow.createCell(1).setCellValue("省市区");
    	headRow.createCell(2).setCellValue("关键字");
    	headRow.createCell(3).setCellValue("起始号");
    	headRow.createCell(4).setCellValue("终止号");
    	headRow.createCell(5).setCellValue("位置信息");
    	
    	for(Subarea s:list){
    		HSSFRow row=sheet.createRow(sheet.getLastRowNum()+1);
    		row.createCell(0).setCellValue(s.getId());
        	row.createCell(1).setCellValue(s.getRegion().getName());
        	row.createCell(2).setCellValue(s.getAddresskey());
        	row.createCell(3).setCellValue(s.getStartnum());
        	row.createCell(4).setCellValue(s.getEndnum());
        	row.createCell(5).setCellValue(s.getPosition());
    	}
    	String filename="分区数据.xls";
    	String content = ServletActionContext.getServletContext().getMimeType(filename);
    	String agent = ServletActionContext.getRequest().getHeader("User-Agent");
    	filename=FileUtils.encodeDownloadFilename(filename, agent);
    	
    	HttpServletResponse response = ServletActionContext.getResponse();
    	response.setHeader("Content-disposition", "attachment;filename="+filename);
    	response.setContentType(content);
    	
    	ServletOutputStream outputStream = response.getOutputStream();
    	workbook.write(outputStream);
    	
    	return null;
    }
    public String listAjax()throws Exception{
		List<Subarea> list=ss.findByCriteria();
		String[] excludes=new String[]{"decidedzone","subareas"};
		this.java2Json(list, excludes);
		
		return null;
	}
    public String associationDecidedzone(){
    	List<Subarea> list=ss.findByDecidedzoneId(model.getId());
		String[] excludes=new String[]{"decidedzone","subareas"};
		this.java2Json(list, excludes);
    	return null;
    }
    public String showHighcharts(){
    	List<Object> list=ss.showHighcharts();
    	this.java2Json(list, new String[]{});
    	return null;
    }
}

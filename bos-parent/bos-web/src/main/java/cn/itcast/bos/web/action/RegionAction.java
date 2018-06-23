package cn.itcast.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.entity.Region;
import cn.itcast.bos.service.RegionService;
import cn.itcast.bos.utils.PageBean;
import cn.itcast.bos.utils.PinYin4jUtils;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller("regionAction")
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
    @Resource(name="regionService")
	private RegionService rs;
    private List<Region> list=new ArrayList<Region>();
    
    private File regionFile;
    public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}
    public String importFile() throws Exception {
    	 String path="D://区域导入测试数据.xls";
		 InputStream in=new FileInputStream(new File(path));
         HSSFWorkbook workbook=new HSSFWorkbook(in);
         HSSFSheet sheet = workbook.getSheet("Sheet1");
         int count=0;
         for(Row row:sheet){
        	 count=row.getRowNum();
        	 if(count==0)
        		 continue;
       	     String id=row.getCell(0).getStringCellValue();
	       	 String province=row.getCell(1).getStringCellValue();
	       	 String city=row.getCell(2).getStringCellValue();
	       	 String district=row.getCell(3).getStringCellValue();
	       	 String postcode=row.getCell(4).getStringCellValue();
	       	 
	       	 Region region=new Region(id, province, city, district, postcode, null, null, null);
	         
	       	 province=province.substring(0, province.length()-1);
			 city=city.substring(0, city.length()-1);
			 district=district.substring(0, district.length()-1);
			 String info=province+city+district;
			 String[] head = PinYin4jUtils.getHeadByString(info);
			 String shortcode=StringUtils.join(head, "");
			 String citycode=PinYin4jUtils.hanziToPinyin(city, "");
			 
			 region.setShortcode(shortcode);
			 region.setCitycode(citycode);
			 list.add(region);
	         
          }
         rs.saveBatch(list);
    	 return null;
    }
	public String queryRegion() throws Exception {
    	
    	rs.queryRegion(pageBean);
    	String[] excludes=new String[]{"currentPage","pageSize","criteria","subareas"};
    	java2Json(pageBean,excludes);
    	return null;
    }
	
	 private String q;
	    
	 public void setQ(String q) {
		this.q = q;
	 }

	public String loadRegion() throws Exception {
			if(StringUtils.isNotBlank(q)){
				list=rs.findByString(q);
			}else{
	    	    list=rs.findAll();
			}
	    	String[] excludes=new  String[]{"subareas"};
	    	this.java2Json(list, excludes);
			
	    	return null;
	    }
		
	public String saveRegion() throws Exception{
			String uuid = UUID.randomUUID().toString();
			model.setId(uuid);
			rs.saveRefion(model);
			return "list";
	}
	private String staff;
	public void setStaff(String staff) {
		this.staff = staff;
	}
	public String deleteBantch() throws Exception{
		rs.deleteBantch(staff);
		
		return "list";
		
	}
	
}

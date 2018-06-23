package cn.itcast.bos.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import cn.itcast.bos.entity.Region;
import cn.itcast.bos.utils.PinYin4jUtils;


public class PInYin4jTest {
	@Test
	public void test1() throws Exception {
		String province="山西省";
		String city="太原市";
		String district="古交市";

		province=province.substring(0, province.length()-1);
		city=city.substring(0, city.length()-1);
		district=district.substring(0, district.length()-1);

		String info=province+city+district;
		System.out.println(info);
		String[] head = PinYin4jUtils.getHeadByString(info);
		System.out.println(StringUtils.join(head));
		
	    String code=PinYin4jUtils.hanziToPinyin(city, "");
	    System.out.println(code);
	}
	@Test
	public void test2() throws Exception{
		
	}
}


package cn.itcast.bos.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import cn.itcast.bos.utils.PinYin4jUtils;


public class POITest {
	@Test
	public void test1() throws Exception {
		// TODO Auto-generated method stub
		  String path="D://区域导入测试数据.xls";
		  InputStream in=new FileInputStream(new File(path));
          HSSFWorkbook workbook=new HSSFWorkbook(in);
          HSSFSheet sheet = workbook.getSheet("Sheet1");
          for(Row rows:sheet){
        	  for(Cell cell:rows)
        		  System.out.print(cell.getStringCellValue()+" ");
          System.out.println();
          }
	}
}

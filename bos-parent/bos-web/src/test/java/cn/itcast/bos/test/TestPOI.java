package cn.itcast.bos.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestPOI {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		  String path="D://区域导入测试数据.xls";
		  InputStream in=new FileInputStream(new File(path));
          HSSFWorkbook workbook=new HSSFWorkbook(in);
	}

}

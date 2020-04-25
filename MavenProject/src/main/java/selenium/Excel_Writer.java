package selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Writer {
	public static void main(String[] args) throws Exception {
		
	
		File src1 = new File("D:\\WS1\\MavenProject\\writeExcel.xlsx");
		FileInputStream fis = new FileInputStream(src1);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");

		sheet.getRow(2).createCell(2).setCellValue("Kuzhuparambil");

		FileOutputStream fos = new FileOutputStream(src1);
		wb.write(fos);
		fos.flush();
	

	}

}

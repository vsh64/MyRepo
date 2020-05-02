package seleniumTestNG;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public  class Excel extends ReadProperties{

	static File src;
	static FileInputStream fis;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static String data = "";
	static String className;
	static String HeaderValue;
	static int totalRows;
	static int totalColumns;
		
	public Excel(String excelPath , String sheetName) {
		
		try {
		src = new File(excelPath);
		fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	

	public static String getData(String ClassName, String HeaderName) throws Exception {

		for (int i = 1; i <= totalRows(); i++) {
			 className = sheet.getRow(i).getCell(0).getStringCellValue();
			try {
				if (className.equalsIgnoreCase(ClassName)) {

					for (int j = 1; j < totalColumns(); j++) {
						 HeaderValue = sheet.getRow(0).getCell(j).getStringCellValue();

						try {
							if (HeaderValue.equalsIgnoreCase(HeaderName)) {
								data = sheet.getRow(i).getCell(j).getStringCellValue();
								System.out.println(
										"Data at Class : " + className + " at Header :" + HeaderValue + " == " + data);
							}

						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("No column Header Found with Name :" + HeaderValue);
						}
					}
				}
			} catch (Exception e) {
				System.out.println("No className Found with name :" + className);
				e.printStackTrace();
			}
		}
		return data;
	}

	private static int totalRows() {
		totalRows = sheet.getLastRowNum();
		return totalRows;
	}

	private static int totalColumns() {
		totalColumns = sheet.getRow(0).getLastCellNum();
		return totalColumns;
	}
}

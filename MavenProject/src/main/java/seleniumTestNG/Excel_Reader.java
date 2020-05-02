package seleniumTestNG;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Reader {
	
	static File src;
	static FileInputStream fis;
	static 	XSSFWorkbook wb;
	static XSSFSheet sheet;

	public static void main(String[] args) throws Exception {
		getData();

	}

	public static String getData() throws Exception {
		src = new File("D:\\WS1\\MavenProject\\Excel.xlsx");
		fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet("Sheet1");
		String data = "";
		String className;
		String HeaderValue;
	
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {

			 className = sheet.getRow(i).getCell(0).getStringCellValue();

			if (className.equalsIgnoreCase("TC1")) {
				for (int j = 1; j < sheet.getRow(0).getLastCellNum(); j++) {
				 HeaderValue = sheet.getRow(0).getCell(j).getStringCellValue();

					if (HeaderValue.equalsIgnoreCase("Header")) {
						data = sheet.getRow(i).getCell(j).getStringCellValue();
						System.out.println(data);

					}
				}

			}
		}
		return data;
	}
//		for (int i = 1; i <= totalRows(); i++) {
//			String className = sheet.getRow(i).getCell(0).getStringCellValue();
//			try {
//				if (className.equalsIgnoreCase(ClassName)) {
//
//					for (int j = 1; j < totalColumns(); j++) {
//						String HeaderValue = sheet.getRow(0).getCell(j).getStringCellValue();
//
//						try {
//							if (HeaderValue.equalsIgnoreCase(HeaderName)) {
//								data = sheet.getRow(i).getCell(j).getStringCellValue();
//								System.out.println(
//										"Data at Class : " + className + " at Header :" + HeaderValue + " == " + data);
//							}
//
//						} catch (Exception e) {
//							e.printStackTrace();
//							System.out.println("No column Header Found with Name :" + HeaderValue);
//						}
//					}
//				}
//			} catch (Exception e) {
//				System.out.println("No className Found with name :" + className);
//				e.printStackTrace();
//			}
//		}
//		return data;
//	}
//
	private static int totalRows() {
		int totalRows = sheet.getLastRowNum();
		return totalRows;
	}

	private static int totalColumns() {

		int totalColumns = sheet.getRow(0).getLastCellNum();
		return totalColumns;
	}
}

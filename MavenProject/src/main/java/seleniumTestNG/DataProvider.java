package seleniumTestNG;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataProvider extends Utils{

	public static Object[][] getTestData() throws IOException {

		File src = new File("D:\\WS1\\MavenProject\\dataProviderData.xlsx");
		FileInputStream fis = new FileInputStream(src);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("Sheet1");
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();

//			data[0][0]="a@a.com";
//			data[0][1]="abcd";
//			
//			data[1][0]="aa@a.com";
//			data[1][1]="aefgh";
//			
//			data[2][0]="hdjdjaa@a.com";
//			data[2][1]="aikjgs";
			}
		}
		System.out.println(data);
		return data;
	}
}

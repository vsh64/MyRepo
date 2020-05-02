package seleniumTestNG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;


public class ReadProperties extends DataProvider {

	static Properties prop;
	static FileInputStream ip;
	public static WebDriver driver;
	public static Logger log = Logger.getLogger(ReadProperties.class);
	String ClassName = this.getClass().getSimpleName();
	//Excel excel = new Excel(getPropertyValue("excelPath"), getPropertyValue("sheetName"));
	

	public static String getPropertyValue(String Key) {
		try {

			prop = new Properties();
			ip = new FileInputStream("D:\\WS1\\MavenProject\\config.properties");
			prop.load(ip);
			String value = prop.getProperty(Key);
			return value;
		} catch (Exception e) {
			return "ReadProperties failed " + e.getStackTrace();

		}
	}
}
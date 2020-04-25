package selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties extends DataProvider{

	static Properties prop;
	static FileInputStream ip;

	public static String getPropertyValue(String Key) throws IOException {

		prop = new Properties();
		ip = new FileInputStream("D:\\WS1\\MavenProject\\config.properties");
		prop.load(ip);
		String value = prop.getProperty(Key);
		return value;
	}

}

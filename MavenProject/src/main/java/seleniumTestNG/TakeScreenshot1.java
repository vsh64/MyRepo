package seleniumTestNG;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.CopyUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public  class TakeScreenshot1 {

	public static RemoteWebDriver driver ;
	
	public static String  takeScreenshot(String desc) throws IOException {

		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		String path ="./Screenshots/"+desc+System.currentTimeMillis()+".png";
		File des = new File(path);
	    FileUtils.copyFile(screenshotAs,des);
	    return path;
	}

}

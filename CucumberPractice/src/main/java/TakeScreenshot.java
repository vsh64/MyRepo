import java.io.File;
import java.io.IOException;

import org.apache.commons.io.CopyUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public  class TakeScreenshot {

	public static RemoteWebDriver driver ;
	
	public static String  takeScreenshot() throws IOException {

		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		String path ="./Screenshots/Screenshot_"+System.currentTimeMillis()+".png";
		File des = new File("");
	    FileUtils.copyFile(screenshotAs,des);
	    return path;
	}

}

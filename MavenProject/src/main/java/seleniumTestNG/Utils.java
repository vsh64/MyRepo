package seleniumTestNG;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Utils {

	public static RemoteWebDriver driver;
	static ExtentHtmlReporter extentHtmlReporter;
	static ExtentReports extentReports;
	static ExtentTest test;

	public static String takeScreenshot(String desc) throws IOException {

		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		String path = "./Screenshots/" + desc + System.currentTimeMillis()+".png";
		File des = new File(path);
		FileUtils.copyFile(screenshotAs, des);
		return path;
	}
	
	public static void report(String desc ,String result) throws IOException {
		extentHtmlReporter = new ExtentHtmlReporter("./Results/report.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentHtmlReporter);
		test = extentReports.createTest("Test", "testing extent reporter");
		test.assignAuthor("Vishnu");
		test.assignCategory("Smoke");

		String temp = Utils.takeScreenshot(desc);
		System.out.println(temp);
		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath(temp).build();

		if (result.equalsIgnoreCase("Pass"))
			test.log(Status.PASS, desc , screenshot);
		else if (result.equalsIgnoreCase("fail"))
			test.fail(desc, screenshot);
		else if (result.equalsIgnoreCase("skip"))
			test.skip(desc);
	

	}
	
	@Test
	public void sample() throws IOException {
		System.setProperty("webdriver.chrome.driver", "D:\\WS1\\MavenProject\\Drivers\\chromedriver.exe");
		 driver = new ChromeDriver();
		driver.get("http://leafground.com/");
		report("Lauched Url ", "pass");
		extentReports.flush();
		driver.quit();
	}
	

}

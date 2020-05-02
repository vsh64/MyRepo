package seleniumTestNG;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ExReporter1 {

	static ExtentHtmlReporter extentHtmlReporter;
	static ExtentReports extentReports;
	static ExtentTest test;
	
	
	public static void report(String desc ,String result) throws IOException {
		extentHtmlReporter = new ExtentHtmlReporter("./Results/report.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentHtmlReporter);
		test = extentReports.createTest("Test", "testing extent reporter");
		test.assignAuthor("Vishnu");
		test.assignCategory("Smoke");

		String temp = Utils.takeScreenshot(desc);

		if (result.equalsIgnoreCase("Pass"))
			test.pass(desc, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		else if (result.equalsIgnoreCase("fail"))
			test.fail(desc, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		else if (result.equalsIgnoreCase("skip"))
			test.skip(desc);
		extentReports.flush();

	}
		}

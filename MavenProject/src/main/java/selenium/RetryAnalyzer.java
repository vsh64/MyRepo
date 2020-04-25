package selenium;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	int counter = 0;
	int retry_limit = 3;

	public boolean retry(ITestResult result) {

		if (counter < retry_limit) {
			counter++;
			return true;
		}
		else
		return false;
	}

}

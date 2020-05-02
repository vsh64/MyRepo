package DataDrivenStepsDef;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CalendarPageWithExamples {

WebDriver driver;
	

	@Given("^User lauches the browser$")
	public void user_lauches_the_browser() {

		System.setProperty("webdriver.chrome.driver", "D:\\WS1\\CucumberPractice\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Then("^Navigate to leafgrounds$")
	public void navigate_to_leafgrounds() {

		System.out.println("In Background");
		driver.get("http://leafground.com/");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

	}

	@Then("^Maximize window$")
	public void maximize_window() {

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	@Given("^Already in Calendar Page$")
	public void already_in_Calendar_Page() {

		driver.findElement(By.linkText("Calendar")).click();

	}

	@Then("^Verify the text on Page is \"(.*)\"$")
	public void verify_the_text_on_Page_is_Get_Started_With_Calendars(String textOnPage) {
		String textOnPage1 = driver.findElement(By.xpath("//h1[contains(text(),'Get Started With Calendars')]"))
				.getText();
		System.out.println(textOnPage1);
		Assert.assertEquals(textOnPage, textOnPage);
	}

	@Then("^Click on Date Picker$")
	public void click_on_Date_Picker() throws Exception {

		driver.findElement(By.cssSelector("input[id='datepicker']")).click();
	}

	@Then("^Select Date as \"(.*)\"$")
	public void select_Date_as(String dateValue) {
		WebElement table = driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']"));
		List<WebElement> weeks = table.findElements(By.tagName("tr"));
		for (int i = 0; i < weeks.size(); i++) {
			List<WebElement> dates = weeks.get(i).findElements(By.tagName("td"));
			for (int j = 0; j < dates.size(); j++) {
				String date = dates.get(j).getText();
				if (date.equalsIgnoreCase(dateValue)) {
					System.out.println(date);
					dates.get(j).click();
				}
			}
		}

	}

}

package DataDrivenStepsDef;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EditPageWithDataTables {

	RemoteWebDriver driver;

	@Before
	public void setUp() throws IOException {
		System.out.println("Here we go .........1");
		System.setProperty("webdriver.chrome.driver", "D:\\WS1\\CucumberPractice\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("In Background");
		driver.get("http://leafground.com/");
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File des = new File("./Screenshots/Screenshot_"+System.currentTimeMillis()+".png");
		FileUtils.copyFile(screenshotAs,des);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Given("^Already in Edit Page$")
	public void already_in_Edit_Page() throws IOException {
		driver.findElement(By.linkText("Edit")).click();
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File des = new File("./Screenshots/Screenshot_"+System.currentTimeMillis()+".png");
		FileUtils.copyFile(screenshotAs,des);
	}

	@When("^Edit Page title$")
	public void edit_Page_Text(DataTable titleData) {
		List<List<String>> data = titleData.raw(); // can use asLists--same steps--converts in to lists of tables and
													// get data by row by col
		String editPageTitle = driver.getTitle();
		Assert.assertEquals(editPageTitle, data.get(0).get(0));
	}

	@Then("^Enter the Email Id$")
	public void enter_the_Email_Id(DataTable email) {
		List<String> emailData = email.asList(String.class); // converts the email table in to a list
		driver.findElement(By.id("email")).sendKeys(emailData.get(1));
		System.out.println(emailData.get(1));
	}

	@Then("^User Appends a text from  append Box$")

	public void user_Appends_a_text_from_append_Box(DataTable appendText) {

		// this can be done only when there are 2 columns --Converts the table to a
		// single Map.
		// The left column is used as keys, the right column as values.
		// Map<String, String> asMap = appendText.asMap(String.class , String.class);
		// asMap.get("table name")

		Map<String, String> asMap = appendText.asMap(String.class, String.class);

		// Converts the table to a List of Map. The top row is used as keys in the
		// maps,and the rows below are used as values.

		driver.findElement(By.xpath("//label[text()='Append a text and press keyboard tab']/following-sibling::input"))
				.sendKeys(asMap.get("AppendData"));
		String attribute = driver.findElement(By.xpath("//input[@value='TestLeaf']")).getAttribute("value");
		System.out.println("Txt Present inside the box is " + attribute);
	}

	@Then("^User clears the text from Clear Box$")
	public void user_clears_the_text_from_Clear_Box() throws IOException {
		driver.findElement(By.xpath("//input[@value='Clear me!!']")).clear();
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File des = new File("./Screenshots/Screenshot_"+System.currentTimeMillis()+".png");
		FileUtils.copyFile(screenshotAs,des);
	}

	@Then("^Verify that the edit field is disabled$")
	public void verify_that_the_edit_field_is_disabled(DataTable datas) throws IOException {

		WebElement disabledField = driver.findElement(
				By.xpath("//label[text()='Confirm that edit field is disabled']/following-sibling::input"));
		Assert.assertEquals(disabledField.isEnabled(), false);
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File des = new File("./Screenshots/Screenshot_"+System.currentTimeMillis()+".png");
		FileUtils.copyFile(screenshotAs,des);
		System.out.println("Done");
// Converts the table to a List of Map. The top row is used as keys in the maps,and the rows below are used as values.
		for (Map<String, String> data : datas.asMaps(String.class, String.class)) {
				data.get("DataSample");
				driver.get("https://onecognizant.cognizant.com/");
				System.out.println(data);
		}

	}

}

package DataDrivenStepsDef;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DropdownPageWithoutExamples{
	
	WebDriver driver ;
	@Given("^Already in Browser$")
	public void already_in_Browser() {
	   
		System.setProperty("webdriver.chrome.driver", "D:\\WS1\\CucumberPractice\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@When("^User is in leafgrounds$")
	public void user_is_in_leafgrounds() {
	   
		System.out.println("In Background");
		driver.get("http://leafground.com/");
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	    
	}

	@Then("^Maximize the window And do basic ettiquites$")
	public void maximize_the_window_And_do_basic_ettiquites() {
	   
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    
	}

	@Given("^User Already in dropdown Page$")
	public void user_Already_in_dropdown_Page() {
	   
		driver.findElement(By.linkText("Drop down")).click();
	}
// 1. \"(.*)\"
// 2. \"([^\"])*\"
	
	@Then("^Verify the Page Text is \"(.*)\"$")
	public void verify_the_Page_Text(String textonPage) {
	   
		String textOnPage = driver.findElement(By.xpath("//h1[text()='Learn Listboxes']")).getText();
		System.out.println(textOnPage);
		Assert.assertEquals(textOnPage, textonPage);
	    
	}

	@Then("^Verify select dropdown by Visible Text is \"(.*)\"$")
	public void verify_select_dropdown_by_Visible_Text(String VisbleText) {
	   
		Select select2 = new Select(driver.findElement(By.xpath("//select[@name='dropdown2']")));
		select2.selectByVisibleText(VisbleText);
	    
	}

	@Then("^Verify select dropdown by Index is (\\d+)$")
	public void verify_select_dropdown_by_Index(int index) {
		Select select1 = new Select(driver.findElement(By.xpath("//select[@id='dropdown1']")));
		select1.selectByIndex(index);
	    
	}

	@Then("^Verify select dropdown  by Value is \"(.*)\"$")
	public void verify_select_dropdown_by_Value(String Value) {
	   
		Select select3 = new Select(driver.findElement(By.xpath("//select[@id='dropdown3']")));
		select3.selectByValue(Value);
	}

	@Then("^Verify Multiple dropdown Selection$")
	public void verify_Multiple_dropdown_Selection() {
	   
		Select select = new Select(driver.findElement(By.xpath("//select[@class='dropdown']")));
		List<WebElement> options = select.getOptions();
		int size = options.size();
		for (int i = 0; i < size; i++) {
			String text = options.get(i).getText();
			System.out.println(text);
		}
	}

	@Then("^Verify Select dropdown by Sendkeys is \"([^\"]*)\"$")
	public void verify_Select_dropdown_by_Sendkeys(String inputValue) {
	    
		WebElement select4 = driver.findElement(By.xpath("(//select[@class='dropdown']/following::select)[1]"));
		select4.sendKeys(inputValue);
	}

	@Then("^Verify Deselect all dropdowns$")
	public void verify_Deselect_all_dropdowns() throws Exception {
	    
		Select select5 = new Select(
				driver.findElement(By.xpath("(//select[@class='dropdown']/following::select)[2]")));
		select5.selectByIndex(1);
		Thread.sleep(2000);
		select5.selectByIndex(2);
		Thread.sleep(2000);
		select5.selectByIndex(3);
		select5.deselectAll();
	    
	}

	
}

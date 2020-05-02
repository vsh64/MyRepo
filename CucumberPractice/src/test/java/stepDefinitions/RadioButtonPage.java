package stepDefinitions;

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
import cucumber.api.java.en.When;

public class RadioButtonPage {

	WebDriver driver;

	@Before
	public void setUp() {
		System.out.println("In Hooks");
		System.setProperty("webdriver.chrome.driver", "D:\\WS1\\CucumberPractice\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}


	@Given("^Navigate to leafgrounds$")
	public void navigate_to_leafgrounds_com() {
		
		System.out.println("In Background");
		driver.get("http://leafground.com/");
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

	}

	@When("^Maximize the window$")
	public void maximize_the_window() {

	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@Given("^Clicking on Radio Button Link$")
	public void clicking_on_Radio_Button_Link() {
		System.out.println("In Scenario");
		driver.findElement(By.linkText("Radio Button")).click();

	}

	@When("^Verify Text on Radio Button$")
	public void verify_Text_on_Radio_Button() {
		String TextOnPage = driver.findElement(By.xpath("//h1[text()='Play with Radio Buttons']")).getText();
		System.out.println(TextOnPage);
		Assert.assertEquals(TextOnPage, "Play with Radio Buttons");

	}

	@Then("^Click on Radio Button with Yes or No Option$")
	public void click_on_Radio_Button_with_Yes_or_No_Option() {
		String enjoyingClassText = driver.findElement(By.xpath("//label[text()='Are you enjoying the classes?']"))
				.getText();
		System.out.println(enjoyingClassText);
		Assert.assertEquals(enjoyingClassText, "Are you enjoying the classes?");
		driver.findElement(By.xpath("//input[@type='radio' and @id='yes']")).click();

	}

	@Then("^Verify the default checked Radio Button$")
	public void verify_the_default_checked_Radio_Button() {

		String defaultSelectedText = driver
				.findElement(By.xpath("//label[text()='Find default selected radio button']")).getText();
		System.out.println(defaultSelectedText);
		Assert.assertEquals(defaultSelectedText, "Find default selected radio button");
		WebElement checked = driver.findElement(By.xpath("(//input[@type='radio' and @name='news'])[2]"));
		if (checked.isSelected() == true)
			System.out.println(" Checked Radio button is selected by default");
		else
			driver.findElement(By.xpath("(//input[@type='radio' and @name='news'])[2]")).click();

	}

	@Then("^Select any Radio Button of your age group$")
	public void select_any_Radio_Button_of_your_age_group() {

		String ageGrpText = driver
				.findElement(By.xpath("//label[contains(text(),'Select your age group (Only if choice wasn')]"))
				.getText();
		System.out.println(ageGrpText);
		Assert.assertEquals(ageGrpText, "Select your age group (Only if choice wasn't selected)");
		List<WebElement> agesCheckbxs = driver.findElements(By.xpath("//input[@type='radio' and @name='age']"));
		for (int i = 0; i < agesCheckbxs.size(); i++) {
			if (!(agesCheckbxs.get(i).isSelected()))
				agesCheckbxs.get(1).click();
			else
				System.out.println("Already Choosen");
		}

	}
	@After
	public void tearDown() {
		driver.quit();
		System.out.println("Out Hooks");
	}
}

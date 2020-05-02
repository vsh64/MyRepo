package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import seleniumTestNG.ReadProperties;

public class Background extends ReadProperties {

		
	@Before
	public 	void beforeScenario() {
		
		
			System.out.println("Hello" + " .. here we go");
			System.setProperty("webdriver.chrome.driver", "D:\\WS1\\MavenProject\\Drivers\\chromedriver.exe");	String downLoadPath = "D:\\WS1\\MavenProject\\Downloads";
			
//			HashMap<String, Object> chromePref = new HashMap<String, Object>();
//			chromePref.put("profile.default_content_settings.popups", 0); // hide popups like save or save As
//			chromePref.put("download.default_directory", downLoadPath); // setting download path
//			chromePref.put("pdfjs.disabled", true);
//			
//			ChromeOptions options = new ChromeOptions();
//			options.setExperimentalOption("prefs", chromePref);
//	
	}
	
	@After
	public void afterScenario() {
		driver.quit();
	}
	
	@Given("^User Launches Chrome Broswer$")
	public void user_Launches_Chrome_Broswer() {

			 driver = new ChromeDriver();
		
	}

	@Then("^Initialize the setUp$")
	public void initialize_the_setUp() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		
	}

	@Then("^Navigate to leafgrounds Website$")
	public void navigate_to_leafgrounds_Website() {
		driver.get("http://leafground.com/");
		log.info("*************Url Launched**********");
		
	}

}

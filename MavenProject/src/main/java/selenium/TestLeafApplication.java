package selenium;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.omg.PortableServer.THREAD_POLICY_ID;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.server.handler.GetElementDisplayed;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadTimeoutException;

import okio.Timeout;

/**
 * @author Vsh
 *
 */
public class TestLeafApplication extends ReadProperties {

	public static WebDriver driver;
	public static Alert alert;

	String ClassName = this.getClass().getSimpleName();
	Logger log = Logger.getLogger(TestLeafApplication.class);

	@BeforeMethod
	public void beforeMethod() {
		try {
			driver.get(getPropertyValue("url"));
			log.info("*************Url Launched**********");
			driver.manage().window().maximize();
		} catch (IOException e) {
			System.out.println("######### Before Method Failed #########");
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void startUp() {
		try {
			Excel excel = new Excel(getPropertyValue("excelPath"), getPropertyValue("sheetName"));
			System.out.println("Hello" + " .. here we go");
			System.setProperty("webdriver.chrome.driver", "D:\\WS1\\MavenProject\\Drivers\\chromedriver.exe");

			String downLoadPath = "D:\\WS1\\MavenProject\\Downloads";

			HashMap<String, Object> chromePref = new HashMap<String, Object>();
			chromePref.put("profile.default_content_settings.popups", 0); // hide popups like save or save As
			chromePref.put("download.default_directory", downLoadPath); // setting download path
			chromePref.put("pdfjs.disabled", true);

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePref);

//			DesiredCapabilities cap = DesiredCapabilities.chrome();
//			cap.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(options);

		} catch (IOException e) {
			System.out.println("###########Start Up Method Failed###########");
			e.printStackTrace();
		}
	}

	@Test(description = " Verifying the Links",priority = 0, enabled = false)
	public void verifyLinks() {

		try {
			log.info(" ***********Get all the links in the home page**********");
			List<WebElement> links = driver.findElements(By.tagName("a"));
			int numOfLinks = links.size();
			System.out.println("Total  Numberf of Links " + numOfLinks);

			Iterator<WebElement> iterator = links.iterator();
			while (iterator.hasNext()) {
				String url = iterator.next().getAttribute("href");
				HttpURLConnection huc = null;
				huc = (HttpURLConnection) (new URL(url).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();
				int responseCode = huc.getResponseCode();
				if (responseCode >= 400)
					System.out.println(url + " is broken");
				else
					System.out.println(url + " is valid");

			}

//			for (WebElement link : links) {
//				System.out.println(link.getText());
//			}

//			for (int i = 0; i < numOfLinks; i++) {
//				String Link = links.get(i).getText();
//				System.out.println(i + ". " + Link);
//			}
		} catch (Exception e) {
			System.out.println("###### Verify links Test Case Failed ##########");
			e.printStackTrace();
		}
	}

	@DataProvider
	public Object[][] getTestData1() throws IOException {
		Object[][] testData = getTestData();
		return testData;

	}

	@Test(dataProvider = "getTestData1", priority = 1, enabled = false)
	public void verifyEdit(String email, String appendText) {

		try {
			log.info("********** Start Verifying Edit Functioanlity*********");
			driver.findElement(By.linkText("Edit")).click();
			log.info("Verify Title of Edit Page ");
			String editPageTitle = driver.getTitle();
			Assert.assertEquals(editPageTitle, Excel.getData(ClassName, "Edit Page Title"));
			driver.findElement(By.id("email")).sendKeys(Excel.getData(ClassName, email));
			driver.findElement(
					By.xpath("//label[text()='Append a text and press keyboard tab']/following-sibling::input"))
					.sendKeys(Excel.getData(ClassName, appendText));
			String attribute = driver.findElement(By.xpath("//input[@value='TestLeaf']")).getAttribute("value");
			System.out.println("Txt Present inside the box is " + attribute);
			driver.findElement(By.xpath("//input[@value='Clear me!!']")).clear();
			WebElement disabledField = driver.findElement(
					By.xpath("//label[text()='Confirm that edit field is disabled']/following-sibling::input"));
			Assert.assertEquals(disabledField.isEnabled(), false);
			log.info("Edit Functionality Verified succesfully");
		} catch (Exception e) {
			System.out.println("######## Edit Test Case Failed #########");
			e.printStackTrace();
		}
	}

	@Test(priority = 9, enabled = false)
	public void verifyAlertPage() {

		try {

			driver.findElement(By.linkText("Alert")).click();
			log.info("*********Verifying Alert Page**********");

			String normalAlertBoxText = driver
					.findElement(By.xpath("//label[text()='Click the button to display a alert box.']")).getText();
			System.out.println(normalAlertBoxText);
			Assert.assertEquals(normalAlertBoxText, "Click the button to display a alert box.");
			driver.findElement(By.xpath("//button[text()='Alert Box']")).click();
			String normalAlertText = driver.switchTo().alert().getText();
			Assert.assertEquals(normalAlertText, "I am an alert box!");
			driver.switchTo().alert().accept();

			String confirmBoxText = driver
					.findElement(By.xpath("//label[text()='Click the button to display a confirm box.']")).getText();
			System.out.println(confirmBoxText);
			Assert.assertEquals(confirmBoxText, "Click the button to display a confirm box.");
			driver.findElement(By.xpath("//button[text()='Confirm Box']")).click();
			String ConfirmAlertText = driver.switchTo().alert().getText();
			Assert.assertEquals(ConfirmAlertText, "Press a button!");
			driver.switchTo().alert().dismiss();
			driver.findElement(By.xpath("//button[text()='Confirm Box']")).click();
			driver.switchTo().alert().accept();
			String resultText = driver.findElement(By.xpath("//p[text()='You pressed OK!']")).getText();
			System.out.println(resultText);
			Assert.assertEquals(resultText, "You pressed OK!");

			String promptBoxText = driver
					.findElement(By.xpath("//label[text()='Click the button to display a prompt box.']")).getText();
			System.out.println(promptBoxText);
			Assert.assertEquals(promptBoxText, "Click the button to display a prompt box.");
			driver.findElement(By.xpath("//button[text()='Prompt Box']")).click();
			String promtAlertText = driver.switchTo().alert().getText();
			Assert.assertEquals(promtAlertText, "Please enter your training institute name");
			driver.switchTo().alert().sendKeys("TestLeaf");
			driver.switchTo().alert().accept();
			String result1Text = driver
					.findElement(By.xpath("//p[text()='You should have enjoyed learning at TestLeaf.']")).getText();
			System.out.println(result1Text);
			driver.findElement(By.xpath("//button[text()='Prompt Box']")).click();
			driver.switchTo().alert().sendKeys("abc");
			Assert.assertEquals(result1Text, "You should have enjoyed learning at TestLeaf.");
			driver.switchTo().alert().accept();
			String result2Text = driver.findElement(By
					.xpath("//p[text()='You should not have enjoyed learning at abc as compared to TestLeaf! Right?']"))
					.getText();
			System.out.println(result2Text);
			Assert.assertEquals(result2Text,
					"You should not have enjoyed learning at abc as compared to TestLeaf! Right?");

			String lineBreaksAlertBoxText = driver
					.findElement(By.xpath("//label[text()='Click the button to learn line-breaks in an alert.']"))
					.getText();
			System.out.println(lineBreaksAlertBoxText);
			Assert.assertEquals(lineBreaksAlertBoxText, "Click the button to learn line-breaks in an alert.");
			driver.findElement(By.xpath("//button[text()='Line Breaks?']")).click();
			String lineBreaksAlertText = driver.switchTo().alert().getText();
			System.out.println(lineBreaksAlertText);
			driver.switchTo().alert().accept();

			String SweetAlertBoxText = driver
					.findElement(By.xpath("//label[text()='Click the below button and click OK.']")).getText();
			System.out.println(SweetAlertBoxText);
			Assert.assertEquals(SweetAlertBoxText, "Click the below button and click OK.");
			driver.findElement(By.id("btn")).click();
			String sweetAlertText = driver.findElement(By.xpath("//div[text()='Happy Coding!']")).getText();
			System.out.println(sweetAlertText);
			Assert.assertEquals(sweetAlertText, "Happy Coding!");
			driver.findElement(By.xpath("//button[text()='OK']")).click();

			log.info("*********Alert Page Verified Successfully**********");

		} catch (Exception e) {
			System.out.println("#########Alert Test Case Failed############# ");
			e.printStackTrace();
		}
	}

	@Test(priority = 2, enabled = false)
	public void verifyButtonPage() {
		try {
			driver.findElement(By.linkText("Button")).click();
			log.info("******************Verifying Button Page ****************");

			String TextOnPage = driver.findElement(By.xpath("//h1[text()='Bond with Buttons']")).getText();
			System.out.println(TextOnPage);
			Assert.assertEquals(TextOnPage, "Bond with Buttons");

			String travelHomePageText = driver
					.findElement(By.xpath("//label[text()='Click button to travel home page']")).getText();
			System.out.println(travelHomePageText);
			Assert.assertEquals(travelHomePageText, "Click button to travel home page");
			driver.findElement(By.xpath("//button[text()='Go to Home Page']")).click();
			driver.findElement(By.linkText("Button")).click();

			String findPositionText = driver.findElement(By.xpath("//label[text()='Find position of button (x,y)']"))
					.getText();
			System.out.println(findPositionText);
			Assert.assertEquals(findPositionText, "Find position of button (x,y)");
			Point location = driver.findElement(By.xpath("//button[text()='Get Position']")).getLocation();
			System.out.println(location);

			String findBtnClrText = driver.findElement(By.xpath("//label[text()='Find button color']")).getText();
			System.out.println(findBtnClrText);
			Assert.assertEquals(findBtnClrText, "Find button color");
			WebElement elementColour = driver.findElement(By.xpath("//button[text()='What color am I?']"));
			String cssValue = elementColour.getCssValue("background-color");
			System.out.println(Color.fromString(cssValue).asHex());

			String HytnWidthText = driver.findElement(By.xpath("//label[text()='Find the height and width']"))
					.getText();
			System.out.println(HytnWidthText);
			Assert.assertEquals(HytnWidthText, "Find the height and width");
			WebElement elementSize = driver.findElement(By.xpath("//button[text()='What is my size?']"));
			int height = elementSize.getSize().getHeight();
			int width = elementSize.getSize().getWidth();
			System.out.println("Height : " + height + " and Width : " + width);

			log.info("******************Button Page Verified Successfully****************");
		} catch (Exception e) {
			System.out.println("######## Verify Button Page Method Failed ###########");
			e.printStackTrace();
		}

	}

	@Test(priority = 6, enabled = false)
	public void verifyRadioButtonPage() {
		try {
			driver.findElement(By.linkText("Radio Button")).click();

			log.info("************Verifying Radio Button Page*********");

			String TextOnPage = driver.findElement(By.xpath("//h1[text()='Play with Radio Buttons']")).getText();
			System.out.println(TextOnPage);
			Assert.assertEquals(TextOnPage, "Play with Radio Buttons");

			String enjoyingClassText = driver.findElement(By.xpath("//label[text()='Are you enjoying the classes?']"))
					.getText();
			System.out.println(enjoyingClassText);
			Assert.assertEquals(enjoyingClassText, "Are you enjoying the classes?");
			driver.findElement(By.xpath("//input[@type='radio' and @id='yes']")).click();

			String defaultSelectedText = driver
					.findElement(By.xpath("//label[text()='Find default selected radio button']")).getText();
			System.out.println(defaultSelectedText);
			Assert.assertEquals(defaultSelectedText, "Find default selected radio button");
			WebElement checked = driver.findElement(By.xpath("(//input[@type='radio' and @name='news'])[2]"));
			if (checked.isSelected() == true)
				System.out.println(" Checked Radio button is selected by default");
			else
				driver.findElement(By.xpath("(//input[@type='radio' and @name='news'])[2]")).click();

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

			log.info("************Verified Radio ButtonFrame Page  Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Radio Button Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 10, enabled = false)
	public void verifyFramePage() {
		try {
			driver.findElement(By.linkText("Frame")).click();

			log.info("************Verifying Frame Page*********");
			String textOnPage = driver.findElement(By.xpath("//h1[text()='Fun with frames']")).getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Fun with frames");
			driver.switchTo().frame(0);
			driver.findElement(By.id("Click")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(1);
			driver.switchTo().frame("frame2");
			driver.findElement(By.id("Click1")).click();
			driver.switchTo().defaultContent();
			List<WebElement> totalFrames = driver.findElements(By.tagName("iframe"));
			System.out.println("total iFrames are :" + totalFrames.size());

			log.info("************Verified Frame Page  Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Frame Method Failed##########");
			e.printStackTrace();
		}
	}


	@Test(priority = 11, enabled = false)
	public void verifyWindowPage() {
		try {
			driver.findElement(By.linkText("Window")).click();

			log.info("************Verifying Window Page*********");
			String textOnPage = driver.findElement(By.xpath("//h1[text()='Work with Windows']")).getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Work with Windows");

			driver.findElement(By.xpath("//button[@id='home']")).click();
			Set<String> windowHandles1 = driver.getWindowHandles();
			ArrayList<String> lst1 = new ArrayList<String>();
			lst1.addAll(windowHandles1);
			WebDriver window = driver.switchTo().window(lst1.get(1));
			window.manage().window().maximize();
			String title = window.getTitle();
			System.out.println(title);
			Assert.assertEquals(title, "TestLeaf - Selenium Playground");
			window.close();
			driver.switchTo().window(lst1.get(0));
			driver.findElement(By.xpath("//button[text()='Open Multiple Windows']")).click();

			Set<String> windowHandles = driver.getWindowHandles();
			ArrayList<String> lst = new ArrayList<String>();
			lst.addAll(windowHandles);
			WebDriver window2 = driver.switchTo().window(lst.get(1));
			String window2title = window2.getTitle();
			System.out.println(window2title);
			Assert.assertEquals(window2title, "TestLeaf - Interact with HyperLinks");
			window2.manage().window().maximize();
			window2.close();
			WebDriver window3 = driver.switchTo().window(lst.get(2));
			window3.manage().window().maximize();
			driver.findElement(By.xpath("//button[text()='Go to Home Page']")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().window(lst1.get(0));
//			driver.findElement(By.xpath("//button[text()='Wait for 5 seconds']")).click();
//			WebDriverWait wait = new WebDriverWait(driver, 10);
//			wait.until(ExpectedConditions.numberOfWindowsToBe(2));

			log.info("************Verified Window Page  Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Window Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 5, enabled = false)
	public void verifyDropdownPage() {
		try {
			driver.findElement(By.linkText("Drop down")).click();

			log.info("************Verifying Dropdown Page*********");
			String textOnPage = driver.findElement(By.xpath("//h1[text()='Learn Listboxes']")).getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Learn Listboxes");
			Select select = new Select(driver.findElement(By.xpath("//select[@class='dropdown']")));
			Select select1 = new Select(driver.findElement(By.xpath("//select[@id='dropdown1']")));
			Select select2 = new Select(driver.findElement(By.xpath("//select[@name='dropdown2']")));
			Select select3 = new Select(driver.findElement(By.xpath("//select[@id='dropdown3']")));
			WebElement select4 = driver.findElement(By.xpath("(//select[@class='dropdown']/following::select)[1]"));
			Select select5 = new Select(
					driver.findElement(By.xpath("(//select[@class='dropdown']/following::select)[2]")));
			select1.selectByIndex(2);
			select2.selectByVisibleText("Appium");
			select3.selectByValue("4");
			List<WebElement> options = select.getOptions();
			int size = options.size();
			for (int i = 0; i < size; i++) {
				String text = options.get(i).getText();
				System.out.println(text);
			}
			select4.sendKeys("Selenium");
			Thread.sleep(2000);
			select5.selectByIndex(1);
			Thread.sleep(2000);
			select5.selectByIndex(2);
			Thread.sleep(2000);
			select5.selectByIndex(3);
			select5.deselectAll();

			log.info("************Verified Dropdown Page  Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Dropdown Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 7, enabled = false)
	public void verifyCheckBoxPage() {
		try {
			driver.findElement(By.linkText("Checkbox")).click();

			log.info("************Verifying Checkbox Page*********");
			String textOnPage = driver.findElement(By.xpath("//h1[text()='Interact with Checkboxes']")).getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Interact with Checkboxes");

			String selectLang = driver.findElement(By.xpath("//label[text()='Select the languages that you know?']"))
					.getText();
			System.out.println(selectLang);
			Assert.assertEquals(selectLang, "Select the languages that you know?");
			driver.findElement(By.xpath("//div[text()='Java']/input")).click();
			driver.findElement(By.xpath("//div[text()='VB']/input")).click();
			driver.findElement(By.xpath("//div[text()='C']/input")).click();

			String confrmSelenium = driver.findElement(By.xpath("//label[text()='Confirm Selenium is checked']"))
					.getText();
			System.out.println(confrmSelenium);
			Assert.assertEquals(confrmSelenium, "Confirm Selenium is checked");
			if (driver.findElement(By.xpath("//div[text()='Selenium']/input")).isSelected())
				System.out.println("Selenium is Checked");
			else
				System.out.println("Selenium is not Checked");

			String deselectChkd = driver.findElement(By.xpath("//label[text()='DeSelect only checked']")).getText();
			System.out.println(deselectChkd);
			Assert.assertEquals(deselectChkd, "DeSelect only checked");
			if (driver.findElement(By.xpath("//div[text()='I am Selected']/input")).isSelected())
				driver.findElement(By.xpath("//div[text()='I am Selected']/input")).click();
			else
				System.out.println("Do Nothing");

			String selectAllChkBoxes = driver.findElement(By.xpath("//label[text()='Select all below checkboxes ']"))
					.getText();
			System.out.println(selectAllChkBoxes);
			Assert.assertEquals(selectAllChkBoxes, "Select all below checkboxes");
			List<WebElement> optionsChk = driver.findElements(By.xpath("//div[contains(text(),'Option')]/input"));

			for (int i = 0; i < optionsChk.size(); i++) {
				optionsChk.get(i).click();
				Thread.sleep(2000);
			}

			log.info("************Verified Checkbox Page  Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Checkbox Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 21, enabled = false , dependsOnMethods= {"verifyWaitForAlertPage","verifyWaitForTextChangePage"})
	public void verifyWaitToDisappearPage() {
		try {
			driver.findElement(By.linkText("Wait to Disappear")).click();

			log.info("************Verifying Wait to Disappear Page*********");

			String textOnPage = driver
					.findElement(By.xpath("//h1[contains(text(),'Explicit Wait for Element Disappearance')]"))
					.getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Explicit Wait for Element Disappearance");

			List<WebElement> texts = driver.findElements(By.xpath("//div/ul/following::li"));
			String text1 = texts.get(0).getText();
			Assert.assertEquals(text1, "1. Once Page has been loaded, You'll be able to see a BUTTON");
			String text2 = texts.get(1).getText();
			Assert.assertEquals(text2, "2. Make sure that button disappeared");
			String text3 = texts.get(2).getText();
			Assert.assertEquals(text3, "3. Once it disappeared you can find a text saying disappeared, verify that");

			WebElement disappearEle = driver
					.findElement(By.xpath("//b[contains(text(),'going to disappear. Keep looking at me!!')]"));
			disappearEle.isDisplayed();
			driver.findElement(By.xpath("//p[contains(text(),'Wait for few seconds. If the above button get "
					+ "disappeared then confirm that you have')]")).isDisplayed();
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.invisibilityOf(disappearEle));
			Assert.assertEquals(driver
					.findElement(By.xpath("//strong[contains(text(),'I know you can do it! Button is disappeared!')]"))
					.isDisplayed(), true);

			log.info("************Verified Wait to Disappear Page  Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Wait to Disappear Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 22, enabled = false)
	public void verifyWaitToAppearPage() {
		try {
			driver.findElement(By.linkText("Wait to Appear")).click();

			log.info("************Verifying Wait to Appear Page*********");

			String textOnPage = driver
					.findElement(By.xpath("//h1[contains(text(),'Explicit Wait for Element to Appears')]")).getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Explicit Wait for Element to Appears");

			List<WebElement> buttons = driver.findElements(By.xpath("//button"));
			for (int i = 0; i < buttons.size(); i++) {
				System.out.println(buttons.get(i).getText());
			}
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfAllElements(buttons));
			for (int i = 0; i < buttons.size(); i++) {
				System.out.println(buttons.get(i).getText());
			}

			log.info("************Verified Wait to Appear Page  Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Wait to Appear Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 23, enabled = false , groups = {"TeamB"})
	public void verifyWaitForTextChangePage() {
		try {
			driver.findElement(By.linkText("Wait for Text Change")).click();

			log.info("************Verifying Wait for Text Change Page*********");

			String textOnPage = driver.findElement(By.xpath("//h1[contains(text(),'Explicit Wait for Text Change')]"))
					.getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Explicit Wait for Text Change");
			WebElement textChangeBtn = driver.findElement(By.xpath("//button[@id='btn']"));
			Assert.assertEquals(textChangeBtn.isDisplayed(), true);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.textToBe(By.xpath("//button[@id='btn']"), "Click ME!"));
			String clickMe = textChangeBtn.getText();
			System.out.println(clickMe);
			textChangeBtn.click();
			String alertText = driver.switchTo().alert().getText();
			Assert.assertEquals(alertText, clickMe);
			driver.switchTo().alert().accept();

			log.info("************Verified Wait for Text Change Page  Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Wait for Text Change Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 24, enabled = true , groups = {"TeamB"},invocationCount=5)
	public void verifyWaitForAlertPage() {
		try {
			driver.findElement(By.linkText("Wait for Alert")).click();

			log.info("************Verifying Wait for Alert Page*********");

			String textOnPage = driver
					.findElement(By.xpath("//h1[contains(text(),'Explicit Wait for Alert Appearance')]")).getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Explicit Wait for Alert Appearance");
			driver.findElement(By.xpath("//button[@id='alert']")).click();
			WebDriverWait wait = new WebDriverWait(driver, 7);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();

			log.info("************Verified Wait for Alert  Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Wait for Alert Method Failed##########");
			e.printStackTrace();
		}
	}
	
	@Parameters({"linkName","Text on Page","XpathOfDraggable"})
	@Test(priority = 13, enabled = false )
	public void verifyDraggablePage(String link,String text,String xpath) {
		try {
			driver.findElement(By.linkText(link)).click();

			log.info("************Verifying Draggable Page*********");

			String textOnPage = driver.findElement(By.xpath("//h1[contains(text(),'Play with Draggable')]")).getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, text);
			WebElement drag = driver.findElement(By.xpath(xpath));
			Point location = drag.getLocation();
			System.out.println(location);
			Actions action = new Actions(driver);
			action.dragAndDropBy(drag, 473, 273).perform(); // can use series of actions.perform()
			Thread.sleep(2000);

			log.info("************Verified Draggable Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Draggable Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 14, enabled = false)
	public void verifyDroppablePage() {
		try {
			driver.findElement(By.linkText("Droppable")).click();

			log.info("************Verifying Droppable Page*********");

			String textOnPage = driver.findElement(By.xpath("//h1[contains(text(),'Play with Droppable')]")).getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Play with Droppable");
			WebElement dragg = driver.findElement(By.xpath("//div[@id='draggable']"));
			WebElement dropp = driver.findElement(By.xpath("//div[@id='droppable']"));
			Actions action = new Actions(driver);
			action.dragAndDrop(dragg, dropp).perform();
			Thread.sleep(2000);

			log.info("************Verified Droppable Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Droppable Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 15, enabled = false , groups={"TeamC"})
	public void verifySelectablePage() {
		try {
			driver.findElement(By.linkText("Selectable")).click();

			log.info("************Verifying Selectable Page*********");

			String textOnPage = driver.findElement(By.xpath("//h1[contains(text(),'Play with Selectable')]")).getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Play with Selectable");
			List<WebElement> items = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
			int totalItems = items.size();
			for (int i = 0; i < totalItems; i++) {
				items.get(i).click();
				Thread.sleep(2000);
				WebElement item = driver.findElement(By.xpath("(//ol[@id='selectable']/li)[" + (i + 1) + "]"));
				String text = item.getText();
				System.out.println(text);
				Assert.assertEquals(item.isSelected(), false);
			}

			log.info("************Verified Selectable Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Selectable Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 16, enabled = false , timeOut= 100 , groups = {"TeamC","TeamB"}, expectedExceptions ={ThreadTimeoutException.class})
	public void verifySortablePage() {
		try {
			driver.findElement(By.linkText("Sortable")).click();

			log.info("************Verifying Sortable Page*********");

			String textOnPage = driver.findElement(By.xpath("//h1[contains(text(),'Play with Sortable')]")).getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Play with Sortable");

			WebElement item2 = driver.findElement(By.xpath("//li[text()='Item 2']"));
			WebElement item7 = driver.findElement(By.xpath("//li[text()='Item 7']"));
			WebElement item1 = driver.findElement(By.xpath("//li[text()='Item 1']"));
			WebElement item3 = driver.findElement(By.xpath("//li[text()='Item 3']"));
			WebElement item4 = driver.findElement(By.xpath("//li[text()='Item 4']"));
			WebElement item6 = driver.findElement(By.xpath("//li[text()='Item 6']"));
			Actions action = new Actions(driver);
			action.clickAndHold(item2).moveToElement(item7).perform();
			Thread.sleep(1000);
			action.clickAndHold(item4).moveToElement(item6).perform();
			Thread.sleep(1000);
			action.clickAndHold(item1).moveToElement(item3).perform();
			Thread.sleep(10000);

			List<WebElement> items = driver.findElements(By.xpath("//ul[@id='sortable']/li"));
			ArrayList<String> lstOrg1 = new ArrayList<String>();
			ArrayList<String> lstOrg = new ArrayList<String>();

			for (int i = 0; i < items.size(); i++) {

				String itemsTextAfterAlter = items.get(i).getText();
				lstOrg1.add(itemsTextAfterAlter);
			}
			lstOrg = lstOrg1;
			Collections.sort(lstOrg1);
			Assert.assertEquals(lstOrg1, lstOrg);

			log.info("************Verified Sortable Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Sortable Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 17, enabled = false)
	public void verifyAutoCompletePage() {
		try {
			driver.findElement(By.linkText("Auto Complete")).click();

			log.info("************Verifying Auto Complete Page*********");

			String textOnPage = driver.findElement(By.xpath("//h1[contains(text(),'Interact with Auto Complete')]"))
					.getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Interact with Auto Complete");
			WebElement courseName = driver.findElement(By.cssSelector("input[id='tags']"));
			courseName.sendKeys("A", Keys.ENTER);

			List<WebElement> options = driver.findElements(By.cssSelector("div[class='ui-menu-item-wrapper']"));

			for (int i = 0; i < options.size(); i++) {
				String text = options.get(i).getText();
				if (text.equalsIgnoreCase("Protractor")) {
					options.get(i).click();
				}
			}
			Thread.sleep(3000);

			log.info("************Verified Auto Complete Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Auto Complete Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 18, enabled = false)
	public void verifyDownLoadFilesPage() {
		try {
			driver.findElement(By.linkText("DownLoad Files")).click();

			log.info("************Verifying DOWNLOAD FILES Page*********");

			String textOnPage = driver.findElement(By.xpath("//h2[contains(text(),'Download Files')]")).getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "DOWNLOAD FILES");

			WebElement excel = driver.findElement(By.linkText("Download Excel"));
			WebElement pdf = driver.findElement(By.linkText("Download PDF"));
			WebElement txt = driver.findElement(By.linkText("Download Text"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", excel);
			Thread.sleep(5000);
			js.executeScript("arguments[0].click();", txt);
			driver.navigate().back();
			Thread.sleep(5000);
//			js.executeScript("arguments[0].click();", pdf);
//			Thread.sleep(5000);
			log.info("************Verified DOWNLOAD FILES Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify DOWNLOAD FILES Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 19, enabled = false)
	public void verifyUploadFilesPage() {
		try {
			driver.findElement(By.linkText("Upload Files")).click();

			log.info("************Verifying Upload File Page*********");

			String textOnPage = driver.findElement(By.xpath("//h1[contains(text(),'Upload File')]")).getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Upload File");
			driver.findElement(By.xpath("//input[@type='file']")).sendKeys(getPropertyValue("excelPath"));
			Thread.sleep(3000);

			log.info("************Verified Upload File Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Upload File Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 20, enabled = false)
	public void verifyToolTipPage() {
		try {
			driver.findElement(By.linkText("Tool Tip")).click();

			log.info("************Verifying Tool Tip Page*********");

			String textOnPage = driver.findElement(By.xpath("//h1[contains(text(),'Interact with Tool Tip')]"))
					.getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Interact with Tool Tip");

			// Tool tip will be attribute - title(use getAttribute) or with tagname div (use
			// Actions Class)
			WebElement ele = driver.findElement(By.xpath("//input[@id='age']"));
			String toolTipText = ele.getAttribute("title");
			System.out.println(toolTipText);
			Thread.sleep(2000);
			Assert.assertEquals(toolTipText, "Enter your Name");

//			WebElement ele1 = driver.findElement(By.xpath("//div[@id='age']"));
//			Actions action = new Actions(driver);
//			action.moveToElement(ele1).perform();
//			String tooltipTxt2 = ele1.getText();
//			System.out.println("jsjs"+tooltipTxt2);

			log.info("************Verified Tool Tip Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Tool Tip Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 25, enabled = false)
	public void verifyMouseHoverPage() {
		
		driver.findElement(By.linkText("Mouse Hover1")).click();
		try {

			log.info("************Verifying Mouse Hover Page*********");

			String textOnPage = driver.findElement(By.xpath("//h1[contains(text(),'Mouse Hover using Actions')]"))
					.getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Mouse Hover using Actions");

			WebElement ele = driver.findElement(By.xpath("//a[text()='TestLeaf Courses']"));
			WebElement ele1 = driver.findElement(By.xpath("//a[text()='Selenium']"));

			Actions action = new Actions(driver);
			action.moveToElement(ele).moveToElement(ele1).click().perform();

			driver.switchTo().alert().accept();

			log.info("************Verified Mouse Hover Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Mouse Hover Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 3, enabled = false )
	public void verifyHyperLinksPage() {
		try {
			driver.findElement(By.linkText("HyperLink")).click();

			log.info("************Verifying HyperLinks Page*********");

			String textOnPage = driver.findElement(By.xpath("//h1[contains(text(),'Play with HyperLinks')]")).getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Play with HyperLinks");

			WebElement homepageLink = driver.findElement(By.linkText("Go to Home Page"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", homepageLink);
			Thread.sleep(10000);
			driver.findElement(By.linkText("HyperLink")).click();
			WebElement findlink = driver.findElement(By.linkText("Find where am supposed to go without clicking me?"));
			String attribute = findlink.getAttribute("href");
			System.out.println(attribute);

			// Get all the links in the Page
			List<WebElement> links = driver.findElements(By.tagName("a"));
			System.out.println("totalLinks =" + links.size());
			for (WebElement link : links) {
				String text = link.getText();
				System.out.println(text);
			}
			// verify if there are any broken link (server not reachable links)
			List<WebElement> AllLinks = driver.findElements(By.tagName("a"));
			Iterator<WebElement> iterator = AllLinks.iterator(); // To traverse through a list we can use iterator ,
																	// foreach , for loop
			while (iterator.hasNext()) {
				String url = iterator.next().getAttribute("href");
				HttpURLConnection huc = null;
				huc = (HttpURLConnection) (new URL(url).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();
				int responseCode = huc.getResponseCode();
				if (responseCode >= 400)
					System.out.println(url + " is broken");
				else
					System.out.println(url + " is valid");
			}

			log.info("************Verified HyperLinks Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify HyperLinks Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 4, enabled = false)
	public void verifyImagePage() {
		try {
			driver.findElement(By.linkText("Image")).click();

			log.info("************Verifying Image Page*********");

			String textOnPage = driver.findElement(By.xpath("//h1[contains(text(),'Interact with Images')]")).getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Interact with Images");
			Thread.sleep(3000);
			WebElement img1 = driver
					.findElement(By.xpath("//label[text()='Click on this image to go home page']/following::img[1]"));
			Actions action = new Actions(driver);
			action.moveToElement(img1).click().perform();

			System.out.println("ccdav");

			driver.navigate().to(getPropertyValue("url"));
			driver.findElement(By.linkText("Image")).click();

			List<WebElement> images = driver.findElements(By.tagName("img"));
			System.out.println("total images = " + images.size());
			Iterator<WebElement> iterator = images.iterator();
			while (iterator.hasNext()) {
				String srcImg = iterator.next().getAttribute("src");
				HttpClient client = HttpClientBuilder.create().build();
				HttpGet request = new HttpGet(srcImg);
				HttpResponse response = client.execute(request);
				int responseCode = response.getStatusLine().getStatusCode();
				if (responseCode != 200)
					System.out.println(srcImg + " is Broken");
				else
					System.out.println(srcImg + " is Valid");

			}

			WebElement img3 = driver
					.findElement(By.xpath("//label[text()='Click on this image to go home page']/following::img[3]"));
			action.moveToElement(img3).click().perform();

			log.info("************Verified Image Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Image Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 8, enabled = false)
	public void verifyTablePage() {
		try {
			driver.findElement(By.linkText("Table")).click();

			log.info("************Verifying Table Page*********");

			String textOnPage = driver.findElement(By.xpath("//h1[contains(text(),'Toy with Tables')]")).getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Toy with Tables");

			// to get data from a simple Table (Static )
			WebElement table = driver.findElement(By.xpath("//table[@id='table_id']"));
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			List<WebElement> cell = rows.get(2).findElements(By.tagName("td"));
			List<WebElement> headers = table.findElements(By.tagName("tr")).get(0).findElements(By.tagName("th"));
			String data = cell.get(0).getText();
			System.out.println("data = " + data);

			// To get dynamic values from the table with Header name and row data
			for (int i = 0; i < headers.size(); i++) {// looping headers
				if (headers.get(i).getText().equalsIgnoreCase("Progress")) {
					for (int j = 1; j < rows.size(); j++) {// looping rows
						for (int k = 0; k < rows.get(j).findElements(By.tagName("td")).size(); k++) {// looping cells
							if (rows.get(j).findElements(By.tagName("td")).get(k).getText().equalsIgnoreCase("Gopi")) {
								String finalData = table.findElements(By.tagName("tr")).get(j)
										.findElements(By.tagName("td")).get(i).getText();
								System.out.println("final Data is " + finalData);
							}
						}
					}
				}
			}

			String Progress = table
					.findElement(By.xpath("//td[text()='Learn to interact with Elements']/following-sibling::td[1]"))
					.getText();
			System.out.println("Header value is " + Progress);

			driver.findElement(By.xpath("//td[text()='" + Progress + "']/following-sibling::td[1]/input")).click();
			Thread.sleep(3000);

			log.info("************Verified Table Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Table Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 12, enabled = false , retryAnalyzer=selenium.RetryAnalyzer.class)
	public void verifyCalendarPage() {
		
		driver.findElement(By.linkText("Calenda1r")).click();
		
		try {
			log.info("************Verifying Calendar Page*********");

			String textOnPage = driver.findElement(By.xpath("//h1[contains(text(),'Get Started With Calendars')]"))
					.getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Get Started With Calendars");

			driver.findElement(By.cssSelector("input[id='datepicker']")).click();
			Thread.sleep(3000);
			WebElement table = driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']"));
			List<WebElement> weeks = table.findElements(By.tagName("tr"));
			for (int i = 0; i < weeks.size(); i++) {
				List<WebElement> dates = weeks.get(i).findElements(By.tagName("td"));
				for (int j = 0; j < dates.size(); j++) {
					String date = dates.get(j).getText();
					if(date.equalsIgnoreCase("10")) {
						System.out.println(date);	
					dates.get(j).click();
				}
			}
			}
			log.info("************Verified Calendar Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Calendar Method Failed##########");
			e.printStackTrace();
		}
	}

	@Test(priority = 26, enabled = false , retryAnalyzer= RetryAnalyzer.class)
	public void verifyAdvanceWebTablePage() {
		driver.findElement(By.linkText("Advance Web Table1")).click();
		
		try {
			log.info("************Verifying Advance Web Table Page*********");

			String textOnPage = driver.findElement(By.xpath("//h1[contains(text(),'Sort Table')]")).getText();
			System.out.println(textOnPage);
			Assert.assertEquals(textOnPage, "Sort Table");

			WebElement table = driver.findElement(By.xpath("//div[@id='table_id_wrapper']"));
			List<WebElement> headers = table.findElements(By.tagName("th"));
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			List<WebElement> cells = rows.get(1).findElements(By.tagName("td"));

			String headerdata = "";
			int i = 1, h = 0;
			ArrayList<String> headerList = new ArrayList<String>();
			ArrayList<String> orginalLst = new ArrayList<String>();
			ArrayList<String> sortedList = new ArrayList<String>();
			for (h = 0; h < headers.size(); h++) {// loping through headers
				String headerName = headers.get(h).getText();
				if (headerName.equalsIgnoreCase("designation")) {
					for (i = 1; i < rows.size(); i++) { // looping through rows
						headerdata = rows.get(i).findElements(By.tagName("td")).get(h).getText(); // getting exact cell
																									// data
						System.out.println(headerdata);
						headerList.add(headerdata); // adding all the data to the list
					}
					headers.get(h).click();
					Thread.sleep(3000);
				}
			}

			orginalLst = headerList; // saving the list in a new list to keep the orginal data before sorting
			System.out.println("Orginal Data before sorting=" + orginalLst);
			Collections.sort(headerList); // sorting all the data
			System.out.println("Sorted Data before Clicking Header=" + headerList);

			WebElement table1 = driver.findElement(By.xpath("//div[@id='table_id_wrapper']"));
			List<WebElement> headers1 = table1.findElements(By.tagName("th"));
			List<WebElement> rows1 = table1.findElements(By.tagName("tr"));

			String headerdata1 = "";

			for (h = 0; h < headers1.size(); h++) {// lopping through headers
				String headerName1 = headers1.get(h).getText();
				if (headerName1.equalsIgnoreCase("designation")) {
					for (i = 1; i < rows1.size(); i++) { // looping through rows
						headerdata1 = rows1.get(i).findElements(By.tagName("td")).get(h).getText(); // getting exact
																									// cell data
						sortedList.add(headerdata1); // adding all the data to the list
					}
				}
			}
			System.out.println("Sorted Data Aftr clicking Header =" + sortedList);

			if (headerList.equals(sortedList))
				System.out.println("Sorting Functionality Working Fine");
			else
				System.out.println("Sorting functionality Failed");

			log.info("************Verified Advance Web Table Successfully*********");
		} catch (Exception e) {
			System.out.println("########## Verify Advance Web Table Method Failed##########");
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void afterMethod() {
		driver.navigate().refresh();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	enum LinkNames {
		Edit("Edit"), Button("Button"), HyperLink("HyperLink"), Image("Image"), Dropdown("Drop down"),
		RadioButton("Radio Button"), Checkbox("Checkbox"), Table("Table"), Alert("Alert"), Frame("Frame"),
		Window("Window"), Calendar("Calendar"), Draggable("Draggable"), Droppable("Droppable"),
		Selectable("Selectable"), Sortable("Sortable"), AutoComplete("Auto Complete"), DownLoadFiles("DownLoad Files"),
		UploadFiles("Upload Files"), ToolTip("Tool Tip"), WaittoDisappear("Wait to Disappear"),
		WaittoAppear("Wait to Appear"), WaitforTextChange("Wait for Text Change"), WaitforAlert("Wait for Alert"),
		MouseHover("Mouse Hover"), AdvanceWebTable("Advance Web Table");

		private String Links;

		LinkNames(String Links) {
			this.Links = Links;
		}
	}

}

package stepDefinitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import seleniumTestNG.ReadProperties;

public class RadioButtonPage extends ReadProperties{
	
	@Given("^In TestLeaf Radio Button Page$")
	public void in_TestLeaf_Radio_Button_Page() {
		driver.findElement(By.linkText("Radio Button")).click();
	    
	}

	@When("Radio Button Page contains the Heading - {string}")
	public void radio_Button_Page_contains_the_Heading(String text) {
		String TextOnPage = driver.findElement(By.xpath("//h1[text()='"+text+"']")).getText();
		System.out.println(TextOnPage);
		Assert.assertEquals(TextOnPage, text);
	    
	}
	

	@Then("^Click on Are you enjoying Classes - Yes Button$")
	public void click_on_Yes_Button() {
		String enjoyingClassText = driver.findElement(By.xpath("//label[text()='Are you enjoying the classes?']"))
				.getText();
		System.out.println(enjoyingClassText);
		Assert.assertEquals(enjoyingClassText, "Are you enjoying the classes?");
		driver.findElement(By.xpath("//input[@type='radio' and @id='yes']")).click();
	    
	}

	@Then("verify the default selected Button of {string}")
	public void verify_the_default_selected_Button_of(String text) {
		String defaultSelectedText = driver
				.findElement(By.xpath("//label[text()='"+text+"']")).getText();
		System.out.println(defaultSelectedText);
		Assert.assertEquals(defaultSelectedText, text);
		WebElement checked = driver.findElement(By.xpath("(//input[@type='radio' and @name='news'])[2]"));
		if (checked.isSelected() == true)
			System.out.println(" Checked Radio button is selected by default");
		else
			driver.findElement(By.xpath("(//input[@type='radio' and @name='news'])[2]")).click();

	    
	}

	@Then("^Select an Age Group Radio Button$")
	public void select_an_Age_Group_Radio_Button_of() {
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


}

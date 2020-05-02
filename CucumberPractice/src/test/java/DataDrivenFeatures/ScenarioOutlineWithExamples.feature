Feature: Verify Calendar Page using Examples 
Description: This is to verify Calendar Page using Examples and scenario Outline

Background: Initial Steps
	Given User lauches the browser
	Then Navigate to leafgrounds
	And Maximize window
	
@DataDrivenScenarioOutlineWithExamplesKeyword
Scenario Outline: Selecting Date in Calendar Page using examples keyword 

	Given Already in Calendar Page
	Then Verify the text on Page is "<TextOnPage>"
	Then Click on Date Picker
	And Select Date as "<Date>"
	
Examples: 
|TextOnPage|Date|
|Get Started With Calendars|13|
|Get Started With Calendars|12|
|Get Started With Calendars|11|



	






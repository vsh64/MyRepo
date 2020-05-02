Feature: Verify Dropdown Page using scenario without examples
Description: This is to check dropdown page using scenario without examples

Background: Initialize setUp
Given Already in Browser 
When User is in leafgrounds 
Then Maximize the window And do basic ettiquites

@DataDrivenScenarioWithoutExamples
Scenario: This scenario is to validate all the dropdown related commands without Examples Keyword
	Given User Already in dropdown Page 
	Then Verify the Page Text is "Learn Listboxes"
	Then Verify select dropdown by Visible Text is "Appium"
	And Verify select dropdown by Index is 2
	And Verify select dropdown  by Value is "4"
	Then Verify Multiple dropdown Selection 
	And Verify Select dropdown by Sendkeys is "Selenium"
	And Verify Deselect all dropdowns


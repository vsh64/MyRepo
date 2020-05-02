Feature: To Verify Radio Button Functionality
Description: The purpose of this feature file is to Test Radio Button

Background: Initialize setUp
Given Navigate to leafgrounds
When Maximize the window

@e2eTests @smokeTests
Scenario: Verify Radio Page1
	Given Clicking on Radio Button Link
	When Verify Text on Radio Button 
	Then Click on Radio Button with Yes or No Option
	And Verify the default checked Radio Button
	And  Select any Radio Button of your age group
	
@smokeTests	
Scenario: Verify Radio Page2
	Given Clicking on Radio Button Link
	When Verify Text on Radio Button 
	Then Click on Radio Button with Yes or No Option
	And Verify the default checked Radio Button
	And  Select any Radio Button of your age group
	
@RegressionTest @smokeTests
Scenario: Verify Radio Page2
	Given Clicking on Radio Button Link
	When Verify Text on Radio Button 
	Then Click on Radio Button with Yes or No Option
	And Verify the default checked Radio Button
	And  Select any Radio Button of your age group 


@e2eTests @sanityTests	
Scenario: Verify Radio Page4
	Given Clicking on Radio Button Link
	When Verify Text on Radio Button 
	Then Click on Radio Button with Yes or No Option
	And Verify the default checked Radio Button
	And  Select any Radio Button of your age group
	
@e2eTests @smokeTests
Scenario: Verify Radio Page5
	Given Clicking on Radio Button Link
	When Verify Text on Radio Button 
	Then Click on Radio Button with Yes or No Option
	And Verify the default checked Radio Button
	And  Select any Radio Button of your age group
	
@smokeTests	
Scenario: Verify Radio Page6
	Given Clicking on Radio Button Link
	When Verify Text on Radio Button 
	Then Click on Radio Button with Yes or No Option
	And Verify the default checked Radio Button
	And  Select any Radio Button of your age group
	
@RegressionTest @smokeTests
Scenario: Verify Radio Page7
	Given Clicking on Radio Button Link
	When Verify Text on Radio Button 
	Then Click on Radio Button with Yes or No Option
	And Verify the default checked Radio Button
	And  Select any Radio Button of your age group 


@e2eTests @sanityTests	
Scenario: Verify Radio Page8
	Given Clicking on Radio Button Link
	When Verify Text on Radio Button 
	Then Click on Radio Button with Yes or No Option
	And Verify the default checked Radio Button
	And  Select any Radio Button of your age group
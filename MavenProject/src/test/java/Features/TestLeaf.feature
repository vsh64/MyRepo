Feature: Test Leaf Application
Description: The purpose of this feature file is to test TestLeaf Application in Cucumber

Background: User Logged into TestLeafApp
Given User Launches Chrome Broswer
Then Initialize the setUp
And Navigate to leafgrounds Website

Scenario: Verify 'Radio Button' Functionality 

Given In TestLeaf Radio Button Page
When Radio Button Page contains the Heading - 'Play with Radio Buttons'
Then Click on Are you enjoying Classes - Yes Button
And verify the default selected Button of 'Find default selected radio button'
And Select an Age Group Radio Button 
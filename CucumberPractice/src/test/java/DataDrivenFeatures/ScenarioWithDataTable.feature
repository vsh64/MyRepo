Feature: Verify Edit Page Using Data Table
Description: This feature is to verify Edit page using Data Tables

@DataDrivenWithDataTables	
Scenario: Verify Edit page fields 
	Given Already in Edit Page 
	When Edit Page title 
	|TestLeaf - Interact with Edit Fields|
	Then Enter the Email Id 
	|aa@a.coms|
	|bb@a.coms|
	Then User Appends a text from  append Box
	|AppendData|TestLeaf|
	
	
	And User clears the text from Clear Box
	Then Verify that the edit field is disabled
	|DataSample|
	|Data1|
	|Data2|
	|Data3|
	|Data4|


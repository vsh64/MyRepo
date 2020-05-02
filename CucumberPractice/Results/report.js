$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("ScenarioWithoutExamples.feature");
formatter.feature({
  "line": 1,
  "name": "Verify Dropdown Page using scenario without examples",
  "description": "Description: This is to check dropdown page using scenario without examples",
  "id": "verify-dropdown-page-using-scenario-without-examples",
  "keyword": "Feature"
});
formatter.before({
  "duration": 20370642700,
  "status": "passed"
});
formatter.background({
  "line": 4,
  "name": "Initialize setUp",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "Already in Browser",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "User is in leafgrounds",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Maximize the window And do basic ettiquites",
  "keyword": "Then "
});
formatter.match({
  "location": "DropdownPageWithoutExamples.already_in_Browser()"
});
formatter.result({
  "duration": 8219747900,
  "status": "passed"
});
formatter.match({
  "location": "DropdownPageWithoutExamples.user_is_in_leafgrounds()"
});
formatter.result({
  "duration": 2311287900,
  "status": "passed"
});
formatter.match({
  "location": "DropdownPageWithoutExamples.maximize_the_window_And_do_basic_ettiquites()"
});
formatter.result({
  "duration": 4239129300,
  "status": "passed"
});
formatter.scenario({
  "line": 10,
  "name": "This scenario is to validate all the dropdown related commands without Examples Keyword",
  "description": "",
  "id": "verify-dropdown-page-using-scenario-without-examples;this-scenario-is-to-validate-all-the-dropdown-related-commands-without-examples-keyword",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 9,
      "name": "@DataDrivenScenarioWithoutExamples"
    }
  ]
});
formatter.step({
  "line": 11,
  "name": "User Already in dropdown Page",
  "keyword": "Given "
});
formatter.step({
  "line": 12,
  "name": "Verify the Page Text is \"Learn Listboxes\"",
  "keyword": "Then "
});
formatter.step({
  "line": 13,
  "name": "Verify select dropdown by Visible Text is \"Appium\"",
  "keyword": "Then "
});
formatter.step({
  "line": 14,
  "name": "Verify select dropdown by Index is 2",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "Verify select dropdown  by Value is \"4\"",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "Verify Multiple dropdown Selection",
  "keyword": "Then "
});
formatter.step({
  "line": 17,
  "name": "Verify Select dropdown by Sendkeys is \"Selenium\"",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "Verify Deselect all dropdowns",
  "keyword": "And "
});
formatter.match({
  "location": "DropdownPageWithoutExamples.user_Already_in_dropdown_Page()"
});
formatter.result({
  "duration": 977121800,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Learn Listboxes",
      "offset": 25
    }
  ],
  "location": "DropdownPageWithoutExamples.verify_the_Page_Text(String)"
});
formatter.result({
  "duration": 331048100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Appium",
      "offset": 43
    }
  ],
  "location": "DropdownPageWithoutExamples.verify_select_dropdown_by_Visible_Text(String)"
});
formatter.result({
  "duration": 1050263100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "2",
      "offset": 35
    }
  ],
  "location": "DropdownPageWithoutExamples.verify_select_dropdown_by_Index(int)"
});
formatter.result({
  "duration": 257766100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "4",
      "offset": 37
    }
  ],
  "location": "DropdownPageWithoutExamples.verify_select_dropdown_by_Value(String)"
});
formatter.result({
  "duration": 171147600,
  "status": "passed"
});
formatter.match({
  "location": "DropdownPageWithoutExamples.verify_Multiple_dropdown_Selection()"
});
formatter.result({
  "duration": 295593600,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Selenium",
      "offset": 39
    }
  ],
  "location": "DropdownPageWithoutExamples.verify_Select_dropdown_by_Sendkeys(String)"
});
formatter.result({
  "duration": 745425700,
  "status": "passed"
});
formatter.match({
  "location": "DropdownPageWithoutExamples.verify_Deselect_all_dropdowns()"
});
formatter.result({
  "duration": 4789928000,
  "status": "passed"
});
formatter.after({
  "duration": 1801123300,
  "status": "passed"
});
});
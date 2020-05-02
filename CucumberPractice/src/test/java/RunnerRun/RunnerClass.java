package RunnerRun;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\java\\Features" ,
					glue = {"stepDefinitions"} ,
					monochrome = true ,
					strict = true ,
					tags = { "not @smokeTests" }, 
					dryRun = false					
					)
public class RunnerClass {

}


// tags = {"@e2eTests , @RegressionTest"}  ORed Tags - separated By Comma within double inverted commas
//tags = {"@e2eTests" , "@RegressionTest"}  ANDed Tags - each tag separated By Comma within  double inverted commas
//tags = {"not @e2eTests" , "not @RegressionTest"} ignoring tagged Test cases  like @ignore in TestNG / like enabled = false 

//When we define multiple  tags in runner class in below form ,it will be defined with the use of logical operator:
//1. tags = {“@tag”, “@tag1”} : means AND condition. –It says that scenarios matching both these tag needs to be executed.
//2. tags = {“@tag1, @tag2”} : means OR condition. — It says that scenarios matching any of this tag needs to be executed.
//Why do we require Tagging in Cucumber and advantages of tags:
//By using tags,  we can easily organize our feature and scenarios.
//Any string literal may be used as a tag in any scenario or entire feature.
//Feature can have multiple tags associated with it.
//Be aware that tags are heritable within Feature files.
//Different reports can be generated as per the requirements and the tags will be provided correspondingly.
package DataDrivenRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class) 
@CucumberOptions(features="src\\test\\java\\DataDrivenFeatures" , 
glue = {"DataDrivenStepsDef"} ,
format = {"pretty" , "html:Results" , "json:Results/jsonReport.json" ,"junit:Results/xmlReport.xml"} ,
monochrome = true ,
strict = true ,
tags = {"@DataDrivenScenarioWithoutExamples"} ,
dryRun = false)

public class RunDataDriven {

}


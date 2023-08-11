package com.automation.runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/features/salesforceFeature.feature","src/test/resources/features/contactsPage.feature","src/test/resources/features/userProfilePage.feature"},
		glue= {"com.automation.steps"},
		monochrome = true,
		dryRun = false
		
		
		)


public class SalesforceRunner extends AbstractTestNGCucumberTests{

}



package com.matschie.service.now.test.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = {"src/test/java/com/matschie/service/now/features/IncidentService.feature:6"},
		          glue = {"com.matschie.service.now.steps"},
		          dryRun = false,
		          plugin = {
		        		    "pretty",
		        		    "html:reports-html/cucumber/report.html",
		        		    "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"
		        		   },
		          publish = true
		         )
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
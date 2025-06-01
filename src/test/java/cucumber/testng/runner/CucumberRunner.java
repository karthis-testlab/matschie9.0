package cucumber.testng.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		          features = "src/test/java/cucmber/features/InicidentService.feature:8",
		          glue = "cucumber.steps.definition",
		          dryRun = false,
		          plugin = {
		        		  "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"
		          }
		         )
public class CucumberRunner extends AbstractTestNGCucumberTests {

}
package com.matschie.service.now.steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;

import static com.matschie.general.utils.FileHandlers.*;

import com.matschie.jira.services.IssueAttachementService;
import com.matschie.jira.services.IssuesService;

public class CucumberHooks {
	
	private TestContext context;
	
	public CucumberHooks(TestContext context) {
		this.context = context;
	}
	
	@Before
	public void runningBeforeScenario() {
		System.out.println("Running Before Each Scenario");
	}
	
	@After
	public void runningAfterScenario(Scenario scenario) {		
		if (scenario.isFailed()) {
			
			Response response = new IssuesService().createBug("FAILED - "+scenario.getName());
			
			scenario.log("Create Bug in the SB Borad. The issue reference link: "+
			             response.jsonPath().getString("self"));			
			
			createNewTxtFile(scenario.getLine()+"-response", context.getContext("response"));
			createNewTxtFile(scenario.getLine()+"-error", context.getContext("error"));
			appendTxtFile(scenario.getLine()+"-error", context.getContext("trace"));
			
			new IssueAttachementService()
			    .testEvidenceAttachment("./test-evidences/"+scenario.getLine()+"-response"+".txt", 
			    		                response.jsonPath().getString("id"));
			
			new IssueAttachementService()
		    .testEvidenceAttachment("./test-evidences/"+scenario.getLine()+"-error"+".txt", 
		    		                response.jsonPath().getString("id"));
		
		}
	}
	
	@BeforeStep
	public void runningBeforeStep() {
		System.out.println("Running Before Each Scenario step");
	}
	
	@AfterStep
	public void runningAfterStep() {
		System.out.println("Running After Each Scenario step");
	}

}
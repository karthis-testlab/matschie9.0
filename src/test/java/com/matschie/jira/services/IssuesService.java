package com.matschie.jira.services;

import static io.restassured.RestAssured.*;

import com.matschie.jira.pojos.Fields;
import com.matschie.jira.pojos.Issuetype;
import com.matschie.jira.pojos.Project;
import com.matschie.jira.pojos.Root;

import static com.matschie.general.utils.PropertiesHandlers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class IssuesService {	
	
	public IssuesService() {
		baseURI = config("jira.base.uri");
		basePath = config("jira.base.path");				        
	}
	
	public Response createBug(String summary) {
		
		Root root = new Root();
		Fields fields = new Fields();
		Issuetype issuetype = new Issuetype();
		Project project = new Project();
		
		project.setKey("SB");
		issuetype.setName("Bug");
		
		fields.setProject(project);
		fields.setIssuetype(issuetype);
		fields.setSummary(summary);
		
		root.setFields(fields);
		
		return given()
				.auth()
				.preemptive()
				.basic(config("jira.username"), secret("jira.api.token"))
          		.contentType(ContentType.JSON)
		        .log().all()
		        .when()
		        .body(root)
		        .post("/issue");
	}

}
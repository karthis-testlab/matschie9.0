package com.matschie.jira.services;

import static com.matschie.general.utils.PropertiesHandlers.config;
import static com.matschie.general.utils.PropertiesHandlers.secret;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.http.ContentType;

public class IssueAttachementService {
	
	public IssueAttachementService() {
		baseURI = config("jira.base.uri");
		basePath = config("jira.base.path");
	}    
    
    public void testEvidenceAttachment(String filePath, String issueIdOrKey) {
    	given()
		.auth()
		.preemptive()
		.basic(config("jira.username"), secret("jira.api.token"))
		.header("X-Atlassian-Token", "no-check")
		.pathParam("issueIdOrKey", issueIdOrKey)
		.contentType(ContentType.MULTIPART)
		.when()
		.multiPart(new File(filePath))
		.post("/issue/{issueIdOrKey}/attachments")
		.then()
		.assertThat()
		.statusCode(200);
    }    

}
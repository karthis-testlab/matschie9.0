package com.matschie.service.now.services;

import com.matschie.api.design.ResponseAPI;
import com.matschie.api.rest.assured.lib.RequestSpecBuilder;
import com.matschie.api.rest.assured.lib.RestAssuredBaseImpl;

import io.restassured.http.ContentType;

import static com.matschie.general.utils.PropertiesHandlers.*;

public class ServiceNow {	
	
	protected ResponseAPI response;
	protected RestAssuredBaseImpl restAssured = new RestAssuredBaseImpl();	
	protected RequestSpecBuilder requestBuilder;	
	
	protected RequestSpecBuilder globalRequest() {
		return new RequestSpecBuilder()
				   .setBaseUri(config("service.now.base.uri"))	
				   .setBasePath(config("service.now.base.path"))
				   .setUsername(config("sevice.now.username"))
			       .setPassword(secret("service.now.password"))
				   .setAccept(ContentType.JSON);
	}	
	
}
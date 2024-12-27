package com.matschie.service.now.services;

import com.matschie.api.rest.assured.lib.RequestSpecBuilder;

import io.restassured.http.ContentType;

import static com.matschie.general.utils.PropertiesHandlers.*;

public class ServiceNow {
	
	public RequestSpecBuilder globalRequestSpec() {
		return new RequestSpecBuilder()
				   .setBaseUri(config("service.now.base.uri"))
				   .setContentType(ContentType.JSON)
				   .setAccept(ContentType.JSON);
	}
	
}
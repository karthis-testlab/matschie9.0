package com.matschie.service.now.services;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import com.matschie.api.design.ResponseAPI;
import com.matschie.api.rest.assured.lib.RestAssuredBaseImpl;

public class ChangeReqeustService extends ServiceNow {
	
	private static final String TABLE_NAME = "change_request";
	
	private RestAssuredBaseImpl restAssured = new RestAssuredBaseImpl();
	private ResponseAPI response;
	
	public void fetchChangeRequestRecords() {
		response = restAssured.get(globalRequestSpec(), TABLE_NAME);
	}
	
	public void validateSuccessResponse() {
		MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getStatusMessage(), Matchers.equalToIgnoringCase("OK"));
		MatcherAssert.assertThat(response.getContentType(), Matchers.equalTo("application/json"));
	}

}
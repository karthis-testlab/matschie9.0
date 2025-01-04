package com.matschie.service.now.services;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

public class ChangeReqeustService extends ServiceNow {	
	
	private static final String TABLE_NAME = "change_request";
	
	public ChangeReqeustService() {
		requestBuilder = globalRequest();		
	}
	
	public void fetchChangeRequestRecords() {
		response = restAssured.get(requestBuilder.build(), TABLE_NAME);
	}
	
	public void fetchChangeRequestRecord(String sysId) {
		response = restAssured.get(requestBuilder.build(), TABLE_NAME+"/"+sysId);
	}
	
	public void validateSuccessResponse() {
		MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getStatusMessage(), Matchers.equalToIgnoringCase("OK"));
		MatcherAssert.assertThat(response.getContentType(), Matchers.equalTo("application/json"));
	}

}
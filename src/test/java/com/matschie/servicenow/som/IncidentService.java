package com.matschie.servicenow.som;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

public class IncidentService extends ServiceNowBase {
	
	public void createIncident() {
		response = base.post(preReq().build(), "/incident");
	}
	
	public void createIncident(String key, String value) {		
		response = base.post(preReq()
				             .setHeaderKey(key)
				             .setHeaderValue(value)
				             .build(), "/incident");
	}
	
	public void retrieveHardwareCategoryRecords() {
		response = base.get(preReq()
				.setQueryParamKey("sysparm_query")
				.setQueryParamValue("category=hardware")
				.build(),
				"/incident");
	}
	
	public void validateRecordCreateSuccefully() {
		MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(201));
		MatcherAssert.assertThat(response.getStatusMessage(), Matchers.equalTo("Created"));
		MatcherAssert.assertThat(response.getContentType(), Matchers.equalTo("application/json"));
	}
	
	public void validateRecordCreateSuccefullyInXmlFormat() {
		MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(201));
		MatcherAssert.assertThat(response.getStatusMessage(), Matchers.equalTo("Created"));
		MatcherAssert.assertThat(response.getContentType(), Matchers.equalTo("application/xml"));
	}
	
	public void validateSuccessResponse() {
		MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getStatusMessage(), Matchers.equalTo("OK"));
		MatcherAssert.assertThat(response.getContentType(), Matchers.equalTo("application/json"));
	}

}
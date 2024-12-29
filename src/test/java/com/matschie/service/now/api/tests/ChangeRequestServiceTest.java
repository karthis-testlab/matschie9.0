package com.matschie.service.now.api.tests;

import org.testng.annotations.Test;

import com.matschie.service.now.services.ChangeReqeustService;

public class ChangeRequestServiceTest {
	
	ChangeReqeustService changeRequest = new ChangeReqeustService();
	
	@Test
	public void userShouldToFetchAllChangeRequestRecords() {
		changeRequest.fetchChangeRequestRecords();
		changeRequest.validateSuccessResponse();
	}

}

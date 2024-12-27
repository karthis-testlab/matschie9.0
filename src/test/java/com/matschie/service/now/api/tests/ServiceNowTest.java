package com.matschie.service.now.api.tests;

import org.testng.annotations.Test;

import com.matschie.service.now.pojos.IncidentRequestPayload;
import com.matschie.service.now.services.IncidentService;

public class ServiceNowTest {
	
	IncidentService incident = new IncidentService();
	
	@Test
	public void userShouldAbleToFetchAllIncidentRecords() {		
		incident.fetchIncidentRecords();
		incident.validateSuccessResponse();		
	}
	
	@Test
	public void userShouldAbleToFetchOnlyHardwareCategoryIncidentRecords() {
		incident.fetchOnlyHardwareCategoryIncidentRecords();
		incident.validateSuccessResponse();
		incident.validateCategories("Hardware");		
	}
	
	@Test
	public void userShouldAbleToFetchSingleIncidentRecord() {
		incident.fetchIncidentRecord("46f1784ba9fe19810018aa27fbb23482");
		incident.validateSuccessResponse();
		incident.validateSysId("46f1784ba9fe19810018aa27fbb23482");		
	}
	
	@Test
	public void userShouldAbleToCreateIncidentRecord() {		
		IncidentRequestPayload payload = new IncidentRequestPayload();
		payload.setDescription("Description");
		payload.setShort_description("Short Description");
		payload.setActive(true);
		payload.setState(1);
		payload.setUrgency(1);
		incident.createIncidentRecord(payload);
	}

}
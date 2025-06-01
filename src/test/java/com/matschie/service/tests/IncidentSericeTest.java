package com.matschie.service.tests;

import org.testng.annotations.Test;

import com.matschie.servicenow.som.IncidentService;

public class IncidentSericeTest {
	
	public IncidentService incident = new IncidentService();
	
	@Test
	public void validateRecordCreation() {
		incident.createIncident();
		incident.validateRecordCreateSuccefully();
	}
	
	@Test
	public void validateRecordsForHardwareCategory() {
		incident.retrieveHardwareCategoryRecords();
		incident.validateSuccessResponse();
	}

}
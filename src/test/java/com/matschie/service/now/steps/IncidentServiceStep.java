package com.matschie.service.now.steps;

import com.matschie.service.now.pojos.IncidentRequestPayload;
import com.matschie.service.now.services.IncidentService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IncidentServiceStep {
	
	private IncidentService incident;
	private IncidentRequestPayload requestPaylod;
	
	@Given("user should create the relevant object for the incident call")
	public void user_should_create_the_relevant_object_for_the_incident_call() {
		incident = new IncidentService();
	}

	@When("user should call get method of the incident table to fetch records")
	public void user_should_call_get_method_of_the_incident_table_to_fetch_records() {
		incident.fetchIncidentRecords();
	}
	
	@When("user should call get method of the incident table to exract given sysid {string} record")
	public void user_should_call_get_method_of_the_incident_table_to_exract_given_sysid_record(String sys_id) {
	    incident.fetchIncidentRecord(sys_id);
	}

	@Then("user should see the success message and response body")
	public void user_should_see_the_success_message_and_response_body() {
		incident.validateSuccessResponse();
	}
	
	@When("/^user create the new record with following (.*), (.*) values as input$/")
	public void user_create_the_new_record_with_following_restapidec2024_frist_record_description_values_as_input(String arg1, String arg2) {
	    requestPaylod = new IncidentRequestPayload();
	    requestPaylod.setShort_description(arg1);
	    requestPaylod.setDescription(arg2);
	}
	
	@When("user hit post record to create value based on the above input")
	public void user_hit_post_record_to_create_value_based_on_the_above_input() {
	    incident.createIncidentRecord(requestPaylod);
	}
	
	@Then("user should see the success code, message and content type")
	public void user_should_see_the_success_code_message_and_content_type() {
	    incident.validateSuccessResponse();
	}
	
	@Then("user should able to see the give sysid {string} value in the response body")
	public void user_should_able_to_see_the_give_sysid_value_in_the_response_body(String sys_id) {
	    incident.validateSysId(sys_id);
	}
	
	@Then("user should able create a record successfullty")
	public void user_should_able_create_a_record_successfullty() {
	    incident.validateCreationResponse();
	}
	
	@Then("user should see the error message and response relevant to not found record")
	public void user_should_see_the_error_message_and_response_relevant_to_not_found_record() {
	    incident.validateNotFoundResponse();
	}

}
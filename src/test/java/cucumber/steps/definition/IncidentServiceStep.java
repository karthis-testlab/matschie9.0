package cucumber.steps.definition;

import com.matschie.servicenow.som.IncidentService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IncidentServiceStep {
	
	private IncidentService incidentService;

	@Given("create object for the incident service model class")
	public void create_object_for_the_incident_service_model_class() {
		incidentService = new IncidentService();
	}
	
	@Given("Add the accept header with key name as {string} and value as {string}")
	public void add_the_accept_header_with_key_name_as_and_value_as(String key, String value) {
	    incidentService.createIncident(key, value);
	}

	@When("user hits the post method to create the new record")
	public void user_hits_the_post_method_to_create_the_new_record() {
		incidentService.createIncident();
	}

	@Then("user should able to create new record successfully in the service now table")
	public void user_should_able_to_create_new_record_successfully_in_the_service_now_table() {
		incidentService.validateRecordCreateSuccefully();
	}
	
	@Then("validate the record is created succesfully and response should be in the XML")
	public void validate_the_record_is_created_succesfully_and_response_should_be_in_the_xml() {
	    incidentService.validateRecordCreateSuccefullyInXmlFormat();
	}

}
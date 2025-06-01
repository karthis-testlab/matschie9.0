Feature: Validate the business logic code for the incident service

  Scenario: User should able to create new record without request body
    Given create object for the incident service model class
    When user hits the post method to create the new record
    Then user should able to create new record successfully in the service now table

  Scenario: User should able to create new record without request body and response in xml format
    Given create object for the incident service model class
    And Add the accept header with key name as "Accept" and value as "application/xml"
    When user hits the post method to create the new record
    Then validate the record is created succesfully and response should be in the XML

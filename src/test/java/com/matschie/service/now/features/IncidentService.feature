Feature: Validate Incident Table in the Service Now API

  Background: 
    Given user should create the relevant object for the incident call

  Scenario: User should able to fetch all records in the incident table
    When user should call get method of the incident table to fetch records
    Then user should see the success message and response body

  Scenario: User should able to fetch single record based on the sys_id of the incident table
    When user should call get method of the incident table to exract given sysid "e8e875b0c0a80164009dc852b4d677d5" record
    Then user should see the success code, message and content type
    And user should able to see the give sysid "e8e875b0c0a80164009dc852b4d677d5" value in the response body

  Scenario Outline: User should able to create one new record in the incident table
    When user create the new record with following <short_descrition>, <description> values as input
    And user hit post record to create value based on the above input
    Then user should able create a record successfullty

    Examples: 
      | short_descrition | description               |
      | RESTAPIDEC2024   | Frist Record Description  |
      | RESTAPIDEC2024   | second Record Description |

  Scenario: User should see the error message when trying to fetch non exsitence record in the incident table
    When user should call get method of the incident table to exract given sysid "e8e875b0c0a80164009dc852b4d677d52323" record
    But user should see the error message and response relevant to not found record
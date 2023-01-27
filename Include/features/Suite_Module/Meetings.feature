Feature: Meetings functionalities

  Scenario: Verify the User is able to create, delete meetings
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Meetings page
    When Create new meeting
    And Go to Meetings page
    And Duplicate meeting
    And Go to Meetings page
    Then Delete meeting
    And Create recurring meeting
    And Go to Meetings page
    And Delete meeting

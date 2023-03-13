Feature: Teams functionalities

  Scenario: Verify the User is able to create, delete Team
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Teams page
    When Create Team
    And Go to Teams page
    Then Delete Team
Feature: Shift functionalities

  Scenario: Verify the Shift Creation and Deletion functionalities
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Shift page
    When Create shift
    And Go to Shift page
    And Delete shift
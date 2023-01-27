Feature: Administrator Settings functionalities

  Scenario: Update data and validate in Admin settings
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Admin settings page
    When Update the Admin setting based on excel data
    Then Validate the changes
    And Update admin settings with default values
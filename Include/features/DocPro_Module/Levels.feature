Feature: Levels functionalities

  Scenario: Verify the user is able to Create, Edit and Delete levels
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Levels page
    When Create level
    And Modify level data
    And Delete modified level
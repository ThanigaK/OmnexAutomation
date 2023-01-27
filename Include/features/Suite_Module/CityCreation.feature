Feature: City Creation/Deletion functionalities

  Scenario: Verify the City Creation/Deletion functionalities
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to City page
    When Create new City
    And Go to City page
    And Validate city creation
    Then Delete City

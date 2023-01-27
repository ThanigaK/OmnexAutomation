Feature: Country Creation/Deletion functionalities

  Scenario: Verify the Country Creation/Deletion functionalities
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Country page
    When Create new Country
    And Go to Country page
    And Validate Country creation
    Then Delete Country

  Scenario: Create Country, State, and City functionalities
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Country page
    When Create new Country
    And Go to State page
    When Create new State
    And Go to City page
    When Create new City
    And Go to City page
    And Validate city creation
    And Delete City
    And Go to State page
    And Validate state creation
    And Delete State
    And Go to Country page
    And Validate Country creation
    And Delete Country

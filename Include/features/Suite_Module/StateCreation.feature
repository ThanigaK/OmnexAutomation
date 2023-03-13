Feature: State Creation/Deletion functionalities

  Scenario: Verify the State Creation/Deletion functionalities
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to State page
    When Create new State
    And Go to State page
    And Validate state creation
    Then Delete State

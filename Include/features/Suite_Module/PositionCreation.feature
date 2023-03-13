Feature: Position Creation/Deletion functionalities

  Scenario: Verify the Position Creation/Deletion functionalities
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Positions page
    When Create new Position
    And Go to Positions page
    And Validate Position creation
    Then Delete Position
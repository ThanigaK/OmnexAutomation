Feature: Entity and Entity owner functionalities

  Scenario: Create entity, assign owner and delete entity
  
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Entity page
    When Create new Entity
    And Go to Entity Owner page
    Then Assign entity owner
    And Go to Entity page
    And Delete Entity
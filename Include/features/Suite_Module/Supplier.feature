Feature: Supplier functionalities

  Scenario: Verify the User is able to create,delete Supplier
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Supplier page
    When Create Supplier
    And Go to Supplier page
    And Delete Supplier

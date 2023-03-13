Feature: Customer functionalities

  Scenario: Verify the User is able to create,delete Customer
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Customers page
    When Create Customer and add address
    And Go to Customers page
    And Delete Customer

Feature: Document Route functionalities

  Scenario: Verify the user is able to Create, Edit and Delete Route
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Document Route page
    When Create new Route
    And Go to Document Route page
    And Validate Document Route creation
    And Edit Route details
    And Go to Document Route page
    And Validate Document Route details
    Then Delete Route

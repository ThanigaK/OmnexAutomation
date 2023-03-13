Feature: Manufacturer functionalities

  Scenario: Verify the User is able to create,delete manufacturer
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Manufacturer page
    When Create manufacturer
    And Go to Manufacturer page
    Then Delete manufacturer

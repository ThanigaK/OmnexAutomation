Feature: Manufacturer functionalities

  Scenario: Verify the User is able to create,delete Vendor/Registrar
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Vendor page
    When Create Vendor
    And Go to Vendor page
    Then Delete Vendor

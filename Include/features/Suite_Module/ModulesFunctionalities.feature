Feature: Modules functionalities

  Scenario: Verify the User is able to Add/Remove Users for module
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Modules page
    And Add user for Document Pro Module
    And Search and validate user by empcode in users_page
    And Remove user from Document Pro Module

Feature: Login page functionalities

  Scenario: Verify the Forgot password functionality
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to users page
    When Create user
    And Logout to the application
    And Change password in Forgot password
    Then Validate the forgot password
    And Logout to the application
    And Login to the application
    And Go to users page
    And Search and validate user by empcode in users_page
    And Delete user

  Scenario: Verify the Remember me functionality
    Given Launch browser
    And Launch application
    When Login to the application with remember me option
    And Logout to the application
    Then Validate remember me option

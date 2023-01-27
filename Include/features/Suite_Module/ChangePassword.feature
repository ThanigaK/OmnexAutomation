Feature: User change password functionality

  Scenario: Verify whether the user is able to change password from preferences
    Given Launch browser
    And Launch application
    And Login to the application
    When Go to users page
    And Create user
    And Logout to the application
    And Login to the application with User name and Password
    And Go to Preferences page
    And Change Password in preferences page
    And Logout to the application
    And Login to the application
    And Go to users page
    And Search and validate user by empcode in users_page
    And Delete user
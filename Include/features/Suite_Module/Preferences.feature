Feature: User Preferences functionalities

  Scenario: Verify the User preferences functionalities
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to users page
    And Create user
    And Logout to the application
    And Login to the application with User name and Password
    And Go to Preferences page
    When Update the preferences settings based on excel data
    Then Validate timezone under profile icon
   # And Go to Labels page
   # And Validate Page size
    And Logout to the application
    And Login to the application
    And Go to users page
    And Search and validate user by empcode in users_page
    And Delete user
 # Scenario: Verify the language change in preferences
   # Given Launch browser
   # And Launch application
   # And Login to the application
   # And Go to users page
   # And Create user
   # And Logout to the application
   # And Login to the application with User name and Password
   # And Go to Preferences page
   # When Update the preferences settings based on excel data
   # Then Validate language
   # And Logout to the application
   # And Login to the application
   # And Go to users page
   # And Search and validate user by empcode in users_page
   # And Delete user

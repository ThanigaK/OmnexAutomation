Feature: User creation functionalities

  Scenario: Verify whether the user is able to create, search and delete user
    Given Launch browser
    And Launch application
    And Login to the application
    When Go to users page
    And Create user
    And Go to users page
    And Search and validate user by empcode in users_page
    And Delete user
    #And Go to Restore user page
    #And Search and validate user by empcode in restore_page
    #And Restore user in restore_page
    #And Go to users page
    #And Search and validate user by empcode in users_page
    #And Delete user
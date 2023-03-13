Feature: Unlock user functionalities

  Scenario: Verify the unlock user functionality
    Given New browser 1
    And Login to the application with Username and Password
    When New browser 2
    And Login to the application
    And Go to Unlock user page
    And Unlock user
    Then Validate user unlocked
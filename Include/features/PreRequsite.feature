Feature: Pre requsite scenario to execute all scenarios

  Scenario: Create donotdelete_one user and assign to module
    Given Launch browser
    And Launch application
    And Login to the application
    When Go to users page
    And Create user
    And Go to Modules page
    And Add user for Document Pro Module
    Then Go to Groups page
    And Add user in the Group under Documents
    
    Scenario: Create donotdelete_two user and assign to module
    Given Launch browser
    And Launch application
    And Login to the application
    When Go to users page
    And Create user
    And Go to Modules page
    And Add user for Document Pro Module
    Then Go to Groups page
    And Add user in the Group under Documents
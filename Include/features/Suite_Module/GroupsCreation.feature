Feature: Groups functionalities

  Scenario: Verify the User is able to create,Modify,delete groups and add/remove users
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Groups page
    When Create new Group under Documents
    And Add user in the Group under Documents
    Then Remove user in the Group under Documents
    And Go to Groups page
    And Modify the group name under Documents
    And Delete the Group under Documents
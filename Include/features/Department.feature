Feature: Validate the Department Page

  Scenario: Add Edit and Delete Department
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Create Department page
    When Add Department and Validate
    Then Edit Department and Validate
    And Delete Department
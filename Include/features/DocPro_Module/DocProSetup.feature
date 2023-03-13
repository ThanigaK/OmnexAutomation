Feature: DocPro Setup functionalities
@Tag1
  Scenario: Update the Document number option and Validate
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to DocPro setup page
    And Go to level in Folder management
    When Update the level data in Folder Management
    And Go to New Doc Request page
    And Create new doc with details
    Then Validate the document number based on document number option
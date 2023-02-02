Feature: DocPro Setup functionalities

  #Scenario: New document creation with auto approval
    #Given Launch browser
    #And Launch application
    #And Login to the application
    #And Go to Levels page
    #And Create level
    #And Go to DocPro setup page
    #And Go to level in Folder management
    #And Make the created level in use
    #And Go to DocPro setup page
    #And Go to level in Folder management
    #When Update the level data in Folder Management
    #And Go to New Doc Request page
    #And Create new doc with details
    #And Go to Documents Page
    #Then Validate uploaded document in Documents TOC
    #And Go to DocPro setup page
    #And Go to level in Document management
    #And Move all available files to bin level
    #And Go to Levels page
    #And Delete level

  Scenario: New document creation with one approver
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Document Route page
    And Create new Route
    And Go to Levels page
    And Create level
    And Go to DocPro setup page
    And Go to level in Folder management
    And Make the created level in use
    And Go to DocPro setup page
    And Go to level in Folder management
    When Update the level data in Folder Management
    And Go to New Doc Request page
    And Create new doc with details
    And Logout to the application
    And Login to the application with Username and Password
    Then Go to Actions page
    And Approve request in Request Needing Approval
    And Go to Documents Page
    Then Validate uploaded document in Documents TOC
    And Go to DocPro setup page
    And Go to level in Document management
    And Move all available files to bin level
    And Go to Levels page
    And Delete level
    And Go to Document Route page
    And Delete Route

  #Scenario: New document creation and reject and resumbit the approval
    #Given Launch browser
    #And Launch application
    #And Login to the application
    #And Go to Document Route page
    #And Create new Route
    #And Go to Levels page
    #And Create level
    #And Go to DocPro setup page
    #And Go to level in Folder management
    #And Make the created level in use
    #And Go to DocPro setup page
    #And Go to level in Folder management
    #When Update the level data in Folder Management
    #And Go to New Doc Request page
    #And Create new doc with details
    #And Logout to the application
    #And Login to the application with Username and Password
    #Then Go to Actions page
    #And Reject request in Request Needing Approval
    #And Logout to the application
    #And Login to the application
    #Then Go to Actions page
    #And Resubmit the Rejected request in Resubmit Rejected Request
    #And Logout to the application
    #And Login to the application with Username and Password
    #Then Go to Actions page
    #And Approve request in Request Needing Approval
    #And Go to Documents Page
    #Then Validate uploaded document in Documents TOC
    #And Go to DocPro setup page
    #And Go to level in Document management
    #And Move all available files to bin level
    #And Go to Levels page
    #And Delete level
    #And Go to Document Route page
    #And Delete Route
#
  Scenario: New document creation with Assign admin
    Given Launch browser
    And Launch application
    And Login to the application
    And Go to Levels page
    And Create level
    And Go to DocPro setup page
    And Go to level in Folder management
    And Make the created level in use
    And Go to DocPro setup page
    And Go to level in Folder management
    When Update the level data in Folder Management
    And Go to New Doc Request page
    And Create new doc with details
    Then Go to Administrator Actions page
    And Assign route for created request
    And Go to Documents Page
    Then Validate uploaded document in Documents TOC
    And Go to DocPro setup page
    And Go to level in Document management
    And Move all available files to bin level
    And Go to Levels page
    And Delete level
#
  #Scenario: New document creation with Level owner
    #Given Launch browser
    #And Launch application
    #And Login to the application
    #And Go to Levels page
    #And Create level
    #And Go to DocPro setup page
    #And Go to level in Folder management
    #And Make the created level in use
    #And Go to DocPro setup page
    #And Go to level in Folder management
    #When Update the level data in Folder Management
    #And Go to New Doc Request page
    #And Create new doc with details
    #And Logout to the application
    #And Login to the application with Username and Password
    #Then Go to Actions page
    #And Approve request in Request Needing Approval
    #And Go to Documents Page
    #Then Validate uploaded document in Documents TOC
    #And Go to DocPro setup page
    #And Go to level in Document management
    #And Move all available files to bin level
    #And Go to Levels page
    #And Delete level
#
  #Scenario: New document creation without document
    #Given Launch browser
    #And Launch application
    #And Login to the application
    #And Go to Levels page
    #And Create level
    #And Go to DocPro setup page
    #And Go to level in Folder management
    #And Make the created level in use
    #And Go to DocPro setup page
    #And Go to level in Folder management
    #When Update the level data in Folder Management
    #And Go to New Doc Request page
    #And Create new doc with details
    #Then Go to Administrator Actions page
    #And Assign author for created request
    #And Logout to the application
    #And Login to the application with Username and Password
    #Then Go to Actions page
    #And Attach document in Document Needing Revision
    #And Go to Documents Page
    #Then Validate uploaded document in Documents TOC
    #And Go to DocPro setup page
    #And Go to level in Document management
    #And Move all available files to bin level
    #And Go to Levels page
    #And Delete level
#
  #Scenario: New document creation and Reassign approver
    #Given Launch browser
    #And Launch application
    #And Login to the application
    #And Go to Document Route page
    #And Create new Route
    #And Go to Levels page
    #And Create level
    #And Go to DocPro setup page
    #And Go to level in Folder management
    #And Make the created level in use
    #And Go to DocPro setup page
    #And Go to level in Folder management
    #When Update the level data in Folder Management
    #And Go to New Doc Request page
    #And Create new doc with details
    #And Logout to the application
    #And Login to the application with Username and Password
    #Then Go to Actions page
    #And Assign user in Reassign Approval
    #And Go to Documents Page
    #Then Validate uploaded document in Documents TOC
    #And Go to DocPro setup page
    #And Go to level in Document management
    #And Move all available files to bin level
    #And Go to Levels page
    #And Delete level
    #And Go to Document Route page
    #And Delete Route
#
  #Scenario: Add doc as favourite and validate
    #Given Launch browser
    #And Launch application
    #And Login to the application
    #And Go to Favourites page
    #And Create Favourite folder
    #And Go to Levels page
    #And Create level
    #And Go to DocPro setup page
    #And Go to level in Folder management
    #And Make the created level in use
    #And Go to DocPro setup page
    #And Go to level in Folder management
    #When Update the level data in Folder Management
    #And Go to New Doc Request page
    #And Create new doc with details
    #And Go to Documents Page
    #Then Add created document as favourite document
    #And Go to Favourites page
    #And Validate the document in favourite folder
    #And Delete Favourite folder
    #And Go to DocPro setup page
    #And Go to level in Document management
    #And Move all available files to bin level
    #And Go to Levels page
    #And Delete level

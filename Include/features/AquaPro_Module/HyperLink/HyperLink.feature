Feature: Validate Hyper Link Functionalities

  Scenario: Verify whether the user is able to create hyper link
    Given Launch browser
    And Launch application
    And Login to the application
    When Go to Aqua Pro Main2 application
    And Click on the product in Manufactured Production Items
    And Open Process FMEA document in Process Documents
    
    And Create DocPro HyperLink in main2
    Then Validate created HyperLink in main2
    And Delete HyperLink in main2
    And Validate deleted HyperLink in main2
    
    And Create DocPro Image HyperLink in main2
    Then Validate created HyperLink in main2
    And Delete HyperLink in main2
    And Validate deleted HyperLink in main2
    
    And Create AquaPro HyperLink in main2
    Then Validate created HyperLink in main2
    And Delete HyperLink in main2
    And Validate deleted HyperLink in main2
    
    And Create Non-controlled HyperLink in main2
    Then Validate created HyperLink in main2
    And Delete HyperLink in main2
    And Validate deleted HyperLink in main2 
    
   

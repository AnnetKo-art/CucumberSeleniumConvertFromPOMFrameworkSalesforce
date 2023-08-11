   
      Feature: Contacts Page Feature
      Background: User is Logged In
               
      Given user navigates to the loginpage
      When user is on "LoginPage"
      When user submits username and password as "annet@properties.com","2022dreamdream2022"
      When clicks on Login button
      When user is on "HomePage"
      And expected title of homepage user can see  "Home Page ~ Salesforce - Developer Edition"
      When user clicks on contacts tab link
      When popup window handle
      Then user is on "ContactsPage"
                            
      
      Scenario: Add a view unique name in contacts page
      Given new view hyperlink is displayed on the contacts page     
      When user clicks on create new view hyperlink 
      And puts the data into view unique name as "ZXCV"
      Then clicks save button 
      
      Scenario: Cancel click in create new contacts in contacts page
      
      Given new view hyperlink is displayed on the contacts page     
      When user clicks on create new view hyperlink 
      And puts the data into view unique name as "BNML"
      And click cancel button 
      Then view drop down check unique name as "BNML" should be absent 
      
      
     Scenario: Select Recently Created Option in recently Modified popup
      
      Given popup recently modified is present on the page
      When user chooses recently created option in the popup
      And refreshes the page
      Then  recently created option as "Recently Modified" in the popup should be displayed
      
      
     
      
      
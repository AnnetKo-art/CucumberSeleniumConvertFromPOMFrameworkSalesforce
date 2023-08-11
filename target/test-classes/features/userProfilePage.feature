Feature: Add phone number in user profile and save

  
   Background: User is Logged In
    Given User open salesforce application
    When user on "LoginPage"
    When expected title of loginpage we can see "Login | Salesforce"
    When User enters value into text box username as "annet@properties.com"
    When User enters value into text box password as "2022dreamdream2022"
    When Click on Login button
    When user on "HomePage"
    Then expected title of homepage we can see  "Home Page ~ Salesforce - Developer Edition"
    
Scenario: Add phone number in user profile page and click saveall button
Given expected title of home page is  "Home Page ~ Salesforce - Developer Edition"
When click on drop down menu from user lable
And click my profile link in a dropdown
And click edit profile link 
And handle the frame
And check if a textbook for work phone is displayed
And insert data as "1029384756" into work phone textbox 
Then click saveall button






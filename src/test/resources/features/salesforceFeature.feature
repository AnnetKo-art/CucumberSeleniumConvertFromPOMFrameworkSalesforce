Feature: Login into salesforce application

  Scenario: Login with valid user and valid password
    Given User open salesforce application
    When user on "LoginPage"
    When expected title of loginpage we can see "Login | Salesforce"
    When User enters value into text box username as "annet@properties.com"
    When User enters value into text box password as "2022dreamdream2022"
    When Click on Login button
    When user on "HomePage"
    Then expected title of homepage we can see  "Home Page ~ Salesforce - Developer Edition"
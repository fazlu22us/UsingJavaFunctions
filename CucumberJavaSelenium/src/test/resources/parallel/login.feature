Feature: feature to test login funactionality

  Scenario: Check login is successful with valid credentials
    Given user is on login page
    When user enters username and password
    And clicks on login button
    Then user is nagivated to the home page
    And search for gift card under products
    Then user search for customer emails display validation
    And click logout from the page

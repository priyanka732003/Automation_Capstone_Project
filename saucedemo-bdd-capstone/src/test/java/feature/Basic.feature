Feature: Basic browser test

  Scenario: Open SauceDemo homepage
    Given I open the browser
    When I navigate to the SauceDemo URL
    Then I should see the login page
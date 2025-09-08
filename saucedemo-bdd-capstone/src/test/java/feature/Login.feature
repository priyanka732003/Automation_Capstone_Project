Feature: Login functionality

  Scenario: Valid login with standard user
    Given I am on the SauceDemo login page
    When I enter username "standard_user" and password "secret_sauce"
    And I click the login button
    Then I should be redirected to the inventory page

  Scenario: Invalid login - wrong password
    Given I am on the SauceDemo login page
    When I enter username "standard_user" and password "wrongpass"
    And I click the login button
    Then I should see an error message "Epic sadface: Username and password do not match any user in this service"

  Scenario: Invalid login - wrong username
    Given I am on the SauceDemo login page
    When I enter username "wrong_user" and password "secret_sauce"
    And I click the login button
    Then I should see an error message "Epic sadface: Username and password do not match any user in this service"

  Scenario: Locked out user cannot login
    Given I am on the SauceDemo login page
    When I enter username "locked_out_user" and password "secret_sauce"
    And I click the login button
    Then I should see an error message "Epic sadface: Sorry, this user has been locked out."

  Scenario: Username and password empty
    Given I am on the SauceDemo login page
    When I enter username "" and password ""
    And I click the login button
    Then I should see an error message "Epic sadface: Username is required"
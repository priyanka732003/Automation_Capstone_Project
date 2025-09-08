Feature: Cart basic

  Background:
    Given I am on the SauceDemo login page
    When I enter username "standard_user" and password "secret_sauce"
    And I click the login button
    Then I should be redirected to the inventory page

  Scenario: Add single item to cart
    When I add "Sauce Labs Backpack" to the cart
    Then the cart badge should show "1"
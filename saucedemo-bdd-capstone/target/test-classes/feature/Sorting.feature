Feature: Product sorting

  Scenario: Sort products A to Z
    Given I am logged in as "standard_user" with password "secret_sauce"
    When I sort products by "Name (A to Z)"
    Then products should be displayed in ascending alphabetical order

  Scenario: Sort products Z to A
    Given I am logged in as "standard_user" with password "secret_sauce"
    When I sort products by "Name (Z to A)"
    Then products should be displayed in descending alphabetical order

  Scenario: Sort products low to high price
    Given I am logged in as "standard_user" with password "secret_sauce"
    When I sort products by "Price (low to high)"
    Then products should be displayed in ascending price order

  Scenario: Sort products high to low price
    Given I am logged in as "standard_user" with password "secret_sauce"
    When I sort products by "Price (high to low)"
    Then products should be displayed in descending price order
Feature: Registration

  @TC1
  Scenario: User should be able to access registration page
    Given User is on dashboard
    When User clicks sign up on dashboard
    And User clicks sign up on lists page
    Then User should be presented with overlay for signing up
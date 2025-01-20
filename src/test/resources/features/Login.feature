Feature: Login functionality of the Retail Web Application

  Scenario: User successfully logs in
    Given User is on the login page
    When User enters valid credentials
    Then User should be redirected to the dashboard

  Scenario: User fails to log in with invalid credentials
    Given User is on the login page
    When User enters invalid credentials
    Then User should see an error message

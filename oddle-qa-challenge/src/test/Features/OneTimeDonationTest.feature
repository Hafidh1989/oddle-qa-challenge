Feature: One Time Donation

  Scenario: One time donation without 3D secure verification
    Given I want to do 5 dollar one time donate with out 3D secure verification
    When I Donate
    Then Then I should redirect to payment succeeded page

  Scenario: One time donation with 3D secure verification
    Given I want to do 5 dollar one time donate with 3D secure verification
    When I Donate
    Then I should be prompt with 3D secure verification page
    And I can click complete button to proceed payment
    Then Then I should redirect to payment succeeded page

  Scenario: One time donation with 3D secure verification and failed it
    Given I want to do 5 dollar one time donate with 3D secure verification
    When I Donate
    Then I should be prompt with 3D secure verification page
    And I can click fail button to abort payment
    Then I should get error payment error message
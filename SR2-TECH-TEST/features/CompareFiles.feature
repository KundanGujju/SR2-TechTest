@SR2_Tech_Test
@tag
Feature: Validating the results from CarTacCheck website

  @tag1
  Scenario Outline: I want to Validate the results from CarTaxcheck against a Text file
    Given I navigate to cartaxcheck website
    When I get details of <nthNoOfCar> car reg
    Then I retireve Car details
    And I write car details in txt file
    And I validate the results against car_output file
    Examples:
    |nthNoOfCar|
    |1|
    |2|
    |3|
    |4|
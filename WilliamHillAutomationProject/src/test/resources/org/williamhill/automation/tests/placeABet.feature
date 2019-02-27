@placeBet
Feature: Placing a bet in Euro 2016 international football event

  Background: User should be on WilliamHill home page
    Given User logged into WilliamHill
    And User navigated to WilliamHill Betting Page

  Scenario Outline: Place bet on Euro 2016 international football event
    When User selects "<sports>" from betting home page
    And selects the match "<matchName>" to place bet in "<competitionName>"
    And places a bet "<betAmount>" on "<favouriteTeam>" to win from Betslip
    Then Verify that odds and betAmount and returns offered are displayed in bet reciept
    
    

    Examples:     
      | sports   | matchName           | favouriteTeam | betAmount | competitionName |
      | Football | Slovakia vs England | Slovakia      | 0.03      | Euro 2016       |
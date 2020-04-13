Feature: Race

  Scenario: New Game
    When  I create a new game
    Then  I must have a game to play

  Scenario Outline: Play Game
    Given I am in a race
    When  I roll a <Roll>
    Then  I must be at position <New Position>

    Examples:
      | Roll  | New Position  |
      | 1     | 1             |
      | 2     | 2             |
      | 3     | 3             |
      | 4     | 4             |
      | 5     | 5             |
      | 6     | 6             |

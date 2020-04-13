Feature: Race

  Scenario: New Game
    When  I create a new game
    Then  I must have a game to play

  Scenario Outline: Play Game
    Given I am in a race
    When  I set my speed to <Speed>
    And   I roll a <Roll>
    Then  I must be at position <New Position>
    And   I must now have damage of <New Damage>

    Examples:
      | Position  | Damage  | Speed   | Roll  | New Position  | New Damage  | Result  |
      | 0         | 0       | NORMAL  | 1     | 1             | 0           |         |
      | 0         | 0       | NORMAL  | 2     | 2             | 0           |         |
      | 0         | 0       | NORMAL  | 3     | 1             | 0           |         |
      | 0         | 0       | NORMAL  | 4     | 2             | 0           |         |
      | 0         | 0       | NORMAL  | 5     | 1             | 0           |         |
      | 0         | 0       | NORMAL  | 6     | 2             | 0           |         |
      | 0         | 0       | SUPER   | 1     | 1             | 1           |         |
      | 0         | 0       | SUPER   | 2     | 2             | 1           |         |
      | 0         | 0       | SUPER   | 3     | 3             | 1           |         |
      | 0         | 0       | SUPER   | 4     | 4             | 1           |         |
      | 0         | 0       | SUPER   | 5     | 5             | 1           |         |
      | 0         | 0       | SUPER   | 6     | 6             | 1           |         |

Feature: Race

  Scenario: New Game
    When  I create a new game
    Then  I must have a game to play

  Scenario Outline: Play Game
    Given I am in a race
    And   I am at position <Position>
    And   I have damage of <Damage>
    And   I set my speed to "<Speed>"
    When  I roll a <Roll>
    Then  I must be at position <New Position>
    And   I must now have damage of <New Damage>
    And   result of the race is "<Result>"

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
      | 0         | 1       | SUPER   | 1     | 0             | 2           |         |
      | 0         | 1       | SUPER   | 2     | 1             | 2           |         |
      | 0         | 1       | SUPER   | 3     | 2             | 2           |         |
      | 0         | 1       | SUPER   | 4     | 3             | 2           |         |
      | 0         | 1       | SUPER   | 5     | 4             | 2           |         |
      | 0         | 1       | SUPER   | 6     | 5             | 2           |         |
      | 6         | 2       | NORMAL  | 1     | 6             | 2           |         |
      | 6         | 2       | NORMAL  | 2     | 6             | 2           |         |
      | 6         | 2       | NORMAL  | 3     | 6             | 2           |         |
      | 6         | 2       | NORMAL  | 4     | 6             | 2           |         |
      | 6         | 2       | NORMAL  | 5     | 6             | 2           |         |
      | 6         | 2       | NORMAL  | 6     | 6             | 2           |         |
      | 6         | 6       | SUPER   | 1     | 6             | 7           |         |
      | 6         | 6       | SUPER   | 2     | 6             | 7           |         |
      | 6         | 6       | SUPER   | 3     | 6             | 7           |         |
      | 6         | 6       | SUPER   | 4     | 6             | 7           |         |
      | 6         | 6       | SUPER   | 5     | 6             | 7           |         |
      | 6         | 6       | SUPER   | 6     | 6             | 7           |         |
      | 9         | 0       | NORMAL  | 1     | 10            | 0           | WIN     |
      | 9         | 0       | NORMAL  | 2     | 10            | 0           | WIN     |
      | 9         | 0       | NORMAL  | 3     | 10            | 0           | WIN     |
      | 9         | 0       | NORMAL  | 4     | 10            | 0           | WIN     |
      | 9         | 0       | NORMAL  | 5     | 10            | 0           | WIN     |
      | 9         | 0       | NORMAL  | 6     | 10            | 0           | WIN     |
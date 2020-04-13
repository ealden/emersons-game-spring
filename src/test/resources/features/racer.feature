Feature: Racer

  Scenario Outline: Race
    Given I am in a race
    And   I am at position <Position>
    And   I have damage of <Damage>
    When  I choose "<Speed>" speed
    And   I roll a <Roll>
    Then  I must be at position <New Position>
    And   I must now have damage of <New Damage>
    And   I must see the race result: "<Result>"

    Examples: NORMAL speed is move 1 for odd roll, 2 for even roll

      | Position  | Damage  | Speed   | Roll  | New Position  | New Damage  | Result  |
      | 0         | 0       | NORMAL  | 1     | 1             | 0           | --      |
      | 0         | 0       | NORMAL  | 2     | 2             | 0           | --      |
      | 0         | 0       | NORMAL  | 3     | 1             | 0           | --      |
      | 0         | 0       | NORMAL  | 4     | 2             | 0           | --      |
      | 0         | 0       | NORMAL  | 5     | 1             | 0           | --      |
      | 0         | 0       | NORMAL  | 6     | 2             | 0           | --      |

    Examples: SUPER speed is move at roll but take 1 damage every use

      | Position  | Damage  | Speed   | Roll  | New Position  | New Damage  | Result  |
      | 0         | 0       | SUPER   | 1     | 1             | 1           | --      |
      | 0         | 0       | SUPER   | 2     | 2             | 1           | --      |
      | 0         | 0       | SUPER   | 3     | 3             | 1           | --      |
      | 0         | 0       | SUPER   | 4     | 4             | 1           | --      |
      | 0         | 0       | SUPER   | 5     | 5             | 1           | --      |
      | 0         | 0       | SUPER   | 6     | 6             | 1           | --      |

    Examples: Damage is deducted from roll

      | Position  | Damage  | Speed   | Roll  | New Position  | New Damage  | Result  |
      | 0         | 1       | NORMAL  | 1     | 0             | 1           | --      |
      | 0         | 1       | NORMAL  | 2     | 1             | 1           | --      |
      | 0         | 1       | NORMAL  | 3     | 0             | 1           | --      |
      | 0         | 1       | NORMAL  | 4     | 1             | 1           | --      |
      | 0         | 1       | NORMAL  | 5     | 0             | 1           | --      |
      | 0         | 1       | NORMAL  | 6     | 1             | 1           | --      |
      | 0         | 1       | SUPER   | 1     | 0             | 2           | --      |
      | 0         | 1       | SUPER   | 2     | 1             | 2           | --      |
      | 0         | 1       | SUPER   | 3     | 2             | 2           | --      |
      | 0         | 1       | SUPER   | 4     | 3             | 2           | --      |
      | 0         | 1       | SUPER   | 5     | 4             | 2           | --      |
      | 0         | 1       | SUPER   | 6     | 5             | 2           | --      |

    Examples: If enough damage, will no longer move

      | Position  | Damage  | Speed   | Roll  | New Position  | New Damage  | Result  |
      | 6         | 2       | NORMAL  | 1     | 6             | 2           | --      |
      | 6         | 2       | NORMAL  | 2     | 6             | 2           | --      |
      | 6         | 2       | NORMAL  | 3     | 6             | 2           | --      |
      | 6         | 2       | NORMAL  | 4     | 6             | 2           | --      |
      | 6         | 2       | NORMAL  | 5     | 6             | 2           | --      |
      | 6         | 2       | NORMAL  | 6     | 6             | 2           | --      |
      | 6         | 6       | SUPER   | 1     | 6             | 7           | --      |
      | 6         | 6       | SUPER   | 2     | 6             | 7           | --      |
      | 6         | 6       | SUPER   | 3     | 6             | 7           | --      |
      | 6         | 6       | SUPER   | 4     | 6             | 7           | --      |
      | 6         | 6       | SUPER   | 5     | 6             | 7           | --      |
      | 6         | 6       | SUPER   | 6     | 6             | 7           | --      |

    Examples: We win if we pass 10 positions!

      | Position  | Damage  | Speed   | Roll  | New Position  | New Damage  | Result  |
      | 9         | 0       | NORMAL  | 1     | 10            | 0           | WIN     |
      | 9         | 0       | NORMAL  | 2     | 10            | 0           | WIN     |
      | 9         | 0       | NORMAL  | 3     | 10            | 0           | WIN     |
      | 9         | 0       | NORMAL  | 4     | 10            | 0           | WIN     |
      | 9         | 0       | NORMAL  | 5     | 10            | 0           | WIN     |
      | 9         | 0       | NORMAL  | 6     | 10            | 0           | WIN     |
      | 9         | 0       | SUPER   | 1     | 10            | 1           | WIN     |
      | 9         | 0       | SUPER   | 2     | 10            | 1           | WIN     |
      | 9         | 0       | SUPER   | 3     | 10            | 1           | WIN     |
      | 9         | 0       | SUPER   | 4     | 10            | 1           | WIN     |
      | 9         | 0       | SUPER   | 5     | 10            | 1           | WIN     |
      | 9         | 0       | SUPER   | 6     | 10            | 1           | WIN     |
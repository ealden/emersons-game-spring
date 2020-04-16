Feature: Racer

  Scenario Outline: Race
    Given I am in a race
    And   I am at position <Position>
    And   I have damage of <Damage>
    And   I see the finish line at position 15
    When  it is my turn to roll
    And   I choose "<Speed>" speed
    And   I roll a <Roll>
    Then  I must now be at position <New Position>
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

    Examples: SUPER speed is move based on roll but take 1 damage every time we roll

      | Position  | Damage  | Speed   | Roll  | New Position  | New Damage  | Result  |
      | 0         | 0       | SUPER   | 1     | 1             | 1           | --      |
      | 0         | 0       | SUPER   | 2     | 2             | 1           | --      |
      | 0         | 0       | SUPER   | 3     | 3             | 1           | --      |
      | 0         | 0       | SUPER   | 4     | 4             | 1           | --      |
      | 0         | 0       | SUPER   | 5     | 5             | 1           | --      |
      | 0         | 0       | SUPER   | 6     | 6             | 1           | --      |

    Examples: Damage is deducted from each roll

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

    Examples: We will no longer be able to move given enough damage

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

    Examples: We win if we reach the finish line!

      | Position  | Damage  | Speed   | Roll  | New Position  | New Damage  | Result  |
      | 14        | 0       | NORMAL  | 1     | 15            | 0           | WIN     |
      | 14        | 0       | NORMAL  | 2     | 15            | 0           | WIN     |
      | 14        | 0       | NORMAL  | 3     | 15            | 0           | WIN     |
      | 14        | 0       | NORMAL  | 4     | 15            | 0           | WIN     |
      | 14        | 0       | NORMAL  | 5     | 15            | 0           | WIN     |
      | 14        | 0       | NORMAL  | 6     | 15            | 0           | WIN     |
      | 14        | 0       | SUPER   | 1     | 15            | 1           | WIN     |
      | 14        | 0       | SUPER   | 2     | 15            | 1           | WIN     |
      | 14        | 0       | SUPER   | 3     | 15            | 1           | WIN     |
      | 14        | 0       | SUPER   | 4     | 15            | 1           | WIN     |
      | 14        | 0       | SUPER   | 5     | 15            | 1           | WIN     |
      | 14        | 0       | SUPER   | 6     | 15            | 1           | WIN     |

  Scenario: New Race
    Given I am in a race
    And   I am at position 2
    And   I have damage of 1
    And   I see the finish line at position 15
    When  I choose to start over in a new race
    And   I join in as a new racer
    Then  I must now be at position 0
    And   I must now have damage of 0
    And   I must see the race result: "--"
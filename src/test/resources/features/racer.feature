Feature: Racer

  Scenario Outline: Race
    Given I am in a race
    And   I am at position <Position>
    And   I have damage of <Damage>
    And   I see the finish line at position 10
    When  I choose "<Speed>" speed
    And   I roll a <Roll>
    Then  I must now be at position <New Position>
    And   I must now have damage of <New Damage>
    And   I must see the race result: <Result>

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

    Examples: We will crash given max damage

      | Position  | Damage  | Speed   | Roll  | New Position  | New Damage  | Result  |
      | 5         | 5       | SUPER   | 1     | 5             | 6           | CRASHED |
      | 5         | 5       | SUPER   | 2     | 5             | 6           | CRASHED |
      | 5         | 5       | SUPER   | 3     | 5             | 6           | CRASHED |
      | 5         | 5       | SUPER   | 4     | 5             | 6           | CRASHED |
      | 5         | 5       | SUPER   | 5     | 5             | 6           | CRASHED |
      | 5         | 5       | SUPER   | 6     | 6             | 6           | CRASHED |

    Examples: We win if we reach the finish line!

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

  Scenario: New Race
    Given I am in a race
    And   I am at position 2
    And   I have damage of 1
    And   I see the finish line at position 10
    When  I choose to start over in a new race
    Then  I must now be at position 0
    And   I must now have damage of 0
    And   I must see the race result: --

  Scenario Outline: Log Roll Entry
    Given I am in a race
    And   I am at position <Position>
    And   I have damage of <Damage>
    And   I see the finish line at position 10
    When  I choose "<Speed>" speed
    And   I roll a <Roll>
    Then  I must now have a log entry with the following:
          * Position: <Position>
          * Damage: <Damage>
          * Speed: "<Speed>"
          * Roll: <Roll>
          * Move: <Move>
          * New Position: <New Position>
          * New Damage: <New Damage>
          * Crashed: "<Crashed>"
          * Win: "<Win>"

    Examples:

      | Position  | Damage  | Speed   | Roll  | Move  | New Position  | New Damage  | Crashed | Win |
      | 0         | 0       | NORMAL  | 1     | 1     | 1             | 0           | NO      | NO  |
      | 0         | 0       | NORMAL  | 2     | 2     | 2             | 0           | NO      | NO  |
      | 0         | 0       | NORMAL  | 3     | 1     | 1             | 0           | NO      | NO  |
      | 0         | 0       | NORMAL  | 4     | 2     | 2             | 0           | NO      | NO  |
      | 0         | 0       | NORMAL  | 5     | 1     | 1             | 0           | NO      | NO  |
      | 0         | 0       | NORMAL  | 6     | 2     | 2             | 0           | NO      | NO  |
      | 0         | 0       | SUPER   | 1     | 1     | 1             | 1           | NO      | NO  |
      | 0         | 0       | SUPER   | 2     | 2     | 2             | 1           | NO      | NO  |
      | 0         | 0       | SUPER   | 3     | 3     | 3             | 1           | NO      | NO  |
      | 0         | 0       | SUPER   | 4     | 4     | 4             | 1           | NO      | NO  |
      | 0         | 0       | SUPER   | 5     | 5     | 5             | 1           | NO      | NO  |
      | 0         | 0       | SUPER   | 6     | 6     | 6             | 1           | NO      | NO  |
      | 9         | 0       | NORMAL  | 1     | 1     | 10            | 0           | NO      | YES |
      | 9         | 0       | NORMAL  | 2     | 2     | 10            | 0           | NO      | YES |
      | 9         | 0       | NORMAL  | 3     | 1     | 10            | 0           | NO      | YES |
      | 9         | 0       | NORMAL  | 4     | 2     | 10            | 0           | NO      | YES |
      | 9         | 0       | NORMAL  | 5     | 1     | 10            | 0           | NO      | YES |
      | 9         | 0       | NORMAL  | 6     | 2     | 10            | 0           | NO      | YES |
      | 9         | 0       | SUPER   | 1     | 1     | 10            | 1           | NO      | YES |
      | 9         | 0       | SUPER   | 2     | 2     | 10            | 1           | NO      | YES |
      | 9         | 0       | SUPER   | 3     | 3     | 10            | 1           | NO      | YES |
      | 9         | 0       | SUPER   | 4     | 4     | 10            | 1           | NO      | YES |
      | 9         | 0       | SUPER   | 5     | 5     | 10            | 1           | NO      | YES |
      | 9         | 0       | SUPER   | 5     | 5     | 10            | 1           | NO      | YES |
      | 9         | 0       | SUPER   | 6     | 6     | 10            | 1           | NO      | YES |
      | 1         | 1       | NORMAL  | 1     | 0     | 1             | 1           | NO      | NO  |
      | 1         | 1       | NORMAL  | 2     | 1     | 2             | 1           | NO      | NO  |
      | 1         | 1       | NORMAL  | 3     | 0     | 1             | 1           | NO      | NO  |
      | 1         | 1       | NORMAL  | 4     | 1     | 2             | 1           | NO      | NO  |
      | 1         | 1       | NORMAL  | 5     | 0     | 1             | 1           | NO      | NO  |
      | 1         | 1       | NORMAL  | 6     | 1     | 2             | 1           | NO      | NO  |
      | 1         | 1       | SUPER   | 1     | 0     | 1             | 2           | NO      | NO  |
      | 1         | 1       | SUPER   | 2     | 1     | 2             | 2           | NO      | NO  |
      | 1         | 1       | SUPER   | 3     | 2     | 3             | 2           | NO      | NO  |
      | 1         | 1       | SUPER   | 4     | 3     | 4             | 2           | NO      | NO  |
      | 1         | 1       | SUPER   | 5     | 4     | 5             | 2           | NO      | NO  |
      | 1         | 1       | SUPER   | 6     | 5     | 6             | 2           | NO      | NO  |
      | 2         | 5       | NORMAL  | 1     | 0     | 2             | 5           | NO      | NO  |
      | 2         | 5       | NORMAL  | 2     | 0     | 2             | 5           | NO      | NO  |
      | 2         | 5       | NORMAL  | 3     | 0     | 2             | 5           | NO      | NO  |
      | 2         | 5       | NORMAL  | 4     | 0     | 2             | 5           | NO      | NO  |
      | 2         | 5       | NORMAL  | 5     | 0     | 2             | 5           | NO      | NO  |
      | 2         | 5       | NORMAL  | 6     | 0     | 2             | 5           | NO      | NO  |
      | 2         | 5       | SUPER   | 1     | 0     | 2             | 6           | YES     | NO  |
      | 2         | 5       | SUPER   | 2     | 0     | 2             | 6           | YES     | NO  |
      | 2         | 5       | SUPER   | 3     | 0     | 2             | 6           | YES     | NO  |
      | 2         | 5       | SUPER   | 4     | 0     | 2             | 6           | YES     | NO  |
      | 2         | 5       | SUPER   | 5     | 0     | 2             | 6           | YES     | NO  |
      | 2         | 5       | SUPER   | 6     | 1     | 3             | 6           | YES     | NO  |

  @wip
  Scenario: Welcome Message
    Given I am in a race
    When  I try to view the race
    Then  I must see the message: "Time to RACE! Alice rolls first!"

  @wip
  Scenario Outline: Message
    Given I am in a race
    And   I am at position <Position>
    And   I have damage of <Damage>
    And   I see the finish line at position 10
    When  I choose "<Speed>" speed
    And   I roll a <Roll>
    Then  I must see the message: "<Message>"

    Examples:

      | Position  | Damage  | Speed   | Roll  | Message                                                                                     |
      | 0         | 0       | NORMAL  | 1     | Alice chose NORMAL speed, and rolled 1 and moved 1. Bob rolls next!                         |
      | 0         | 0       | SUPER   | 1     | Alice chose SUPER speed, and rolled 1 and moved 1. Alice now has 1 damage. Bob rolls next!  |
      | 1         | 1       | NORMAL  | 2     | Alice chose NORMAL speed, and rolled 2 and moved 1. Alice now has 1 damage. Bob rolls next! |
      | 1         | 1       | SUPER   | 2     | Alice chose SUPER speed, and rolled 2 and moved 1. Alice now has 2 damage. Bob rolls next!  |
      | 5         | 5       | NORMAL  | 1     | Alice chose NORMAL speed, and rolled 1 and moved 0. Alice now has 5 damage. Bob rolls next! |
      | 5         | 5       | SUPER   | 1     | Alice chose SUPER speed, and rolled 1 and moved 0. Alice CRASHED!!! Bob rolls next!         |
      | 9         | 0       | NORMAL  | 1     | Alice wins the race! Congratulations!!!                                                     |
      | 9         | 0       | SUPER   | 1     | Alice wins the race! Congratulations!!!                                                     |

  Scenario: Crash and Burn
    Given I am in a race
    When  all racers have crashed!
    Then  our race must be over!

  Scenario: End
    When  it's over, it's over
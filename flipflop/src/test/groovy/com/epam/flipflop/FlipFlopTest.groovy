package com.epam.flipflop

import spock.lang.Specification

class FlipFlopTest extends Specification {
    def 'the flip-flop predicate returns false until the first condition is met'() {
        given:
        def flipFlop = new FlipFlop({ it == 1 }, { it == 2 })

        expect:
        !flipFlop.test(0)

        flipFlop.test(1)
        flipFlop.test(2)
        !flipFlop.test(3)
    }

}

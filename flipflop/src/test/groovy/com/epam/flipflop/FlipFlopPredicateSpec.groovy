package com.epam.flipflop

import spock.lang.Specification

import java.util.function.Predicate

class FlipFlopPredicateSpec extends Specification {
    def 'the flip-flop predicate returns false until the first condition is met'() {
        given: 'the flip-flop left and right side predicates'
        def leftSide = 'ON'::equals
        def rightSide = 'OFF'::equals

        when: 'we create a flip-flop predicate'
        def flipFlop = FlipFlopPredicate.flipFlop(leftSide, rightSide)

        then: 'the result is false while the left side is not met'
        flipFlop.test('A') == false

        then: 'the result is true when the left side is met'
        flipFlop.test('ON') == true

        then: 'the result is true until the right side is met'
        flipFlop.test('A') == true
    }

    def 'the newly created flip-flop predicate returns false until the first condition is met'() {
        given: 'the flip-flop predicate'
        def flipFlop = FlipFlop.create({ it == 1 }, { it == 2 })

        when: 'the number does not match the first condition'
        def result = flipFlop.test(0)

        then: 'the result is false'
        !result

        when: 'the number matches the first condition'
        result = flipFlop.test(1)

        then: 'the result is true'


    }
}

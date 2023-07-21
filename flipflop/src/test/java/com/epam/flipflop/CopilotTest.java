package com.epam.flipflop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.function.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CopilotTest {
   @Test
   void testFlipFlop() {
      var flipFlop = new FlipFlop<Integer>(x -> x % 2 == 0, x -> x % 3 == 0);

      assertTrue(flipFlop.test(2));
      assertTrue(flipFlop.test(3));
      assertTrue(flipFlop.test(4));
      assertTrue(flipFlop.test(6));
      assertFalse(flipFlop.test(9));
      assertTrue(flipFlop.test(10));
   }

   @Test
   @DisplayName("flipFlop should return false until the first condition is met")
   void testing_objects_that_do_not_satisfy_the_first_condition() {
      Predicate<Integer> isEven = x -> x % 2 == 0;
      Predicate<Integer> isDivisibleBy3 = x -> x % 3 == 0;

      Predicate<Integer> flipFlop = FlipFlopPredicate.flipFlop(isEven, isDivisibleBy3);

      assertFalse(flipFlop.test(1));
      assertFalse(flipFlop.test(3));
      assertFalse(flipFlop.test(7));
      assertTrue(flipFlop.test(4));
   }
}

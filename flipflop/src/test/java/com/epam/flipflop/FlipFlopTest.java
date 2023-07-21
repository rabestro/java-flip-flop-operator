package com.epam.flipflop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Predicate;

public class FlipFlopTest {

   @Test
   @DisplayName("Test FlipFlop with both false predicates")
   void testFlipFlopWithFalsePredicates() {
      // Both lhs and rhs return false
      Predicate<Integer> lhs = value -> false;
      Predicate<Integer> rhs = value -> false;
      FlipFlop<Integer> flipFlop = new FlipFlop<>(lhs, rhs);
      assertFalse(flipFlop.test(10), "Expected false when both predicates are false");
   }

   @Test
   @DisplayName("Test FlipFlop with left true and right false predicates")
   void testFlipFlopWithLeftTrueRightFalse() {
      // lhs returns true, rhs returns false
      Predicate<Integer> lhs = value -> true;
      Predicate<Integer> rhs = value -> false;
      FlipFlop<Integer> flipFlop = new FlipFlop<>(lhs, rhs);
      assertTrue(flipFlop.test(10), "Expected true when lhs is true and rhs is false");
   }

   @Test
   @DisplayName("Test FlipFlop with left false and right true predicates")
   void testFlipFlopWithLeftFalseRightTrue() {
      // lhs returns false, rhs returns true
      Predicate<Integer> lhs = value -> false;
      Predicate<Integer> rhs = value -> true;
      FlipFlop<Integer> flipFlop = new FlipFlop<>(lhs, rhs);
      assertFalse(flipFlop.test(10), "Expected false when lhs is false and rhs is true");
   }

   @Test
   @DisplayName("Test FlipFlop with both true predicates")
   void testFlipFlopWithTruePredicates() {
      // Both lhs and rhs return true
      Predicate<Integer> lhs = value -> true;
      Predicate<Integer> rhs = value -> true;
      FlipFlop<Integer> flipFlop = new FlipFlop<>(lhs, rhs);
      assertTrue(flipFlop.test(10), "Expected true when both predicates are true");
   }
}

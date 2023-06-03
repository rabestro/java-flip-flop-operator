package com.epam.engx.flipflop;

import java.util.function.Predicate;


public interface FlipFlopPredicate<T> extends Predicate<T> {

   boolean state();

   static <T> FlipFlopPredicate<T> flipFlop(Predicate<? super T> lhs, Predicate<? super T> rhs) {
      return new FlipFlop<>(lhs, rhs);
   }

   static <T> FlipFlopPredicate<T> lazyFlipFlop(Predicate<? super T> lhs, Predicate<? super T> rhs) {
      return new LazyFlipFlop<>(lhs, rhs);
   }
}

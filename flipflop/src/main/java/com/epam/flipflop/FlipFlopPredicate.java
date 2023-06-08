package com.epam.flipflop;

import java.util.function.Predicate;


@FunctionalInterface
public interface FlipFlopPredicate<T> extends Predicate<T> {

   static <T> Predicate<T> flipFlop(Predicate<? super T> lhs, Predicate<? super T> rhs) {
      return new FlipFlop<>(lhs, rhs);
   }

   static <T> Predicate<T> lazyFlipFlop(Predicate<? super T> lhs, Predicate<? super T> rhs) {
      return new LazyFlipFlop<>(lhs, rhs);
   }
}

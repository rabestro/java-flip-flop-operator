package com.epam.flipflop;

import java.util.function.Predicate;

@SuppressWarnings("java:S2039")
abstract class AbstractFlipFlop<T> implements FlipFlopPredicate<T> {
   final Predicate<? super T> lhs;
   final Predicate<? super T> rhs;
   boolean state;

   AbstractFlipFlop(Predicate<? super T> lhs, Predicate<? super T> rhs) {
      this.lhs = lhs;
      this.rhs = rhs;
   }
}

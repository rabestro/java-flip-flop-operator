package com.epam.engx.flipflop;

import java.util.function.Predicate;

@SuppressWarnings("java:S2039")
abstract class AbstractFlipFlop<T> implements FlipFlopPredicate<T> {
   final Predicate<? super T> flip;
   final Predicate<? super T> flop;
   boolean isActive;

   AbstractFlipFlop(Predicate<? super T> flip, Predicate<? super T> flop) {
      this.flip = flip;
      this.flop = flop;
   }

   @Override
   public boolean state() {
      return isActive;
   }
}

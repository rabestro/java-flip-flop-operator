package com.epam.engx.flipflop;

import java.util.function.Predicate;


abstract class AbstractFlipFlop<T> implements FlipFlopPredicate<T> {
   protected final Predicate<? super T> flip;
   protected final Predicate<? super T> flop;
   protected boolean isActive;

   AbstractFlipFlop(Predicate<? super T> flip, Predicate<? super T> flop) {
      this.flip = flip;
      this.flop = flop;
   }

   @Override
   public boolean isActive() {
      return isActive;
   }
}

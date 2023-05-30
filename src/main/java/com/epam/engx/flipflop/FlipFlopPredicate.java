package com.epam.engx.flipflop;

import java.util.function.Predicate;


public interface FlipFlopPredicate<T> extends Predicate<T> {

   boolean isActive();

   static <T> FlipFlopPredicate<T> of(Predicate<? super T> flip, Predicate<? super T> flop) {
      return new TwoDotFlipFlop<>(flip, flop);
   }
}

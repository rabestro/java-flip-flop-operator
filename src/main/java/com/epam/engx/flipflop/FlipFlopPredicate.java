package com.epam.engx.flipflop;

import java.util.function.Predicate;


public interface FlipFlopPredicate<T> extends Predicate<T> {

   boolean state();

   static <T> FlipFlopPredicate<T> twoDots(Predicate<? super T> flip, Predicate<? super T> flop) {
      return new TwoDotFlipFlop<>(flip, flop);
   }

   static <T> FlipFlopPredicate<T> threeDots(Predicate<? super T> flip, Predicate<? super T> flop) {
      return new ThreeDotFlipFlop<>(flip, flop);
   }
}

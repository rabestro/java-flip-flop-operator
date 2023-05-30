package com.epam.engx.flipflop;

import java.util.function.Predicate;


public class ThreeDotFlipFlop<T> extends AbstractFlipFlop<T> {

   public ThreeDotFlipFlop(Predicate<? super T> flip, Predicate<? super T> flop) {
      super(flip, flop);
   }

   @Override
   public boolean test(T value) {
      if (isActive) {
         isActive = !flop.test(value);
         return true;
      }
      isActive = flip.test(value);
      return isActive;
   }
}

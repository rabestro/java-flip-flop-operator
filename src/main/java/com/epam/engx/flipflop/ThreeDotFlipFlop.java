package com.epam.engx.flipflop;

import java.util.function.Predicate;


final class ThreeDotFlipFlop<T> extends AbstractFlipFlop<T> {

   ThreeDotFlipFlop(Predicate<? super T> flip, Predicate<? super T> flop) {
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

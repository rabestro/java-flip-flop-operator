package com.epam.engx.flipflop;

import java.util.function.Predicate;


public class TwoDotFlipFlop<T> extends AbstractFlipFlop<T> {
   TwoDotFlipFlop(Predicate<? super T> flip, Predicate<? super T> flop) {
      super(flip, flop);
   }

   @Override
   public boolean test(T value) {
      if (isActive || flip.test(value)) {
         isActive = !flop.test(value);
         return true;
      }
      return false;
   }
}

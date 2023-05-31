package com.epam.engx.flipflop;

import java.util.function.Predicate;


final class TwoDotFlipFlop<T> extends AbstractFlipFlop<T> {
   TwoDotFlipFlop(Predicate<? super T> flip, Predicate<? super T> flop) {
      super(flip, flop);
   }

   @Override
   public boolean test(T value) {
      var result = isActive || flip.test(value);
      isActive = result && !flop.test(value);
      return result;
   }
}

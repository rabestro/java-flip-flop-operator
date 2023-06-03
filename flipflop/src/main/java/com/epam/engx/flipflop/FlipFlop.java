package com.epam.engx.flipflop;

import java.util.function.Predicate;


final class FlipFlop<T> extends AbstractFlipFlop<T> {
   FlipFlop(Predicate<? super T> lhs, Predicate<? super T> rhs) {
      super(lhs, rhs);
   }

   @Override
   public boolean test(T value) {
      var result = state || lhs.test(value);
      state = result && !rhs.test(value);
      return result;
   }
}

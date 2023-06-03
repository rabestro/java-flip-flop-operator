package com.epam.engx.flipflop;

import java.util.function.Predicate;


final class LazyFlipFlop<T> extends AbstractFlipFlop<T> {

   LazyFlipFlop(Predicate<? super T> lhs, Predicate<? super T> rhs) {
      super(lhs, rhs);
   }

   @Override
   public boolean test(T value) {
      if (state) {
         state = !rhs.test(value);
         return true;
      }
      state = lhs.test(value);
      return state;
   }
}

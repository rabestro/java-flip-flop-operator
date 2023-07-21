package com.epam.flipflop;

import java.util.function.Predicate;


/**
 * A flip flop implementation that extends AbstractFlipFlop.
 *
 * @param <T> the type of the value being evaluated by the flip flop
 */

final class FlipFlop<T> extends AbstractFlipFlop<T> {
   FlipFlop(Predicate<? super T> lhs, Predicate<? super T> rhs) {
      super(lhs, rhs);
   }

   @Override
   public boolean test(final T value) {
      var result = state || lhs.test(value);
      state = result && !rhs.test(value);
      return result;
   }
}

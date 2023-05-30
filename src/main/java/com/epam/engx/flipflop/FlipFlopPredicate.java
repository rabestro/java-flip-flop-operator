package com.epam.engx.flipflop;

import java.util.function.Predicate;


public final class FlipFlopPredicate<T> implements Predicate<T> {
   private final Predicate<? super T> onPredicate;
   private final Predicate<? super T> offPredicate;
   private boolean onState;

   public FlipFlopPredicate(Predicate<? super T> onPredicate, Predicate<? super T> offPredicate) {
      this.onPredicate = onPredicate;
      this.offPredicate = offPredicate;
   }

   @Override
   public boolean test(T value) {
      if (onState || onPredicate.test(value)) {
         onState = !offPredicate.test(value);
         return true;
      }
      return false;
   }
}

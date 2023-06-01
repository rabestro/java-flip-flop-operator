package com.epam.engx.flipflop;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static com.epam.engx.flipflop.FlipFlopPredicate.flipFlop;
import static com.epam.engx.flipflop.FlipFlopPredicate.lazyFlipFlop;
import static java.util.stream.IntStream.rangeClosed;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class FlipFlopPredicateTest {
   private static final Predicate<Integer> isEven = x -> (x & 1) == 0;
   private static final Predicate<Integer> isBuzz = x -> x % 7 == 0 || x % 10 == 7;
   private static final Predicate<Integer> isDuck = x -> x > 0 && x % 10 == 0;

   @Test
   void create_integer_flip_flop_predicate_by_lambdas() {
      var flipFlop = FlipFlopPredicate.<Integer>flipFlop(
            x -> x == 2,
            x -> x == 5
      );

      assertThat(flipFlop.state())
            .as("The flip-flop operator has the status false just after the creation")
            .isFalse();
   }

   @Test
   void create_integer_flip_flop_predicate() {
      var actual = rangeClosed(1, 10).boxed()
            .filter(flipFlop(isBuzz, isEven));

      assertThat(actual)
            .as("the buzz number 7 turns on flip-flop, the even number turns it off")
            .containsExactly(7, 8);
   }

   @Test
   void flip_flop_turns_on_and_off_on_the_same_element() {
      var numbers = rangeClosed(10, 16).boxed();

      var actual = numbers.filter(flipFlop(isBuzz, isEven));

      assertThat(actual)
            .as("the flip-flop can turn off immediately after turning on")
            .containsExactly(14);
   }

   @Test
   void filter_one_javadoc_comment() {
      var sourceCode = """
            /**
             * Says 'Hello' to the World.
             */
            public class Sample {

               public String sayHelloWorld() {
                  return "Hello, world!"
               }
            }
            """;

      var javadoc = sourceCode.lines()
            .filter(flipFlop("/**"::equals, " */"::equals));

      assertThat(javadoc)
            .as("extract a javadoc comment from the source code")
            .containsExactly(
                  "/**",
                  " * Says 'Hello' to the World.",
                  " */");
   }

   @Test
   void extract_all_javadoc_comments() {
      var sourceCode = """
            /**
             * Says 'Hello' to the World.
             */
            public class Sample {
               public String sayHelloWorld() {
                  /** a string literal */
                  return "Hello, world!"
               }
            }
            """;

      var javadocPredicate = FlipFlopPredicate.<String>flipFlop(
            line -> line.contains("/**"),
            line -> line.contains("*/")
      );

      var javadocs = sourceCode.lines()
            .filter(javadocPredicate);

      assertThat(javadocs)
            .as("extract all javadoc comments from the source code")
            .containsExactly(
                  "/**",
                  " * Says 'Hello' to the World.",
                  " */",
                  "      /** a string literal */");
   }

   @Test
   void lazy_flip_flop_do_not_check_second_condition_immediately() {
      var flipFlop = lazyFlipFlop(isBuzz, isEven);

      var actual = rangeClosed(10, 16).boxed().filter(flipFlop).toArray();

      assertThat(actual)
            .as("the three-dots flip-flop don't check second condition immediately after turning on")
            .containsExactly(14, 15, 16);
   }

   @Test
   void test1() {
      var underTest = new FlipFlopImpl<Integer>(x -> x == 2, x -> x == 5);

      then(underTest.state())
            .isFalse();
      then(underTest.test(1))
            .isFalse();
      then(underTest.state())
            .isFalse();
      then(underTest.test(2))
            .isTrue();
      then(underTest.state()).isTrue();
   }
}

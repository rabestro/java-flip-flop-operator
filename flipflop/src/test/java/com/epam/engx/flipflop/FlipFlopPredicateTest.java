package com.epam.engx.flipflop;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;
import java.util.stream.Stream;

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

      var actual = numbers
            .filter(flipFlop(isBuzz, isEven));

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

      Predicate<String> javadocOpen = line -> line.contains("/**");
      Predicate<String> javadocClose = line -> line.contains("*/");

      var javadocPredicate = flipFlop(javadocOpen, javadocClose);

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

   private Stream<String> passingCars() {
      return Stream.of(
            "VC-087", "BN-756", "LM-408",
            "POL-01", "SQ-782", "AA-001", "TQ-644", "POL-02",
            "BC-034", "JQ-877", "KL-554", "WR-079");
   }

   @Test
   void use_flip_flop_to_filter_cortege_cars() {
      Predicate<String> policeOne = "POL-01"::equals;
      Predicate<String> policeTwo = "POL-02"::equals;

      var cortege = passingCars()
            .filter(flipFlop(policeOne, policeTwo));

      assertThat(cortege)
            .as("the cortege consists of five cars")
            .containsExactly("POL-01", "SQ-782", "AA-001", "TQ-644", "POL-02");
   }

   @Test
   void use_lazy_flip_flop_to_filter_cortege_cars() {
      Predicate<String> policeOne = "POL-01"::equals;
      Predicate<String> policeTwo = "POL-02"::equals;
      var policeCar = policeOne.or(policeTwo);

      var incorrectResult = passingCars()
            .filter(flipFlop(policeCar, policeCar));

      assertThat(incorrectResult)
            .as("the ordinal flip-flop works incorrectly")
            .containsExactly("POL-01", "POL-02");

      var cortege = passingCars()
            .filter(lazyFlipFlop(policeCar, policeCar));

      assertThat(cortege)
            .as("the cortege consists of five cars")
            .containsExactly("POL-01", "SQ-782", "AA-001", "TQ-644", "POL-02");
   }

   @Test
   void test1() {
      var underTest = new FlipFlop<Integer>(x -> x == 2, x -> x == 5);

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

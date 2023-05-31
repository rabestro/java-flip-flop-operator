package com.epam.engx;

import com.epam.engx.flipflop.FlipFlopPredicate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;


public class FlipFlopApp {
   private static final Predicate<String> JAVADOC_ON =
         Pattern.compile(".*/\\*\\*.*")
               .asPredicate();

   private static final Predicate<String> JAVADOC_OFF =
         Pattern.compile(".*[*]/.*")
               .asPredicate();

   @SuppressWarnings({ "java:S106", "java:S2096" })
   public static void main(String[] args) throws IOException {
      var filePath = Path.of(args[0]);
      var sourceCodeLines = Files.readAllLines(filePath).stream();

      var javaDocPredicate = FlipFlopPredicate.twoDots(JAVADOC_ON, JAVADOC_OFF);

      sourceCodeLines
            .filter(javaDocPredicate)
            .forEach(System.out::println);
   }
}

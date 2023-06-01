package com.epam.engx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.epam.engx.flipflop.FlipFlopPredicate.flipFlop;


@SuppressWarnings({ "java:S106", "java:S2096" })
public class FlipFlopApp {

   public static void main(String[] args) throws IOException {
      Files.readAllLines(Path.of(args[0]))
            .stream()
            .filter(flipFlop("/**"::equals, " */"::equals))
            .forEach(System.out::println);
   }
}

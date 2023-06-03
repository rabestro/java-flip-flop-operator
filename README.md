# Flip-Flop operator

Implementation of the logical operator Flip-Flop in Java.

## The AWK script with flip-flop operator

The script extracts and prints all javadoc comments by using flip-flop boolean operator.

```shell
gawk -f javadoc.awk Sample.java
```

## Java implementation

[plantuml, format="png", id="myId"]
----
@startuml
    circle Predicate<T>

    interface FlipFlopPredicate<T> {
        + {abstract} boolean state()
    }
    abstract class AbstractFlipFlop<T> {
        ~Predicate lhs
        ~Predicate rhs
        ~boolean state
        +boolean state()
    }
    class FlipFlop<T> {
        +test(T)
    }
    class LazyFlipFlop<T> {
        +test(T)
    }
    Predicate -- FlipFlopPredicate
    FlipFlopPredicate <|.. AbstractFlipFlop
    AbstractFlipFlop <|-- FlipFlop
    AbstractFlipFlop <|-- LazyFlipFlop
@enduml
----

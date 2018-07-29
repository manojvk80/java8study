package org.mvk.java8study;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * Consumer
 * Represents an operation that accepts a single input argument and returns no result
 * It is an interface in java.util.function. Has one method to implement.
 *  void accept(T t);
 * Consumer operates on t and create side effects - meaning it calls some other methods using values in t ot simply mutates t
 */
public class ConsumerExample {
    public static void main(String args[]) {
        ConsumerExample test = new ConsumerExample();
        test.printIntegers();
    }

    private void printIntegers() {
        PrintValues printValues = new PrintValues();
        IntStream.range(1, 10).forEach(printValues::accept);
        System.out.println();

        /*
        Below code is not directly related to Consumer
        Add5 add5 = new Add5();
        List<Object> added5List = IntStream.range(1, 10).boxed().map(add5).collect(Collectors.toList());
        added5List.forEach(t -> System.out.println(t));
        System.out.println();

        Add10 add10 = new Add10();
        List<Object> added10List = IntStream.range(1, 10).boxed().map(add10::accept).collect(Collectors.toList());
        added10List.forEach(t -> System.out.println(t));
        System.out.println();*/
      }

    class PrintValues implements Consumer<Integer> {
        @Override
        public void accept(Integer s) {
            System.out.println(s);
        }
    }

    class Add5 implements Function<Integer, Integer> {
        @Override
        public Integer apply(Integer integer) {
            return Math.addExact(integer, 5);
        }
    }

    class Add10 implements Consumer<Integer> {
        @Override
        public void accept(Integer integer) {
            integer = Math.addExact(integer, 10);
        }
    }
}

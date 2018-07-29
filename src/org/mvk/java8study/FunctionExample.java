package org.mvk.java8study;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Operate on an input object ands return a brand new Object
 * Does not mutate or has side effects (pure function)
 * public interface Function(T, R)
 * Input is type T and output is type R
 * Has one abstract method
 * R apply (T t)
 * Has andThen and compose methods which are used to chain methods calls
 * Has identity() function which is related to Monads in functional programming - See this  TODO
 * <p>
 * No need to write function from scratch every time. Example of practical use of function is map method in Streams
 */
public class FunctionExample {

    /**
     * Takes an input String and return an Integer
     */
    Function<String, Integer> findWordCount = new Function<String, Integer>() {
        @Override
        public Integer apply(String s) {
            return s.split(" ").length;
        }
    };


    public static void main(String args[]) {
        FunctionExample functionExample = new FunctionExample();
        functionExample.testWordCount();
        functionExample.testWordCount2();
        functionExample.testTypeConversion();
        functionExample.testFunctionChaining();
    }

    // Simple example of how to execute a function
    private void testWordCount() {
        System.out.println("testWordCount----------------");
        Integer count = findWordCount.apply("This is a test function");
        System.out.println(count);
    }

    // Above example is lame. You could have simple executed the method the traditional style.
    // That is true, but remember that we now have a way to pass around this function as a reference!
    private void testWordCount2() {
        System.out.println("testWordCount2----------------");
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stringList.add("Test" + i);
        }
        stringList.stream().map(findWordCount::apply);
    }

    // Note: If you have a method that takes more that one input, it cannot be represented by Function

    // Example of a function that converts a type from one to another
    Function<Integer, String> integerToStringFunction = new Function<Integer, String>() {
        @Override
        public String apply(Integer integer) {
            switch (integer) {
                case 0:
                    return "ZERO";
                case 1:
                    return "ONE";
                case 2:
                    return "TWO";
                default:
                    return String.valueOf(integer);
            }
        }
    };

    private void testTypeConversion() {
        System.out.println("testTypeConversion----------------");

        List<Integer> stringList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stringList.add(i);
        }
        stringList.stream().map(integerToStringFunction::apply);
    }

    // Function chaining
    private void testFunctionChaining() {
        System.out.println("testFunctionChaining----------------");

        // With andThen, the function calls are executed in the same order as written.
        // That means the apply is called on first function, then output is applied to second function and so on
        String countAsString = findWordCount.andThen(integerToStringFunction).apply("This is a test function");
        System.out.println(countAsString);

        // Compose does the chaining in the reverse order. Apply method is first applied to the composed function (integerToStringFunction) then the original function (findWordCount) is called
        Integer wordCount = findWordCount.compose(integerToStringFunction).apply(new Integer(10));
        System.out.println(wordCount);

        // Note: Important thing is that the output types has to match the chained functions input type.
    }
}

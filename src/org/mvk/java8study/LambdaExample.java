package org.mvk.java8study;

import java.util.Calendar;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.mvk.java8study.dataobjects.Employee;

/**
 * Lambda expressions
 * Simplifies use of Function, Supplier, Consumer, Predicate interfaces
 * A lambda expression is of type FunctionalInterface
 *
 */
public class LambdaExample {

    public static void main(String[] args) {
        new LambdaExample();
    }

    public LambdaExample() {

        // Create a predicate
        Predicate<String> keyWordChecker = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("MyKeyWord");
            }
        };
        // Converting this to a lambda expression would look like this:  t -> t.contains("MyKeyWord")
        Predicate<String> keyWordChecker2 = t -> t.contains("MyKeyWord");
        // Read it as: For input attribute t, apply t.contains("MyKeyWord") and return its output value
        // How will compiler understand the type of t? - Depends on the context in which it is used
        // How will compiler know that this should be treated as a Predicate? -> Depends on the return type. Here boolean is returned which is compatible with Predicate
        // If nothing is returned and if the context permits, JVM will implicitly convert lambda to a Consumer
        Consumer<String> aStringConsumer = t -> System.out.println(t);

        // Regular operations are treated as implicit return value. So below lambda takes an input type and returns a String. So JVM treat this as Function
        Function<Integer, String> integerToString = t -> Integer.toString(t);
        Function<Integer, Employee> integerToEmployee = t -> new Employee(t, "Default Employee Name");

        // Here we are using the same lambda as a consumer and a function!
        Consumer<Employee> anEmployeeConsumer = t -> t.getName();
        Function<Employee, String> anEmployeeNameFunction = t -> t.getName();

        // if type need to be mentioned in lambda do this: (String t) -> t.contains("MyKeyWord")
        // if multiple input parameters are there, do this: (a, b) -> a > b
        // if multiple input parameters along with their type are there, do this: (Integer a, Integer b) -> a > b
        // to specify return value -> (int a, int b) -> {return a+b;}

        // Simple example - lambda function
        Function<String, Integer> count = s -> s.split(" ").length;
        System.out.println(count.apply("Count the words"));

        // Lambda expression for supplier
        Supplier<Date> dateSupplier = new Supplier<Date>() {
            @Override
            public Date get() {
                return Calendar.getInstance().getTime();
            }
        };
        Supplier<Date> dateSupplierLambda = () -> Calendar.getInstance().getTime();

        //Lambda expression for Runnable
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Run something here");
            }
        };
        Runnable runnableLambda = () -> System.out.println("Run something here");


    }
}

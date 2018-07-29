package org.mvk.java8study;

import java.util.Date;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.mvk.java8study.dataobjects.MyDate;

/**
 * Predicate is a functional interface that takes a type as input and returns a boolean
 * The abstract method is:
 *  boolean test(T t)
 * Has two methods to chain the calls and compose the logic -> and(Predicate anotherPredicate), or(Predicate anotherPredicate)
 * Has a method to get logical negate predicate -> Predicate<T> negate()
 *
 * In short predicates cna be used to check if an object conforms to a logical operation or not
 *
 * @see java.util.function.Predicate
 */
public class PredicateExample {
    Predicate<String> containsKeyWord = new Predicate<String>() {
        @Override
        public boolean test(String s) {
            return s.contains("MyKeyword");
        }
    };


    Predicate<String> hasLessThan100Characters = new Predicate<String>() {
        @Override
        public boolean test(String s) {
            return s.length() <= 100;
        }
    };

    public static void main(String[] args) {
        PredicateExample predicateExample = new PredicateExample();
        predicateExample.test1();

    }

    private void test1() {
        String testString = "Learning Java 8 features by example";
        System.out.println(containsKeyWord.test(testString));
        System.out.println(hasLessThan100Characters.test(testString));

        // Chaining of predicates
        System.out.println(containsKeyWord.and(hasLessThan100Characters).test(testString));

        // Get logical negative predicate
        System.out.println(containsKeyWord.negate().and(hasLessThan100Characters).test(testString));

        // Create a predicate from a function already defined in another class
        // We could directly access the method here as the method is static
        Predicate<Date> weekendCheckerPredicate = MyDate :: isWeekend;
        System.out.println("Is weekend: "+weekendCheckerPredicate.test(new Date()));

        // What if the method is not static?
        MyDate myDate = new MyDate();
        Predicate<Date> mondayCheckerPredicate = myDate :: isMonday;
        System.out.println("Is monday: "+mondayCheckerPredicate.test(new Date()));

        // What if the method returns a boolean but there is no input? We cannot use it as a Predicate
        // We cannot use a function also due to missing input type
        // Instead use a Supplier
        Supplier<Boolean> isTodayMondayCheckerPredicate = myDate :: isTodayMonday;
        System.out.println("Is today monday: "+isTodayMondayCheckerPredicate.get());

    }
}

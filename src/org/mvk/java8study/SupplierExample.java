package org.mvk.java8study;

import java.util.Date;
import java.util.function.Function;
import java.util.function.Supplier;

import org.mvk.java8study.dataobjects.MyDate;

/**
 * A Supplier takes no arguments but returns a type
 */
public class SupplierExample {

    public SupplierExample() {

    }

    public static void main(String args[]) {
        // Create a supplier for MyDate. Remember that Supplier do not take input. So we can only invoke the no arg constructor!
        Supplier<MyDate> myDateSupplierVersion1 = MyDate::new;

        // Another way - least preferred
        Supplier<MyDate> myDateSupplierVersion2 = new Supplier<MyDate>() {
            @Override
            public MyDate get() {
                return new MyDate();
            }
        };

        // To initialize the supplier, call the get method. This will call the constructor of supplied class and returns the instance.
        MyDate myDate = myDateSupplierVersion1.get();
        System.out.println(myDate.today());

        // Supplier can not only return object instances. It can provide function references too
        Supplier<Date> todaySupplier = myDate::today;
        System.out.println(todaySupplier.get());

        // What if I want to create a supplier for a function that takes an input. Supplier cannot be used as it do not take any input
        // Instead use Function
        // Remember: A Function takes in a type T and returns a type R using the apply method (Function<T, R>)
        Function<Integer, Date> futureDayFunction = myDate::getFutureDateIncrementedBy;
        Date day5 = futureDayFunction.apply(5);
        System.out.println(day5);

        // We referred to myDate object to get the supplier. Instead if the class has static methods we can refer to methods directly
        // eg: Supplier<Date> todaySupplier = MyDate::today;

    }
}

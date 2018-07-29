package org.mvk.java8study;

import java.util.ArrayList;
import java.util.List;

import org.mvk.java8study.dataobjects.Employee;

/**
 * ForEach
 * Replacement to loops in java. Functional style. Added to java.lang.Iterable interface in Java 8.
 *  The default implementation of forEach method in ArrayList uses a for loop internally
 *  default void forEach(Consumer<? super T> action)
 *      Performs the given action for each element of the Iterable until all elements have been processed or the action throws an exception.
 *      Unless otherwise specified by the implementing class, actions are performed in the order of iteration (if an iteration order is specified).
 *      Exceptions thrown by the action are relayed to the caller.
 * History -> for/while loops -> Enumeration -> Iterator -> Enhanced for loop [for (Employee e: employees)] -> ForEach
 * @see java.util.function.Consumer
 */
public class ForEachExample {
    public static void main(String[] args) {
        ForEachExample main = new ForEachExample();
        main.testForEach();
    }

    private void testForEach() {
        Employee e;
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            e = new Employee();
            e.setId(i);
            e.setName("Test" + i);
            employeeList.add(e);
        }
        employeeList.forEach(t -> System.out.println(t.getName()));
        // Note: System.out.println is a consumer which takes an input and do not return anything, but can cause side effects on input
    }

}

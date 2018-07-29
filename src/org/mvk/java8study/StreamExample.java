package org.mvk.java8study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.mvk.java8study.dataobjects.Employee;

/**
 * Stream is kind of a pipeline where you can apply functions, be it filtering, transforming -> converting to another type, limiting to certain size etc
 * Advantage of using Streams:
 *      Function programming style - very readable code
 *      Takes advantage of multi core systems and hence execution is faster
 * Original collection is not modified when converted to stream
 * Intermediate Methods - ones that take a stream and returns a stream
 *      filter - filter values using a predicate function
 *      map - processes and converts each object to another type using a Function function
 *      limit - Limits the results to the given input limit number
 *      sorted - Sorts the stream using a Comparator
 *      distinct - Removed duplicate method by using equals method on object
 *      flatMap - If the map operation returns a list and if that list need to be converted to a stream, use flatMap
 * Terminal Operations - Terminal operation creates a result other than a stream - eg: List, Integer etc
 *      forEach - Do something on each object in the stream and ends the processing. Input is a consumer
 *      count - Returns an indicating the count
 *      collect - Reduces the stream into a desired collection using a Collector. It can also return a single Object
 */
public class StreamExample {
    public static void main(String[] args) {
        new StreamExample();
    }

    public StreamExample() {
        // Simple stream of String - from literal values
        Stream<String> nameStream = Stream.of("Joe", "Adam", "Pete");
        nameStream.forEach(System.out::println);
        System.out.println();

        // Stream using IntStream
        IntStream.range(1, 10).forEach(System.out::println);
        System.out.println();

        // Steam using IntStream, but skip the first 5 values
        IntStream.range(1, 10).skip(5).forEach(System.out::println);
        System.out.println();

        // Create a stream using Collection interfaces stream method
        Set<Integer> integerSet = new HashSet<>();
        integerSet.add(10);
        integerSet.add(15);
        integerSet.add(11);
        integerSet.add(12);
        integerSet.add(13);
        integerSet.add(14);

        integerSet.stream().forEach(System.out::println);
        System.out.println();

        // Sort the list in descending order.
        // Streams has a sorted method which takes a Comparator. Simply supply the comparator as a lambda expression
        // Reading the below lambda: A function that takes two arguments a and b as input and then returns the value of b - a.
        integerSet.stream().sorted((a, b) -> b-a).forEach(System.out::println);
        System.out.println();

        // Filter values in stream - filter even values out. Means keep only odd values in the list
        // filter takes a predicate. That means it can take a function that takes one input and return a boolean value
        // Reading the lambda: A function that takes a single input of a and returns the value of a % 2 != 0
        integerSet.stream().filter(a -> a % 2 != 0).forEach(System.out::println);
        System.out.println();

        // Lets try filtering a stream of custom objects
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            employeeList.add(new Employee(i, "Employee "+i));
        }
        // Filter out employees who has even employee id
        employeeList.stream().filter(t -> t.getId()%2 != 0).forEach(t -> System.out.println(t.getId()+", "+t.getName()));
        System.out.println();

        // Did my original employeeList got modified? It should not be as stream does not modify the original collection. Lets check
        employeeList.stream().forEach(t -> System.out.println(t.getId()+", "+t.getName()));
        System.out.println();

        // Chaining of filters. Filter out employees who has even ids and whose name contains 5
        employeeList.stream().filter(t -> t.getId()%2 != 0).filter(t -> !t.getName().contains("5")).forEach(t -> System.out.println(t.getId()+", "+t.getName()));
        System.out.println();

        // Lets convert the list of employees to a list of Strings which contain their names
        // Map function is used here
        employeeList.stream().map(new Function<Employee, String>() {
            @Override
            public String apply(Employee employee) {
                return employee.getName();
            }
        }).forEach(System.out::println);
        System.out.println();

        // Lets use a lambda instead. But map method takes as input a Function. How can it take as input a lambda?
        // Function is a FunctionalInterface (it is annotated with @FunctionalInterface). lambda expressions, method references, or constructor references automatically creates an instance of FunctionInterface
        // So you can pass lambda and method references to map method
        // Same is happening with forEach. forEach method takes a Consumer which is a FunctionInterface. Method reference System.out::println creates a Function interface. Hence it is compatible
        employeeList.stream().map(employee -> employee.getName()).forEach(System.out::println);
        System.out.println();

        // Another way of implementing this is by passing a function. The :: operator creates a method reference (which is an instance of Functional Reference)
        employeeList.stream().map(this::getTranslatedEmployeeName).forEach(System.out::println);
        System.out.println();

        // Collector
        // Simple collect
        List<String> employeeName = employeeList.stream().map(this::getTranslatedEmployeeName).collect(Collectors.toList());

        // Collect and group employees into odd id and even id group
        Map<Integer, List<Employee>> groupedEmployee = employeeList.stream().collect(Collectors.groupingBy(t -> t.getId() % 2 == 0 ? 1 : 2));
        System.out.println(groupedEmployee);

        // FlatMap
        // Useful to flatten multiple lists into a single stream
        List<List<String>> list = Arrays.asList(Arrays.asList("a1", "a2", "a3"),Arrays.asList("b1", "b2", "b3", "b4"));
        List<String> newFlatList = list.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(newFlatList);
        // Consider a employee object with list of programmingLanguages as String. flatmap is useful to flatten the list across employees and create a new stream of languages
        // eg: employeeList.stream().map(e -> e.getLanguages()).flatMap(l -> l.stream()).distinct().collect(Collectors.asList());

    }

    private String getTranslatedEmployeeName(Employee e) {
        return (e.getId()%2==0?"Even":"Odd")+" "+e.getName();
    }

}

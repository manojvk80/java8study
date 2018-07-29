## Study project to understand features added in Java 8

### FunctionalInterface
 An interface that contains only one abstract method is called a functional interface. 
 A functional interface can have any number of default methods. 
 Runnable, ActionListener, Comparable are some of the examples of functional interfaces.
 
 @FunctionalInterface annotation is used to ensure that the functional interface can’t have more than one abstract method. However, it is not mandatory to use this annotation.
                                                                                                                           
 BuiltIn functional interfaces are available in java.util.function package and some of the commonly used ones are:
  * Predicate
  * Supplier
  * Consumer
  * Function
  * BinaryOperator
  
  **Predicate**
  Accepts a type as input and and returns a boolean. The abstract method is: boolean test(T t). Predicates are used to check if an object conforms to a logical operation or not
  
  For examples refer _org.mvk.java8study.PredicateExample_
  
  **Supplier**
  Does not accept an input, but returns a type. The abstract method is: T get();  Suppliers represents <obviously> a supplier of results
  
  For examples refer _org.mvk.java8study.SupplierExample_
  
  **Consumer**
  Accepts an input, but does not return anything. The abstract method is void accept(T t); Consumer operates on t and create side effects - meaning it calls some other methods using values in t or simply mutates t

  For examples refer _org.mvk.java8study.ConsumerExample_
  
  **Function**
  Accepts an input type and returns a type (can be same type or different). They do not mutate or has side effects (pure function). Useful to map data from the input object to an output object. The abstract method is R apply (T t)
  
  For examples refer _org.mvk.java8study.FunctionExample_
  
  All of these interfaces support chaining so that we can build fluent style code
  
#### Lambda expression
Lambda expression let's us pass code as arguments in a concise way, eliminating the need to write anonymous inner classes. Think of lambdas as a way of coding a "behaviour" that can be passed around.
A lambda expression do not have a name, but can accept parameters, has a return type, has a body and can also throw exceptions (consider a regular java method without a name)
A lambda expression can be used where a Function Interface is expected provided the lambda expression has the same signature as the abstract method of the function interface (not just Predicate, Consumer or any of the newer ones, but also the old ones like Comparator, Runnable etc)

For example, a lambda expression for a simple comparator: (a, b) -> a - b OR (Employee a, Employee b) -> a.getSalary().compareTo(b.getSalary())

Refer _org.mvk.java8study.LambdaExample_ 

#### Putting concepts together - Enabling functional programming
FunctionInterface annotation forces you to have only one abstract method. This can then be implemented using lambda. This will force us to create a lot of function
interfaces. To avoid that Java pre-built generic interfaces as Predicate, Consumer, Function etc. 
For efficiency (avoid auto-boxing), there are functional interfaces for primitives like IntPredicate, IntConsumer, IntFunction etc
  

#### :: operator 
Double colon operator is used for method referencing. We can extract static methods from classes by using it or methods from objects. The same operator can be used even for constructors.

Example: MyDate::isWeekend or myDate::isMonday  

A method reference is the shorthand syntax for a lambda expression that executes just ONE method  

Tip: Instead of using AN ANONYMOUS CLASS you can use A LAMBDA EXPRESSION. And if this just calls one method, you can use A METHOD REFERENCE


### Streams
Streams lets you apply transformations on a collection in a concise and efficient way (parallel processing using multiple cpu cores)
 
Refer _org.mvk.java8study.StreamExample_


### Optional
TODO

### New Date and Time APIs
TODO

### Default
TODO

### CompletableFuture
TODO

###:: operator 
Double colon operator is used for method referencing. So, one can extract static methods from classes by using it or methods from objects. The same operator can be used even for constructors.

A method reference is the shorthand syntax for a lambda expression that executes just ONE method. Here ONE method means there is only one line in the implementing code and that is calling a method 

Instead of using AN ANONYMOUS CLASS you can use A LAMBDA EXPRESSION. And if this just calls one method, you can use A METHOD REFERENCE

https://www.codementor.io/eh3rrera/using-java-8-method-reference-du10866vx


###Functional interface
 An interface that contains only one abstract method is called a functional interface. A functional interface can have any number of default methods. Runnable, ActionListener, Comparable are some of the examples of functional interfaces.
 
 @FunctionalInterface annotation is used to ensure that the functional interface can’t have more than one abstract method. However, it is not mandatory to use this annotation.
                                                                                                                           
BuiltIn functional interfaces are available in java.util.function package and includes:
  * Predicate
  * Supplier
  * Consumer
  * Function
  * BinaryOperator
  
    
    
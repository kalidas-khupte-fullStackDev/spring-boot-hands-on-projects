import java.util.*;
import java.util.function.*;
import java.util.stream.*;

// ─────────────────────────────────────────────
//  Helper classes used across all 4 examples
// ─────────────────────────────────────────────
class MathUtils {
    // Static method  → used in Type 1
    public static int square(int n) {
        return n * n;
    }
}

class Greeter {
    private String prefix;

    public Greeter(String prefix) {
        this.prefix = prefix;
    }

    // Instance method on a PARTICULAR object → Type 2
    public String greet(String name) {
        return prefix + ", " + name + "!";
    }
}

class Employee {
    private String name;
    private double salary;

    // Constructor → Type 4
    public Employee(String name) {
        this.name   = name;
        this.salary = 50_000;
    }

    // Instance method on an ARBITRARY object of the type → Type 3
    public String getNameUpperCase() {
        return name.toUpperCase();
    }

    public String getNameLowerCase() {
        return name.toLowerCase();
    }

    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', salary=" + salary + "}";
    }
}

// ─────────────────────────────────────────────
//  Main demo class
// ─────────────────────────────────────────────
public class MethodReferences {

    public static void main(String[] args) {

        separator("TYPE 1 — Static Method Reference  (ClassName::staticMethod)");
        type1_StaticMethodReference();

        separator("TYPE 2 — Instance Method Reference on a Specific Object  (instance::method)");
        type2_InstanceMethodOnSpecificObject();

        separator("TYPE 3 — Instance Method Reference on an Arbitrary Object  (ClassName::instanceMethod)");
        type3_InstanceMethodOnArbitraryObject();

        separator("TYPE 4 — Constructor Reference  (ClassName::new)");
        type4_ConstructorReference();
    }

    // ──────────────────────────────────────────
    // TYPE 1 : ClassName::staticMethod
    //
    //  Lambda equivalent  : n -> MathUtils.square(n)
    //  Used when          : the method belongs to the class (not an instance)
    // ──────────────────────────────────────────
    static void type1_StaticMethodReference() {

        // --- with lambda (for comparison) ---
        Function<Integer, Integer> squareLambda = n -> MathUtils.square(n);

        // --- with method reference ---
        Function<Integer, Integer> squareRef = MathUtils::square;

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        System.out.println("Squares using lambda        : " +
                numbers.stream().map(squareLambda).collect(Collectors.toList()));

        System.out.println("Squares using method ref    : " +
                numbers.stream().map(squareRef).collect(Collectors.toList()));

        // Printing to console — System.out::println is also a static-style ref
        System.out.println("\nPrinting each number via System.out::println:");
        numbers.forEach(System.out::println);
    }

    // ──────────────────────────────────────────
    // TYPE 2 : instance::instanceMethod
    //
    //  Lambda equivalent  : name -> specificGreeter.greet(name)
    //  Used when          : you already have a concrete object whose method to call
    // ──────────────────────────────────────────
    static void type2_InstanceMethodOnSpecificObject() {

        Greeter morningGreeter = new Greeter("Good Morning");
        Greeter eveningGreeter = new Greeter("Good Evening");

        // --- with lambda ---
        Function<String, String> morningLambda  = name -> morningGreeter.greet(name);

        // --- with method reference ---
        Function<String, String> morningRef     = morningGreeter::greet;
        Function<String, String> eveningRef     = eveningGreeter::greet;

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        System.out.println("Morning greetings (lambda)  : " +
                names.stream().map(morningLambda).collect(Collectors.toList()));

        System.out.println("Morning greetings (ref)     : " +
                names.stream().map(morningRef).collect(Collectors.toList()));

        System.out.println("Evening greetings (ref)     : " +
                names.stream().map(eveningRef).toList());
    }

    // ──────────────────────────────────────────
    // TYPE 3 : ClassName::instanceMethod
    //
    //  Lambda equivalent  : emp -> emp.getNameUpperCase()
    //  Used when          : the object on which to call the method is supplied
    //                       by the stream / functional interface at runtime
    // ──────────────────────────────────────────
    static void type3_InstanceMethodOnArbitraryObject() {

        List<Employee> employees = Arrays.asList(
                new Employee("diana"),
                new Employee("eve"),
                new Employee("frank")
        );

        // --- with lambda ---
        List<String> namesLambda = employees.stream()
                .map(emp -> emp.getNameUpperCase())
                .collect(Collectors.toList());

        // --- with method reference ---
        List<String> namesRef = employees.stream()
                .map(Employee::getNameUpperCase)        // Employee is the TYPE, not an instance
                .collect(Collectors.toList());

        List<String> lowerNamesRef = employees.stream()
                .map(Employee::getNameLowerCase).       // Employee is the TYPE, not an instance
                toList();

        System.out.println("Upper-case names (lambda)   : " + namesLambda);
        System.out.println("Upper-case names (ref)      : " + namesRef);
        System.out.println("lower-case names (ref)      : " + lowerNamesRef);

        // Another classic example: String::toLowerCase
        List<String> words = Arrays.asList("HELLO", "WORLD", "JAVA");
        List<String> lower = words.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        System.out.println("String::toLowerCase example : " + lower);

        // Sorting with Comparator — also Type 3
        List<String> sorted = Arrays.asList("banana", "apple", "cherry", "date");
        sorted.sort(String::compareTo);
//        Collections.sort(sorted);
        System.out.println("Sorted via String::compareTo: " + sorted);
    }

    // ──────────────────────────────────────────
    // TYPE 4 : ClassName::new
    //
    //  Lambda equivalent  : name -> new Employee(name)
    //  Used when          : you want to create objects inside a stream / factory
    // ────────────────────────────0──────────────
    static void type4_ConstructorReference() {

        List<String> names = Arrays.asList("Grace", "Heidi", "Ivan");

        // --- with lambda ---
        List<Employee> empLambda = names.stream()
                .map(Employee::new)
                .toList();

        // --- with constructor reference ---
        List<Employee> empRef = names.stream()
                .map(Employee::new)                     // calls Employee(String) constructor
                .toList();

        System.out.println("Employees (lambda)          : " + empLambda);
        System.out.println("Employees (constructor ref) : " + empRef);

        // Supplier<Employee> with no-arg style (using a Supplier<String>-backed approach)
        Supplier<ArrayList<String>> listFactory = ArrayList::new;  // ArrayList::new
        ArrayList<String> freshList = listFactory.get();
        freshList.add("one");
        freshList.add("two");
        System.out.println("ArrayList via Supplier ref  : " + freshList);
    }

    // ─── utility ───────────────────────────────
    static void separator(String title) {
        System.out.println("\n" + "=".repeat(65));
        System.out.println("  " + title);
        System.out.println("=".repeat(65));
    }
}
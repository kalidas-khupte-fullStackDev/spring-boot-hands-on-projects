import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class HashCodeExample {

    // The broken way
    private static int normalCount = 0;

    public static void main(String[] args) throws InterruptedException {

        printMessage();

    }

    public static void printMessage() {
        AtomicReference<String> message = new AtomicReference<>("Hello, World!"); // This is 'effectively final'

//         message = "Changed"; // If you uncomment this, 'message' is no longer effectively final
        // The code below would then cause a compiler error

//        Runnable r = () -> {
//            System.out.println(message.get()); // Uses the effectively final variable
//             message.set("Changed"); // If you uncomment this, 'message' is no longer effectively final
//            System.out.println(message.get()); // Uses the effectively final variable
//        };
//        r.run();

        int counter = 0;
        List<String> names = Arrays.asList("Java", "Spring", "Karat");

        names.forEach(name -> {
//            counter++; // COMPILE-TIME ERROR: Variable 'counter' is accessed from within inner class,
            // needs to be final or effectively final.
//            System.out.println(name + counter);
        });

        List<String> stream = List.of("A", "B", "C");
        stream.forEach(System.out::println); // This consumes the stream

        Stream<String>  stream1 = Stream.of("A1", "B2", "C");
// RUNTIME ERROR: IllegalStateException: stream has already been operated upon or closed
        stream1.forEach(System.out::println);
    }
}
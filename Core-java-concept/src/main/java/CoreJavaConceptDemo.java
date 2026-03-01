import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CoreJavaConceptDemo {

    public static void main(String[] args) {
//        String input = "microservices";
//        countAlphabet(input);
        new CoreJavaConceptDemo().withdraw(1000);
    }

    static void countAlphabet(String input) {
        Map<String, Long> charCount = Arrays.stream(input.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()
                ));

    }

    private int balance = 10000;

    public void withdraw(int amount) {
        // Any thread can run this part simultaneously
        System.out.println(Thread.currentThread().getName() + " is trying to withdraw...");

        // The Synchronization Block: Only ONE thread can enter this at a time
        synchronized (this) {
            if (balance >= amount) {
                System.out.println("Balance is sufficient for " + Thread.currentThread().getName());
                balance = balance - amount; // Safely update the shared data
                System.out.println("Withdrawal successful. New balance: " + balance);
            } else {
                System.out.println("Insufficient funds.");
            }
        }
    }
}

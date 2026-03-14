import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicExample {

    // The broken way
    private static int normalCount = 0;

    // The safe, modern, lock-free way
    private static AtomicInteger atomicCount = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Integer a = 100;
        int b = 200;
        System.out.println("first");
        System.out.println(a.equals(b));
        a =200;
        b =200;
        System.out.println("2nd");
        System.out.println(a==b);
        try (ExecutorService executor = Executors.newFixedThreadPool(10)) {

            // 1000 tasks trying to add 1 at the same time
            for (int i = 0; i < 1000; i++) {
                executor.submit(() -> {
                    normalCount++; // DANGER: Threads overwrite each other here
                    atomicCount.incrementAndGet(); // SAFE: Atomic, lock-free addition
                });
            }
        }

        // Let's see the results!
        System.out.println("Normal int (usually wrong): " + normalCount);
        System.out.println("AtomicInteger (always 1000): " + atomicCount.get());

        UserBuilderPattern user = new UserBuilderPattern.UserBuilder()
                .setName("Alice")
                .setAge(28)
                .build();

        System.out.println("UserBuilderPattern Ex "+ user);
        System.out.println("UserBuilderPattern Ex "+ user.getName());

    }
}
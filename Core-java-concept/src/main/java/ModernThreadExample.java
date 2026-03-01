import java.util.concurrent.*;

public class ModernThreadExample {
    public static void main(String[] args) {
        // 1. Create a queue that holds up to 5 messages
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

        // Declare the executor INSIDE the try() parentheses
        // 2. Create a Thread Pool with 2 worker threads
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {

            // 3. The Sender Task (Producer)
            executor.submit(() -> {
                try {
                    System.out.println("Sender: Working on a message...");
//                    Thread.sleep(2000); // Simulate delay
                    queue.put("Hello from the ExecutorService!"); // Automatically waits if queue is full
                    System.out.println("Sender: Message sent into the queue.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            // 4. The Receiver Task (Consumer)
            executor.submit(() -> {
                try {
                    System.out.println("Receiver: Waiting for a message...");
                    // take() automatically sleeps until a message arrives! No wait() needed.
                    String message = queue.take();
                    System.out.println("Receiver got: " + message);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

        } // <-- The magic happens here! Java automatically calls shutdown() right here.

    }
}
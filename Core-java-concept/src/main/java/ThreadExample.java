import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MessageBoard {
    private String message;
    private boolean hasMessage = false;

    // Method for Thread 1 to wait and read
    public synchronized void readMessage() {
        // ALWAYS put wait() inside a while loop to check the condition!
        while (!hasMessage) {
            try {
                System.out.println("Receiver: Waiting for a message...");
                wait(); // Drops the lock and goes to sleep
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Receiver got: " + message);
        hasMessage = false;
    }

    // Method for Thread 2 to write and notify
    public synchronized void writeMessage(String msg) {
        this.message = msg;
        this.hasMessage = true;
        System.out.println("Sender: Wrote the message.");
        notifyAll(); // Wakes up all threads waiting on this object
    }
}

public class ThreadExample {
    public static void main(String[] args) {
//        MessageBoard board = new MessageBoard();
//
//        // 1. Create Thread 1 (Receiver)
//        Thread receiverThread = new Thread(() -> {
//            board.readMessage();
//        });
//
//        // 2. Create Thread 2 (Sender)
//        Thread senderThread = new Thread(() -> {
//            try {
//                Thread.sleep(2000); // Simulate some delay
//                board.writeMessage("Hello from the other thread!");
//            } catch (InterruptedException e) {}
//        });
//
//        // 3. Start both threads
//        receiverThread.start();
//        senderThread.start();

        futureDemo();
    }

    public static void futureDemo() {

        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {

            System.out.println("Main Thread: Submitting tasks...");

            // submit() gives you a Future. We use Callable (returns a value) instead of Runnable.
            Future<Integer> mathFuture = executor.submit(() -> {
                Thread.sleep(2000);
                return 85; // Math score
            });

            Future<Integer> scienceFuture = executor.submit(() -> {
                Thread.sleep(1000);
                return 92; // Science score
            });

            System.out.println("Main Thread: Waiting for results...");

            try {
                // future.get() is the modern join()!
                // The Main Thread pauses here until the specific task is done.
                int mathScore = mathFuture.get();
                int scienceScore = scienceFuture.get();

                System.out.println("Main Thread: Total Score is " + (mathScore + scienceScore));

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}


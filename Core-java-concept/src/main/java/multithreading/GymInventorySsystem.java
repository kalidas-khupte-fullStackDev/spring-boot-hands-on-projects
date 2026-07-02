package multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class GymInventorySsystem {

    public static void main(String[] args) throws InterruptedException {
        // Create a thread-safe, bounded queue that holds a maximum of 3 elements
        BlockingQueue<String> proteinBuffer = new ArrayBlockingQueue<>(3);

        // 🍳 THE PRODUCER THREAD: Generates resource containers
        Thread producerWorker = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    String shake = "Protein_Shake_#" + i;
                    System.out.println("🏭 [Producer]: Preparing and mixing " + shake + "...");

                    // .put() is a blocking operation! If the queue is full (size 3),
                    // this thread freezes here automatically until the consumer calls .take().
                    proteinBuffer.put(shake);
                    System.out.println("📥 [Producer]: Successfully slotted " + shake + " onto the belt.");

                    Thread.sleep(100); // Producer works relatively fast
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 😋 THE CONSUMER THREAD: Processes/Dispatches containers
        Thread consumerWorker = new Thread(() -> {
            try {
                // Simulating continuous resource consumption
                for (int i = 1; i <= 5; i++) {
                    Thread.sleep(600); // Simulating slow consumption (heavy processing)

                    // .take() is a blocking operation! If the queue goes empty,
                    // this thread freezes until the producer injects a fresh item.
                    String consumedItem = proteinBuffer.take();
                    System.out.println("🎯 [Consumer]: Drank and processed: " + consumedItem);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        System.out.println("🚀 Initializing Producer-Consumer Background Lifecycles...\n");
        producerWorker.start();
        consumerWorker.start();

//        producerWorker.join();
//        consumerWorker.join();
        System.out.println("\n🏁 Production lines drained. System shut down cleanly.");
    }
}

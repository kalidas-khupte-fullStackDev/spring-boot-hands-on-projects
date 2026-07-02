package multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class QueueBehaviorDemo {
    public static void main(String[] args) throws InterruptedException {

        // 🧱 1. ArrayBlockingQueue: Strictly limited to 2 items
        BlockingQueue<String> arrayQueue = new ArrayBlockingQueue<>(2);
        arrayQueue.put("Item A");
        arrayQueue.put("Item B");
//         arrayQueue.put("Item C"); // 🛑 This line would freeze the main thread forever!
        System.out.println("ArrayBlockingQueue 1st " + arrayQueue.take());
        System.out.println("ArrayBlockingQueue 2nd " + arrayQueue.take());
//        System.out.println("ArrayBlockingQueue 3rd " + arrayQueue.take());


        // 👑 2. PriorityBlockingQueue: Sorts elements dynamically by weight/natural order
        // Let's create a queue of integers
        BlockingQueue<Integer> priorityQueue = new PriorityBlockingQueue<>();

        priorityQueue.put(50); // Insert 50
        priorityQueue.put(10); // Insert 10 (Lower value, but higher priority naturally)
        priorityQueue.put(99); // Insert 99

        // Even though 50 was inserted first, 10 jumps straight to the front of the line!
        System.out.println("🎯 First item out of Priority Queue: " + priorityQueue.take()); // Prints: 10
        System.out.println("🎯 Second item out of Priority Queue: " + priorityQueue.take()); // Prints: 50
        System.out.println("🎯 3rd item out of Priority Queue: " + priorityQueue.take()); // Prints: 50
    }
}

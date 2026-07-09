package advance.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {

    // Shared resource
    private double x = 0.0;
    private double y = 0.0;

    private final StampedLock lock = new StampedLock();

    // 1. Writer Method (Exclusive Lock)
    public void move(double deltaX, double deltaY) {
        long stamp = lock.writeLock(); // Enforces a happens-before relationship
        try {
            System.out.println(Thread.currentThread().getName() + " acquired WRITE lock.");
            x += deltaX;
            y += deltaY;
            try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        } finally {
            lock.unlockWrite(stamp);
            System.out.println(Thread.currentThread().getName() + " released WRITE lock.");
        }
    }

    // 2. Reader Method (Optimistic, Non-Blocking Read with Fallback)
    public double distanceFromOrigin() {
        // Try an optimistic read (does not look at standard lock flags; returns a validation stamp)
        long stamp = lock.tryOptimisticRead();

        double currentX = x;
        double currentY = y;

        // ⚠️ Place a breakpoint here during debug to simulate a collision!
        // If a writer updates x or y after tryOptimisticRead(), validate() returns false.
        if (!lock.validate(stamp)) {
            System.out.println(Thread.currentThread().getName() + " detected dirty read! Falling back to readLock().");

            // Fallback: Acquire a traditional, pessimistic read lock
            stamp = lock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                lock.unlockRead(stamp); // Ensure release
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " successfully completed OPTIMISTIC read.");
        }

        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    public static void main(String[] args) throws InterruptedException {
        StampedLockDemo demo = new StampedLockDemo();
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Reader Thread 1
        executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
               double result = demo.distanceFromOrigin();
               System.out.println("distanceFromOrigin result: " + result);
                try { Thread.sleep(20); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        });

        // Writer Thread
        executor.submit(() -> {
            for (int i = 0; i < 5; i++) {
                demo.move(1.0, 2.0);
                try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        });

        // Reader Thread 2
        executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                demo.distanceFromOrigin();
                try { Thread.sleep(20); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        });

        // Let it run briefly then shut down safely
        Thread.sleep(500);
        executor.shutdownNow();
        executor.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("Execution finished.");
    }
}

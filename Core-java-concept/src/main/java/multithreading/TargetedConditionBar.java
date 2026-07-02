package multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TargetedConditionBar {
    private final Lock lock = new ReentrantLock();

    // Spawning separate, independent phone extension signaling lines from the same lock
    private final Condition towelCondition = lock.newCondition();
    private final Condition keyCondition = lock.newCondition();

    private boolean towelsAvailable = false;
    private boolean keysAvailable = false;

    public void waitForTowels() {
        lock.lock();
        try {
            while (!towelsAvailable) {
                System.out.println("⏳ [Trainer A]: Waiting specifically for clean towels...");
                towelCondition.await(); // Sleeps quietly on the towel queue extension line
            }
            System.out.println("🎯 [Trainer A]: Woke up! Towels gathered cleanly.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("🎯 [Trainer A]: finally block.");
            lock.unlock();
        }
    }

    public void waitForKeys() {
        lock.lock();
        try {
            while (!keysAvailable) {
                System.out.println("⏳ [Trainer B]: Waiting specifically for locker keys...");
                keyCondition.await(); // Sleeps quietly on the key queue extension line
            }
            System.out.println("🎯 [Trainer B]: Woke up! Keys secured smoothly.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("🎯 [Trainer B]: finally block.");
            lock.unlock();
        }
    }

    public void supplyTowels() {
        lock.lock();
        try {
            towelsAvailable = true;
            System.out.println("\n🧺 [Helper]: Delivering clean towels. Signaling Towel Extension Line...");
            towelCondition.signal(); // 📱 TARGETED WAKEUP: Wakes Trainer A up. Trainer B remains asleep!
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TargetedConditionBar controlDesk = new TargetedConditionBar();

        Thread trainerA = new Thread(controlDesk::waitForTowels);
        Thread trainerB = new Thread(controlDesk::waitForKeys);

        trainerA.start();
        trainerB.start();

        Thread.sleep(1000); // Allow both threads to settle deep into their separate wait queues

        // Trigger delivery
        controlDesk.supplyTowels();

        trainerA.join();
        // Note: trainerB will remain frozen because no one signaled the key line.
        // This confirms our direct targeted phone signal was completely isolated!
        System.out.println("🏁 Execution check finished.");
        System.exit(0); // Explicitly kill remaining threads to exit cleanly
    }
}

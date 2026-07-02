package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GymGateKeeper {

    private int totalRegisteredMembers = 0;
    // ⚡ Volatile establishes the strict Happens-Before memory visibility boundary
    private volatile boolean isGymOpen = false;
//    private boolean isGymOpen = false; // infinite loop

    // Thread 1 logic: Configures data and opens the gate
    public void configureGymOpening() {
        System.out.println("🏋️‍♂️ [Thread 1]: Parsing and loading 500 member profiles into memory...");
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        this.totalRegisteredMembers = 500;  // Write A (Normal Variable)
        this.isGymOpen = true;              // Write B (Volatile Write)

        System.out.println("🔓 [Thread 1]: Configuration synchronized. The gym gate is now OPEN!");
    }

    // Thread 2 logic: Continuously monitors the gate switch
    public void checkAccess() {
        System.out.println("👀 [Thread 2]: Gatekeeper worker active. Monitoring entrance status...");

        // Spinning until the volatile variable changes to true
        while (!isGymOpen) {
            // Read C (Volatile Read)
        }

        // 🛡️ GUARANTEE: Because Read C saw 'true', Java guarantees that Write A
        // (totalRegisteredMembers = 500) is completely visible to this thread right now.
        System.out.println("🎯 [Thread 2]: Gate open detected! Verifying memory registers...");
        System.out.println("📊 [Thread 2]: Successfully verified " + totalRegisteredMembers + " active records.");
    }

    public static void main(String[] args) throws InterruptedException {
        GymGateKeeper gateKeeper = new GymGateKeeper();

        // Initialize our two parallel workers
        Thread monitorThread = new Thread(gateKeeper::checkAccess);
        Thread setupThread = new Thread(gateKeeper::configureGymOpening);

        monitorThread.start();
        Thread.sleep(200); // Ensure the monitor thread is actively reading first
        setupThread.start();

        // Wait for execution pipelines to conclude safely
        setupThread.join();
        monitorThread.join();
        System.out.println("🏁 Visibility check complete.");
    }
}
package multithreading;

public class LoudspeakerNotificationBar {

    // A single shared object acting as our synchronization monitor lock
    private final Object deskLock = new Object();

    private boolean towelsAvailable = false;
    private boolean keysAvailable = false;

    // 🏋️‍♂️ Trainer A Workflow
    public void waitForTowels() {
        // 🚀 RULE 1: You MUST own the object's monitor lock (synchronized) before calling .wait()
        synchronized (deskLock) {
            // 🚀 RULE 2: Always check your condition inside a 'while' loop, NOT an 'if' statement!
            // This protects your logic from accidental spurious wakeups.
            while (!towelsAvailable) {
                System.out.println("⏳ [Trainer A]: Waiting for clean towels...");
                try {
                    // This thread drops the lock and goes to sleep on the shared deskLock monitor pool
                    deskLock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("🎯 [Trainer A]: Woke up! Collected clean towels.");
        }
    }

    // 🏋️‍♂️ Trainer B Workflow
    public void waitForKeys() {
        synchronized (deskLock) {
            while (!keysAvailable) {
                System.out.println("⏳ [Trainer B]: Waiting for locker keys...");
                try {
                    // Trainer B sleeps in the EXACT SAME wait pool block as Trainer A
                    deskLock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("🎯 [Trainer B]: Woke up! Secured locker keys.");
        }
    }

    // 🧺 Helper Workflow: Delivers Towels
    public void supplyTowels() {
        synchronized (deskLock) {
            towelsAvailable = true;
            System.out.println("\n🧺 [Helper]: Delivering clean towels. Blasting notifyAll() over the megaphone...");

            // 📢 This wakes up EVERY SINGLE THREAD sleeping on deskLock (Both Trainer A and Trainer B)
            deskLock.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LoudspeakerNotificationBar controlDesk = new LoudspeakerNotificationBar();

        Thread threadA = new Thread(controlDesk::waitForTowels);
        Thread threadB = new Thread(controlDesk::waitForKeys);

        threadA.start();
        threadB.start();

        Thread.sleep(1000); // Allow both trainers to settle deep into the wait pool

        // Deliver towels
        controlDesk.supplyTowels();

        // Join threads safely
        threadA.join();

        System.out.println("\n📝 [Main System Info]: Notice that Trainer B woke up, realized no keys were ready, and went back to sleep.");
        System.out.println("🏁 Execution check finished.");
        System.exit(0); // Forcing application termination since Thread B goes back to sleep permanently
    }
}

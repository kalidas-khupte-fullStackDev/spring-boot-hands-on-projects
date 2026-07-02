package multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmoothieKitchenPipeline {

    // Helper method to simulate a time-consuming background kitchen task
    private static void simulateKitchenWork(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.err.println("Task interrupted: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a fixed thread pool to manage our asynchronous kitchen workers
        ExecutorService kitchenPool = Executors.newFixedThreadPool(4);

        System.out.println("🎬 [Main Thread]: Client walks up to the counter and places orders...\n");

        // ==========================================
        // 🧪 USE CASE 1: thenCompose() -> Sequential
        // ==========================================
        System.out.println("⏳ Starting thenCompose() pipeline...");


        CompletableFuture<String> smoothieOrderPipeline = CompletableFuture.supplyAsync(() -> {
                    try {
                        System.out.println("🕵️‍♂️ [Worker 1]: Checking front desk order tickets...");
                        simulateKitchenWork(800); // Simulating time to find the ticket
                        return "Mango"; // Output of Task 1
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }, kitchenPool)
                .thenCompose(flavorOutput -> CompletableFuture.supplyAsync(() -> {
                    // Task 2 consumes flavorOutput directly!
                    System.out.println("Worker 2 Blender]: Received order confirmation for: " + flavorOutput);
                    System.out.println("Worker 2 Blender]: Blending ice, milk, and fresh " + flavorOutput + "...");
                    simulateKitchenWork(1000); // Simulating blending time
                    return flavorOutput + " Smoothie 🥭"; // Final combined result
                }, kitchenPool)).exceptionally(ex -> "Blender broke");


        // ==========================================
        // 🧪 USE CASE 2: thenCombine() -> Parallel
        // ==========================================
        System.out.println("\n⏳ Starting thenCombine() pipeline...");

        // Task A: Prepare fruit base independently
        CompletableFuture<String> berryTask = CompletableFuture.supplyAsync(() -> {
            System.out.println("🍓 [Worker A]: Mashing and blending mixed berries in the back corner...");
            simulateKitchenWork(1200);
            return "Mixed Berry Puree";
        }, kitchenPool);

        // Task B: Prepare protein base independently at the same time
        CompletableFuture<String> proteinTask = CompletableFuture.supplyAsync(() -> {
            System.out.println("🥛 [Worker B]: Whisking premium vanilla protein powder at the side bar...");
            simulateKitchenWork(1000);
            return "Vanilla Protein Shake Base";
        }, kitchenPool);

        // Merge both independent tasks together once BOTH complete
        CompletableFuture<String> proteinBerryComboPipeline = berryTask.thenCombine(proteinTask, (berryResult, proteinResult) -> {
            System.out.println("🔀 [Merger Worker]: Both components are ready! Pouring them into a single luxury cup...");
            return "Premium Shake [" + berryResult + " + " + proteinResult + "] 💪";
        });


        // ==========================================
        // 🏁 PRINTING RESULTS (Non-blocking tracking)
        // ==========================================

        // When the sequential smoothie finishes, print it
        smoothieOrderPipeline.thenAccept(finalSmoothie -> {
            System.out.println("\n🎯 [DELIVERY]: Here is your fresh " + finalSmoothie);
        });

        // When the parallel combo shake finishes, print it
        proteinBerryComboPipeline.thenAccept(finalComboShake -> {
            System.out.println("🎯 [DELIVERY]: Here is your complex " + finalComboShake);
        });

        // Allow background tasks enough time to finish running before shutting down the main thread
        Thread.sleep(4000);

        System.out.println("\n🏁 [Main Thread]: Kitchen shifting down. Closing shop.");
        kitchenPool.shutdown();
    }
}

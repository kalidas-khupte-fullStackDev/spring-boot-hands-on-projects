package core;

import java.util.concurrent.*;

public class WorkerTypeDemo {

    public static void main(String[] args) throws Exception {

        // 1. RUNNABLE: Fire-and-forget task (Java 8 Lambda expression)
        Runnable runnableTask = () -> {
            System.out.println("🏃 Runnable: Cleaning the gym floor... (No result returned)");
            int temp = 20*34;
            System.out.println("Temp val in Runnble:" + temp);
        };

        // 2. CALLABLE: Task that returns a concrete result
        Callable<String> callableTask = () -> {
            System.out.println("📞 Callable: Counting membership files...");
            Thread.sleep(1000); // Simulating time-consuming work
            return "Total Members Found: 150"; // Returning a value!
        };

        // Thread Pool execution (The standard enterprise way to manage threads)
        try(ExecutorService executor = Executors.newSingleThreadExecutor()){
        // Executing Runnable
        executor.execute(runnableTask);

        // Submitting Callable returns a Future receipt
        Future<String> receipt = executor.submit(callableTask);

        // This line blocks and waits until the Callable finishes its job
        System.out.println("🎯 Result from Future: " + receipt.get());

        executor.shutdown();
        }catch (ExecutionException ex){
            Runnable runnable = ex::printStackTrace;
            runnable.run();
        }

    }
}

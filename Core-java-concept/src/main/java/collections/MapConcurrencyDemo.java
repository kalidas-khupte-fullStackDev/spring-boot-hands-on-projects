package collections;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapConcurrencyDemo {
    public static void main(String[] args) {

        // ❌ 1. Regular HashMap: Fail-Fast Behavior
        Map<String, String> standardMap = new HashMap<>();
        standardMap.put("A", "Apple");
        standardMap.put("B", "Banana");

        System.out.println("--- Testing Regular HashMap ---");
        try {
            for (String key : standardMap.keySet()) {
                System.out.println("Reading: " + key);
                // Modifying the map structurally during iteration triggers a crash
                break;
//                standardMap.put("C", "Cherry");
            }
        } catch (Exception e) {
            System.out.println("🛑 Caught expected exception: " + e.toString()); // Throws ConcurrentModificationException
        }

        // 🔄 2. ConcurrentHashMap: Weakly-Consistent Behavior
        Map<String, String> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("A", "Apple");
        concurrentMap.put("B", "Banana");

        System.out.println("\n--- Testing ConcurrentHashMap ---");
        // This loop will finish safely without crashing
        for (String key : concurrentMap.keySet()) {
            System.out.println("Reading: " + key);
            concurrentMap.put("C", "Cherry"); // Safely allowed mid-loop!
        }
        System.out.println("Final Map Size: " + concurrentMap.size()); // Size is now 3
    }
}

package design.pattern;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentSolution {
    public static int[] topKFrequent(int[] nums, int k) {
        // 1. Build a frequency map
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // 2. Keep a Min-Heap based on frequency values
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> countMap.get(a) - countMap.get(b));

        // 3. Maintain only the top 'k' elements in the heap
        for (int num : countMap.keySet()) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll(); // Evict the least frequent element out of the current group
            }
        }

        // 4. Build the output array structure
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = heap.poll();
        }

        return result;
    }

    public static void main(String[] args) {

    }
}

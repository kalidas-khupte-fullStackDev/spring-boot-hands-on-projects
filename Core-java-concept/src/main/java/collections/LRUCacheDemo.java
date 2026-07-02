package collections;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheDemo {

    // 🏎️ A simple LRU Cache extending LinkedHashMap
    static class SimpleLRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int maxCapacity;

        // Constructor
        public SimpleLRUCache(int capacity) {
            // initialCapacity, loadFactor, accessOrder = true
            // accessOrder = true tells Java to track the order of ACCESS, not just insertion!
            super(capacity, 0.75f, true);
            this.maxCapacity = capacity;
        }

        // 🔄 This method is checked by Java after every single put() operation.
        // If it returns true, the map automatically deletes the oldest, least-used entry.
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > maxCapacity; // Evict when we exceed our limit
        }
    }

    public static void main(String[] args) {
        // Create a cache that holds a maximum of 3 items
        SimpleLRUCache<String, String> bookTable = new SimpleLRUCache<>(3);

        bookTable.put("BookA", "Content A");
        bookTable.put("BookB", "Content B");
        bookTable.put("BookC", "Content C");
        System.out.println("📚 Initial Table: " + bookTable.keySet()); // [BookA, BookB, BookC]

        // 🔍 Access BookA. This moves BookA to the "Most Recently Used" end of the list!
        bookTable.get("BookA");
        System.out.println("🔄 After accessing BookA: " + bookTable.keySet()); // [BookB, BookC, BookA]

        // 📥 Add a 4th book. The cache is full, so it must evict the least-used item.
        bookTable.put("BookD", "Content D");

        // BookB was at the front of the list, so it gets dropped!
        System.out.println("❌ After adding BookD (Eviction): " + bookTable.keySet()); // [BookC, BookA, BookD]
    }
}

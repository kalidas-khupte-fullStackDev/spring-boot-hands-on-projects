import java.util.HashMap;
import java.util.Map;

public class AdvancedAnagramChecker {

    public static boolean isAnagramUnicode(String s, String t) {
        if (s == null || t == null) return false;

        Map<Integer, Integer> pool = new HashMap<>();

        // Process string S: Lowercase, filter, and count code points
        s.chars()
                .map(Character::toLowerCase)
                .filter(Character::isLetterOrDigit) // Ignores spaces/punctuation
                .forEach(cp -> pool.put(cp, pool.getOrDefault(cp, 0) + 1));

        // Process string T: Lowercase, filter, and decrement counts
        t.chars()
                .map(Character::toLowerCase)
                .filter(Character::isLetterOrDigit)
                .forEach(cp -> pool.put(cp, pool.getOrDefault(cp, 0) - 1));

        System.out.println("pool:" + pool
        );

        // If the map contains any non-zero value, they aren't anagrams
        for (int count : pool.values()) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        boolean t= isAnagramUnicode("Hello", "Hello");
        System.out.println("Is An:" + t);

    }
}
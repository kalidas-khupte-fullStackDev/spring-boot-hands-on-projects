import java.util.*;

public class GenericsDemo {

    public static void main(String[] args) {
        // 1. Generic Method: Infers type automatically
        String winner = pickRandom("Apple", "Banana");
        System.out.println("Random Winner: " + winner);

        Integer intWinner = pickRandom(120, 456);
        System.out.println("Random intWinner Winner: " + intWinner);

        // 2. Bounded Parameter: Only types that can be compared
        Integer maxNum = findMax(10, 25);
        System.out.println("Max Number: " + maxNum);

        String maxStr = findMax("Kals", "Feros");
        System.out.println("Max Str: " + maxStr);

        // 3. Wildcard: Reading from any list
        List<String> names = List.of("Alice", "Bob");
        List<Integer> ages = List.of(25, 30);

        System.out.println("Printing Names:");
        printList(names);

        System.out.println("Printing Ages:");
        printList(ages);
    }

    // Generic Method: <T> defines the type for this method
    public static <T> T pickRandom(T first, T second) {
        return Math.random() > 0.5 ? first : second;
    }

    // Bounded Parameter: <T extends Comparable<T>> ensures T can be compared
    public static <T extends Comparable<T>> T findMax(T a, T b) {
        return (a.compareTo(b) > 0) ? a : b;
    }

    // Wildcard: List<?> accepts any list, but we can only read from it
    public static void printList(List<?> list) {
        for (Object elem : list) {
            System.out.println(elem);
        }
    }
}
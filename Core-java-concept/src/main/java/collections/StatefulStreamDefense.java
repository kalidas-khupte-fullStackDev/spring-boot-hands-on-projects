package collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StatefulStreamDefense {
    public static void main(String[] args) {
        List<String> rawNames = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // ❌ THE DANGEROUS WAY (Stateful Side Effect):
        // Modifying an external list from inside a stream lambda block.
        // If switched to parallelStream(), this will cause data races or array exceptions!
        List<String> badTargetList = new ArrayList<>();
        rawNames.parallelStream()
//                .filter(name -> name.startsWith("C"))
                .forEach(badTargetList::add); // 🛑 Mutation side effect!
        System.out.println(badTargetList);

        // 👍 THE ARCHITECTURAL GOLD STANDARD (Pure & Stateless):
        // Let the Stream API build, isolate, and safely collect the results for you.
        List<String> pristineList = rawNames.parallelStream()
//                .filter(name -> name.startsWith("C"))
                .toList(); // No external side effects!
        System.out.println(pristineList);
    }
}
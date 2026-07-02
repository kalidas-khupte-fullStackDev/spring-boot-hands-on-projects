package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionMemoryDemo {
    public static void main(String[] args) {

        // 1. Create our original mutable master list
        List<String> originalMasterList = new ArrayList<>();
        originalMasterList.add("Yoga");
        originalMasterList.add("Kickboxing");

        // 🔒 2. Create an UNMODIFIABLE View (The Glass Box)
        List<String> unmodifiableView = new ArrayList<>(Collections.unmodifiableList(originalMasterList));

        // 🗿 3. Create an IMMUTABLE Copy (The Solid Stone) - Java 9+ syntax
//        List<String> immutableCopy = List.copyOf(originalMasterList);
        List<String> immutableCopy = new ArrayList<>(List.copyOf(originalMasterList));

        // ❌ Direct modification attempts will fail on BOTH
         unmodifiableView.add("Zumba"); //-> Throws UnsupportedOperationException
         immutableCopy.add("Zumba");   // -> Throws UnsupportedOperationException

        System.out.println("--- Before Modification ---");
        System.out.println("Unmodifiable View: " + unmodifiableView); // [Yoga, Kickboxing]
        System.out.println("Immutable Copy:    " + immutableCopy);     // [Yoga, Kickboxing]

        // ⚠️ THE TRAP: Modify the original underlying master list reference!
        originalMasterList.add("Zumba");

        System.out.println("\n--- After Modifying Original Master List ---");
        // ⚡ Look closely: The unmodifiable view changed because it points back to the master!
        System.out.println("Unmodifiable View: " + unmodifiableView); // [Yoga, Kickboxing, Zumba]

        // 🛡️ The immutable copy remains completely untouched and safe!
        System.out.println("Immutable Copy:    " + immutableCopy);     // [Yoga, Kickboxing]
    }
}

import java.util.Arrays;

public class AnagramChecker {

    public static boolean isAnagram(String s, String t) {
        // If lengths aren't equal, they can't be anagrams
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        // Using 128 for full ASCII coverage, or 26 if strictly a-z
        int[] charCounts = new int[128];

        // Convert to lowercase to ensure case-insensitivity
        String lowerS = s.toLowerCase();
        String lowerT = t.toLowerCase();

        // Count frequencies
        for (int i = 0; i < lowerS.length(); i++) {
            System.out.println("lowerS.charAt(i): " + Integer.parseInt(String.valueOf(lowerS.charAt(i))));
            System.out.println("charCounts[lowerS.charAt(i)]: " + charCounts[lowerS.charAt(i)]);
            charCounts[lowerS.charAt(i)]++;
            System.out.println("charCounts[lowerT.charAt(i)]: " + charCounts[lowerT.charAt(i)]);
//            System.out.println("lowerT.charAt(i): " + lowerT.charAt(i));
            charCounts[lowerT.charAt(i)]--;
        }

        System.out.println("charCounts: " + Arrays.toString(charCounts));

        // If all counts are 0, it's a perfect anagram
        for (int count : charCounts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("Listen", "Silent")); // true
        System.out.println(isAnagram("Hello", "Bello"));   // false
    }
}
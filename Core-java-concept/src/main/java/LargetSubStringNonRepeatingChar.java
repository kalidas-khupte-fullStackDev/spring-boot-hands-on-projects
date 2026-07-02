import java.util.*;

class LargetSubStringNonRepeatingChar {
    public static int lengthOfLongestSubstring(String s) {
        int start = 0;
        int maxLen = 0;
        // Stores the character and its last seen index
        Map<Character, Integer> hasSeenMap = new HashMap<>();

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            if (hasSeenMap.containsKey(currentChar)) {
                // Move start to the right of the previous occurrence
                start = Math.max(start, hasSeenMap.get(currentChar) + 1);
            }

            hasSeenMap.put(currentChar, end);
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }

    public static void main(String[] a) {
        //int result = lengthOfLongestSubstring("abcddddd");
        int result = lengthOfLongestSubstring("bbbbbb");

        System.out.println(result); // Output: 4 (for "abcd")
    }
}
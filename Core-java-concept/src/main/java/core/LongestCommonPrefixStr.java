package core;

public class LongestCommonPrefixStr {
    public static String longestCommonPrefix(String[] strs) {
        // Handle empty input case
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Start by assuming the first string is the common prefix
        String prefix = strs[0];

        // Compare the prefix with every subsequent string
        for (int i = 1; i < strs.length; i++) {

            // While the current string (strs[i]) does not start with the prefix
            while (strs[i].indexOf(prefix) != 0) {
                // Shorten the prefix by one character from the end
                prefix = prefix.substring(0, prefix.length() - 1);

                // If the prefix becomes empty, there is no common prefix
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
       String result = longestCommonPrefix(new String[]{"flower", "flow", "flight"});
        System.out.println(result);
    }
}

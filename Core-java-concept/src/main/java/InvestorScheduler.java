import java.util.*;

public class InvestorScheduler {

    public static int countMeetings(List<Integer> firstDay, List<Integer> lastDay) {
        int n = firstDay.size();
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;

        // Sort by last day ascending (earliest deadline first)
        // Tie-break: latest first day first (most constrained first)
        Arrays.sort(idx, (a, b) ->
                !lastDay.get(a).equals(lastDay.get(b))
                        ? lastDay.get(a) - lastDay.get(b)
                        : firstDay.get(b) - firstDay.get(a)
        );

        // Use a TreeSet to track all booked days for O(log n) lookup
        TreeSet<Integer> bookedDays = new TreeSet<>();
        int count = 0;

        for (int i : idx) {
            int first = firstDay.get(i);
            int last = lastDay.get(i);

            // Find the latest available day in [first, last] not yet booked
            Integer day = last;
            while (day != null && day >= first && bookedDays.contains(day)) {
//                Integer lower = bookedDays.lower(day);
                Integer lower = bookedDays.lower(day);
                if (lower == null || lower < first) {
                    day = null;
                    break;
                }
                day = (lower - 1 >= first) ? lower - 1 : null;
            }

            if (day != null && day >= first) {
                bookedDays.add(day);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // Test 1: Expected 4
        System.out.println(countMeetings(
                new ArrayList<>(Arrays.asList(1, 2, 3, 3, 3)),
                new ArrayList<>(Arrays.asList(2, 2, 3, 4, 4))
        )); // 4

        // Test 2: Expected 3
        System.out.println(countMeetings(
                new ArrayList<>(Arrays.asList(1, 10, 11)),
                new ArrayList<>(Arrays.asList(11, 10, 11))
        )); // 3
    }
}
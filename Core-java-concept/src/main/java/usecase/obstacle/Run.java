package usecase.obstacle;

import java.util.List;
import java.util.ArrayList;

/**
 * Tracks the absolute performance times recorded for a singular trial run[cite: 4, 23].
 * * @param course        The course target profile [cite: 24]
 * @param obstacleTimes Ordered timing history entries measured in seconds [cite: 25, 26, 28]
 * @param complete      Evaluation flag tracking if all barriers were completed [cite: 24, 25]
 */
public record Run(Course course, List<Integer> obstacleTimes, boolean complete) {

    // Canonical style or secondary constructor to kick off an empty, initial trial run [cite: 26]
    public Run(Course course) {
        this(course, List.of(), false);
    }

    /**
     * Replaces the old mutable method. Returns a brand new Run instance
     * with the appended time segment.
     */
    public Run withAddedTime(int obstacleTime) {
        if (complete) {
            throw new IllegalStateException("Cannot add obstacle to an already complete run[cite: 28].");
        }

        // Create a new expanded copy of the timing history
        List<Integer> newTimes = new ArrayList<>(this.obstacleTimes);
        newTimes.add(obstacleTime);

        // Evaluate completion parameters against the master course layout [cite: 29]
        boolean isNowComplete = newTimes.size() == course.obstacleCount();

        return new Run(this.course, List.copyOf(newTimes), isNowComplete);
    }

    /**
     * Computes cumulative seconds clocked across active legs[cite: 29, 30].
     */
    public int getRunTime() {
        return obstacleTimes.stream().mapToInt(Integer::intValue).sum();
    }
}

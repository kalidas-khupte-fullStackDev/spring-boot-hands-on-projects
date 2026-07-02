package usecase.obstacle;

import java.util.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Historical aggregator container managing execution trials for a distinct course context[cite: 4, 5, 31, 32].
 * * @param course Target route blueprint rules mapped to this record system [cite: 31, 32]
 * @param runs   Historical database array tracking relative operational logs [cite: 31, 33]
 */
public record RunCollection(Course course, List<Run> runs) {

    // Initializer to start an empty collection safely [cite: 31, 33]
    public RunCollection(Course course) {
        this(course, List.of());
    }

    /**
     * Fluent style mutation builder to cleanly register an alternative run run.
     */
    public RunCollection withNewRun(Run run) {
        if (!run.course().equals(this.course)) {
            throw new IllegalArgumentException("Run's Course context does not match the RunCollection[cite: 35].");
        }
        List<Run> updatedRuns = new ArrayList<>(this.runs);
        updatedRuns.add(run);
        return new RunCollection(this.course, List.copyOf(updatedRuns));
    }

    public int getNumRuns() {
        return runs.size(); // [cite: 34]
    }

    /**
     * Identifies the absolute baseline maximum efficiency record set by a user[cite: 36].
     */
    public int personalBest() {
        int lowestRunTime = Integer.MAX_VALUE;
        for(int i =0; i< runs.size(); i++){
            Run currRun = runs.get(i);
            if(currRun.complete()){
                if(currRun.getRunTime() < lowestRunTime){
                    lowestRunTime = currRun.getRunTime();
                }
            }
        }

        return lowestRunTime;

//        return runs.stream()
//                .filter(Run::complete) // [cite: 36]
//                .mapToInt(Run::getRunTime) // [cite: 36]
//                .min()
//                .orElse(Integer.MAX_VALUE); // [cite: 36]
    }

    /**
     * TASK 2: Computes standard target maximum potential if everything went flawlessly[cite: 9, 10].
     * Aggregates lowest segmented components into a hypothetical total[cite: 10].
     */
//    public int bestOfBests() {
//        int totalBestTime = 0;
//
//        for (int i = 0; i < course.obstacleCount(); i++) {
//            final int obstacleIndex = i;
//
//            int minRecordedTime = runs.stream()
//                    .filter(run -> run.obstacleTimes().size() > obstacleIndex)
//                    .mapToInt(run -> run.obstacleTimes().get(obstacleIndex))
//                    .min()
//                    .orElse(Integer.MAX_VALUE);
//
//            if (minRecordedTime != Integer.MAX_VALUE) {
//                totalBestTime += minRecordedTime;
//            }
//        }
//        return totalBestTime;
//    }
//
//    /**
//     * TASK 3: Calculates structural personal best probability distributions[cite: 13, 14].
//     * Uses Monte Carlo random uniform sample evaluation models[cite: 15, 16].
//     */
//    public double chanceOfPersonalBest(Run inProgress) {
//        int currentBest = personalBest();
//        if (currentBest == Integer.MAX_VALUE) {
//            return 1.0;
//        }
//
//        Random rand = new Random();
//        int totalTrials = 10000; // [cite: 16]
//        int successfulTrials = 0;
//
//        // Group past times into target lists keyed by individual segment index for rapid lookups [cite: 16]
//        Map<Integer, List<Integer>> performancePools = new HashMap<>();
//        for (int i = inProgress.obstacleTimes().size(); i < course.obstacleCount(); i++) {
//            final int segmentIndex = i;
//            List<Integer> timeOptions = runs.stream()
//                    .filter(r -> r.obstacleTimes().size() > segmentIndex)
//                    .map(r -> r.obstacleTimes().get(segmentIndex))
//                    .collect(Collectors.toList());
//            performancePools.put(segmentIndex, timeOptions);
//        }
//
//        // Run simulation trials [cite: 16]
//        for (int t = 0; t < totalTrials; t++) {
//            int simulatedRunTime = inProgress.getRunTime();
//            boolean calculationValid = true;
//
//            for (int i = inProgress.obstacleTimes().size(); i < course.obstacleCount(); i++) {
//                List<Integer> options = performancePools.get(i);
//
//                if (options == null || options.isEmpty()) {
//                    calculationValid = false;
//                    break;
//                }
//
//                // Pick a historical time uniformly at random [cite: 15, 16]
//                int simulatedSegmentTime = options.get(rand.nextInt(options.size()));
//                simulatedRunTime += simulatedSegmentTime;
//            }
//
//            // Verify if simulated runtime breaks or ties the personal best record [cite: 14, 16]
//            if (calculationValid && simulatedRunTime <= currentBest) {
//                successfulTrials++;
//            }
//        }
//
//        return (double) successfulTrials / totalTrials; // [cite: 17]
//    }


    public int bestOfBests() {
        int totalBestTime = 0;
        int totalObstacles = course.obstacleCount();

        // Loop through each individual obstacle index (0, 1, 2, 3...) [cite: 37]
        for (int i = 0; i < totalObstacles; i++) {
            int minRecordedTime = Integer.MAX_VALUE;


            // Inner Loop: Check every run to see who did THIS specific obstacle the fastest [cite: 38]
            for (int j = 0; j < runs.size(); j++) {
                Run currentRun = runs.get(j);

                // Make sure this run actually made it far enough to have a time for obstacle 'i' [cite: 38]
                if (currentRun.obstacleTimes().size() > i) {
                    int recordedTime = currentRun.obstacleTimes().get(i);

                    if (recordedTime < minRecordedTime) {
                        minRecordedTime = recordedTime;
                    }
                }
            }

            // If we found a valid time recorded by anyone, add it to our perfect run total [cite: 39]
            if (minRecordedTime != Integer.MAX_VALUE) {
                totalBestTime += minRecordedTime;
            }
        }
        return totalBestTime;
    }

    /**
     * TASK 3: Calculates personal best probability using classic loop structures. [cite: 40]
     */
    public double chanceOfPersonalBest(Run inProgress) {
        int currentBest = personalBest();
        if (currentBest == Integer.MAX_VALUE) {
            return 1.0;
        }

        Random rand = new Random();
        int totalTrials = 10_000;
        int successfulTrials = 0;

        // 1. POPULATE HISTORICAL POOLS USING TRADITIONAL LOOPS
        // We need a pool of times for each upcoming obstacle index
        Map<Integer, List<Integer>> performancePools = new HashMap<>();

        for (int i = inProgress.obstacleTimes().size(); i < course.obstacleCount(); i++) {
            List<Integer> timeOptions = new ArrayList<>();

            // Loop through all history to find matching segment times
            for(int j = 0; j < runs.size(); j++) {
                Run r = runs.get(j);
                if (r.obstacleTimes().size() > i) {
                    timeOptions.add(r.obstacleTimes().get(i));
                }
            }
            performancePools.put(i, timeOptions);
        }

        // 2. SIMULATION TRIALS LOOP [cite: 41]
        for (int t = 0; t < totalTrials; t++) {
            int simulatedRunTime = inProgress.getRunTime();
            boolean calculationValid = true;

            // Simulate times for the remaining incomplete obstacles [cite: 42]
            for (int i = inProgress.obstacleTimes().size(); i < course.obstacleCount(); i++) {
                List<Integer> options = performancePools.get(i);

                if (options == null || options.isEmpty()) {
                    calculationValid = false;
                    break;
                }

                // Pick a historical time uniformly at random [cite: 15, 16]
                int randomIdx = rand.nextInt(options.size());
                int simulatedSegmentTime = options.get(randomIdx);

                simulatedRunTime += simulatedSegmentTime;
            }

            // Verify if simulated runtime breaks or ties the personal best record [cite: 16]
            if (calculationValid && simulatedRunTime <= currentBest) {
                successfulTrials++;
            }
        }

        // Return the final ratio [cite: 17]
        return (double) successfulTrials / totalTrials;
    }
}
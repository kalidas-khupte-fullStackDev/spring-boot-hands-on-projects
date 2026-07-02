package usecase.obstacle;

public class RecordObstacleVerification {

    public static void main(String[] args) {
        // Setup a 4-obstacle course structure
        Course alphaCourse = new Course("Alpha Course", 4);

        // Populate standard test matrix using fluent mutation [cite: 5, 35]
        RunCollection collection = new RunCollection(alphaCourse)
                .withNewRun(new Run(alphaCourse)
                        .withAddedTime(3)
                        .withAddedTime(4)
                        .withAddedTime(5).withAddedTime(6))  // Run 1: 18s
                .withNewRun(new Run(alphaCourse).withAddedTime(4).withAddedTime(4).withAddedTime(4).withAddedTime(5))  // Run 2: 17s
                .withNewRun(new Run(alphaCourse).withAddedTime(4).withAddedTime(5).withAddedTime(4).withAddedTime(6))  // Run 3: 19s
                .withNewRun(new Run(alphaCourse).withAddedTime(5).withAddedTime(5).withAddedTime(3));                  // Run 4: Incomplete

        // 1. Verify Personal Best (Should pick Run 2 = 17 seconds) [cite: 5, 36]
        System.out.println("Personal Best Time: " + collection.personalBest() + " seconds (Expected: 17)");

        // 2. Verify Best of Bests (Min: 3, 4, 3, 5 -> Sums to 15 seconds) [cite: 5, 11]
        int bestOfBestsTime = collection.bestOfBests();
        System.out.println("Best of Bests Value: " + bestOfBestsTime + " seconds (Expected: 15)");

        // 3. Test Simulation Framework with an In-Progress Run
        Run currentAttempt = new Run(alphaCourse).withAddedTime(3); // Start with a fast leg
        double probability = collection.chanceOfPersonalBest(currentAttempt);
        System.out.printf("Probability of securing a Personal Best: %.2f%%\n", probability * 100);
    }
}



//    // Verifies the 15-second total constraint detailed in your documentation
//    public static void testBestOfBestsStandardExample() {
//        Course course = new Course("Alpha Course", 4);
//        RunCollection collection = new RunCollection(course);
//
//        // Run 1: 3 4 5 6 (18s)
//        Run r1 = new Run(course);
//        r1.addObstacleTime(3); r1.addObstacleTime(4); r1.addObstacleTime(5); r1.addObstacleTime(6);
//
//        // Run 2: 4 4 4 5 (17s)
//        Run r2 = new Run(course);
//        r2.addObstacleTime(4); r2.addObstacleTime(4); r2.addObstacleTime(4); r2.addObstacleTime(5);
//
//        // Run 3: 4 5 4 6 (19s)
//        Run r3 = new Run(course);
//        r3.addObstacleTime(4); r3.addObstacleTime(5); r3.addObstacleTime(4); r3.addObstacleTime(6);
//
//        // Run 4: 5 5 3 (Incomplete)
//        Run r4 = new Run(course);
//        r4.addObstacleTime(5); r4.addObstacleTime(5); r4.addObstacleTime(3);
//
//        collection.addRun(r1);
//        collection.addRun(r2);
//        collection.addRun(r3);
//        collection.addRun(r4);
//
//        int bestOfBests = collection.bestOfBests();
//        assert bestOfBests == 15 : "Expected 15, but got: " + bestOfBests;
//        System.out.println("testBestOfBestsStandardExample: PASSED (Result: " + bestOfBests + ")");
//    }
//
//    public static void testChanceOfPersonalBestSimulation() {
//        Course course = new Course("Beta Course", 2);
//        RunCollection collection = new RunCollection(course);
//
//        // Establishing Personal Best = 10
//        Run historical1 = new Run(course);
//        historical1.addObstacleTime(5); historical1.addObstacleTime(5); // 10s
//
//        Run historical2 = new Run(course);
//        historical2.addObstacleTime(4); historical2.addObstacleTime(8); // 12s
//
//        collection.addRun(historical1);
//        collection.addRun(historical2);
//
//        // In progress run has completed segment 1 in 5 seconds.
//        // Remaining options for segment 2 pool: [5, 8].
//        // To tie/beat 10s, it must hit 5s (1 out of 2 options -> 50% chance).
//        Run inProgress = new Run(course);
//        inProgress.addObstacleTime(5);
//
//        double probability = collection.chanceOfPersonalBest(inProgress);
//
//        // Target precision boundary verification (+/- 0.02)
//        assert probability >= 0.48 && probability <= 0.52 : "Expected ~0.50, but got: " + probability;
//        System.out.println("testChanceOfPersonalBestSimulation: PASSED (Probability: " + probability + ")");
//    }
//
//    public static void testEdgeCaseNoPersonalBest() {
//        Course course = new Course("Gamma Course", 3);
//        RunCollection collection = new RunCollection(course);
//
//        // Incomplete run in historical records means personalBest() evaluates to MAX_VALUE
//        Run historicalIncomplete = new Run(course);
//        historicalIncomplete.addObstacleTime(4);
//        collection.addRun(historicalIncomplete);
//
//        Run inProgress = new Run(course);
//        inProgress.addObstacleTime(3);
//
//        double probability = collection.chanceOfPersonalBest(inProgress);
//        assert probability == 1.0 : "Expected 1.0 chance when no historical benchmark exists, got: " + probability;
//        System.out.println("testEdgeCaseNoPersonalBest: PASSED (Probability: " + probability + ")");
//    }
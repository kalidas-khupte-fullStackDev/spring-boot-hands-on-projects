package usecase.obstacle;

/**
 * Represents a specific obstacle course[cite: 4, 20].
 * Automatically generates clean structural equality and hashcode checks[cite: 19, 23].
 * * @param title         The name of the obstacle course [cite: 20]
 * @param obstacleCount The number of individual segments/obstacles in the course [cite: 20, 21]
 */
public record Course(String title, int obstacleCount) {

    // Compact constructor for input validation
    public Course {
        if (obstacleCount <= 0) {
            throw new IllegalArgumentException("Obstacle count must be greater than zero.");
        }
    }
}
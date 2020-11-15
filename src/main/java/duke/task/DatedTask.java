package duke.task;

import java.time.LocalDateTime;

/**
 * Task must implement these interface to make {@link LocalDateTime} comparable by {@link Comparable}.
 */
public interface DatedTask {

    /**
     * Implement getter to let {@link Comparable} know which {@link LocalDateTime} attribute
     * to use for date and time comparison.
     * @return local date time attribute to compare
     */
    LocalDateTime getComparableDateTime();
}

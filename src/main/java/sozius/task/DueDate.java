package sozius.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * DueDate class represents a date with an optional time for DeadlineTask and EventTask
 */
public class DueDate {
    private static final DateTimeFormatter USER_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter FILE_DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");

    private final LocalDate date;
    private final LocalTime time;

    /**
     * Creates a DueDate.
     * @param date Date
     * @param time Time
     */
    public DueDate(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    /**
     * Creates a DueDate from a string.
     * A DueDate has a date in yyyy-MM-dd format
     * and an optional time in HHmm format.
     * @param args the string
     * @return the DueDate
     */
    public static DueDate parse(String args) {
        String[] splitArgs = args.trim().split("\\s+");
        LocalDate date = LocalDate.parse(splitArgs[0], FILE_DATE_FORMAT);
        LocalTime time = splitArgs.length == 1
                ? null
                : LocalTime.parse(splitArgs[1], TIME_FORMAT);
        return new DueDate(date, time);
    }

    /**
     * Returns date of a DueDate.
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }
    /**
     * Returns time of a DueDate, or null if the DueDate doesn't have one.
     * @return the time or null.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns string representation of the DueDate.
     * For displaying to a user.
     * Date represented as MMM d yyyy
     * Time represented as HHmm
     * @return the string
     */
    public String toUserString() {
        return time == null
                ? date.format(USER_DATE_FORMAT)
                : date.format(USER_DATE_FORMAT) + " " + time.format(TIME_FORMAT);
    }

    /**
     * Returns string representation of the DueDate.
     * For writing to file.
     * Date represented as yyyy-mm-dd
     * Time represented as HHmm
     * @return the string
     */
    public String toFileString() {
        return time == null
                ? date.format(FILE_DATE_FORMAT)
                : date.format(FILE_DATE_FORMAT) + " " + time.format(TIME_FORMAT);
    }
}

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DueDate {
    private LocalDate date;
    private LocalTime time;

    public DueDate(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    public String toUserString() {
        return time == null
                ?  date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                        " " +
                        time.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    public String toFileString() {
        return time == null
                ?  date.format(DateTimeFormatter.ISO_LOCAL_DATE)
                : date.format(DateTimeFormatter.ISO_LOCAL_DATE) +
                " " +
                time.format(DateTimeFormatter.ofPattern("HHmm"));
    }
}

package Sozius.task;

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

    public static DueDate parse(String args) {
        String[] splitArgs = args.split(" ");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalDate date = LocalDate.parse(splitArgs[0], dateFormatter);
        LocalTime time = splitArgs.length == 1
                ? null
                : LocalTime.parse(splitArgs[1], timeFormatter);
        return new DueDate(date, time);
    }

    public LocalDate getDate() {
        return date;
    }
    public LocalTime getTime() {
        return time;
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

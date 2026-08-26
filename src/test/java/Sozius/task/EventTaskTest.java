package Sozius.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {
    private static DueDate date = new DueDate(
            LocalDate.of(2026, 8, 30),
            null
    );
    private static DueDate date2 = new DueDate(
            LocalDate.of(2026, 12, 25),
            null
    );
    private static DueDate dateWithTime = new DueDate(
            LocalDate.of(2026, 8, 30),
            LocalTime.of(14, 0)
    );
    private static DueDate dateWithTime2 = new DueDate(
            LocalDate.of(2026, 12, 25),
            LocalTime.of(17, 30)
    );

    @Test
    public void toFileString_dateWithoutTime_returnsCorrectFormat() {
        EventTask task = new EventTask("Y2S1", date, date2);

        assertEquals(
                "E | 0 | Y2S1 | 2026-08-30/2026-12-25",
                task.toFileString()
        );
    }
    @Test
    public void toFileString_dateWithTime_returnsCorrectFormat() {
        EventTask task = new EventTask("Y2S1", dateWithTime, dateWithTime2);

        assertEquals(
                "E | 0 | Y2S1 | 2026-08-30 1400/2026-12-25 1730",
                task.toFileString()
        );
    }
    @Test
    public void toFileString_completedTask_returnsCorrectFormat() {
        EventTask task = new EventTask("Y2S1", date, date2);
        task.setDone(true);

        assertEquals(
                "E | 1 | Y2S1 | 2026-08-30/2026-12-25",
                task.toFileString()
        );
    }

    @Test
    public void toUserString_dateWithoutTime_returnsCorrectFormat() {
        EventTask task = new EventTask("Y2S1", date, date2);

        assertEquals(
                "[E][ ] Y2S1 (from: Aug 30 2026 to: Dec 25 2026)",
                task.toUserString()
        );
    }
    @Test
    public void toUserString_dateWithTime_returnsCorrectFormat() {
        EventTask task = new EventTask("Y2S1", dateWithTime, dateWithTime2);

        assertEquals(
                "[E][ ] Y2S1 (from: Aug 30 2026 1400 to: Dec 25 2026 1730)",
                task.toUserString()
        );
    }
    @Test
    public void toUserString_completedTask_returnsCorrectFormat() {
        EventTask task = new EventTask("Y2S1", date, date2);
        task.setDone(true);

        assertEquals(
                "[E][X] Y2S1 (from: Aug 30 2026 to: Dec 25 2026)",
                task.toUserString()
        );
    }
}

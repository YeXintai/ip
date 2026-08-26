package Sozius.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {
    private static DueDate date = new DueDate(
            LocalDate.of(2026, 8, 30),
            null
    );
    private static DueDate dateWithTime = new DueDate(
            LocalDate.of(2026, 8, 30),
            LocalTime.of(14, 0)
    );

    @Test
    public void toFileString_dateWithoutTime_returnsCorrectFormat() {
        DeadlineTask task = new DeadlineTask("submit report", date);

        assertEquals(
                "D | 0 | submit report | 2026-08-30",
                task.toFileString()
        );
    }
    @Test
    public void toFileString_dateWithTime_returnsCorrectFormat() {
        DeadlineTask task = new DeadlineTask("submit report", dateWithTime);

        assertEquals(
                "D | 0 | submit report | 2026-08-30 1400",
                task.toFileString()
        );
    }
    @Test
    public void toFileString_completedTask_returnsCorrectFormat() {
        DeadlineTask task = new DeadlineTask("submit report", date);
        task.setDone(true);

        assertEquals(
                "D | 1 | submit report | 2026-08-30",
                task.toFileString()
        );
    }

    @Test
    public void toUserString_dateWithoutTime_returnsCorrectFormat() {
        DeadlineTask task = new DeadlineTask("submit report", date);

        assertEquals(
                "[D][ ] submit report (by: Aug 30 2026)",
                task.toUserString()
        );
    }
    @Test
    public void toUserString_dateWithTime_returnsCorrectFormat() {
        DeadlineTask task = new DeadlineTask("submit report", dateWithTime);

        assertEquals(
                "[D][ ] submit report (by: Aug 30 2026 1400)",
                task.toUserString()
        );
    }
    @Test
    public void toUserString_completedTask_returnsCorrectFormat() {
        DeadlineTask task = new DeadlineTask("submit report", date);
        task.setDone(true);

        assertEquals(
                "[D][X] submit report (by: Aug 30 2026)",
                task.toUserString()
        );
    }
}

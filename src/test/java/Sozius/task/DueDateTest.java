package Sozius.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DueDateTest {
    @Test
    public void parse_dateWithoutTime() {
        DueDate result = DueDate.parse("2026-08-30");

        assertEquals(
                "2026-08-30",
                result.toFileString()
        );
    }
    @Test
    public void parse_dateWithTime() {
        DueDate result = DueDate.parse("2026-08-30 1430");

        assertEquals(
                "2026-08-30 1430",
                result.toFileString()
        );
    }
}

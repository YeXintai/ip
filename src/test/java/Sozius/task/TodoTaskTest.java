package Sozius.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {
    @Test
    public void toFileString_incompleteTask() {
        assertEquals(
                "T | 0 | do homework",
                new TodoTask("do homework").toFileString()
        );
    }
    @Test
    public void toFileString_completedTask() {
        TodoTask task = new TodoTask("read book");
        task.setDone(true);

        assertEquals("T | 1 | read book", task.toFileString());
    }
    @Test
    public void toUserString_incompleteTask() {
        assertEquals(
                "[T][ ] do homework",
                new TodoTask("do homework").toUserString()
        );
    }
    @Test
    public void toUserString_completedTask() {
        TodoTask task = new TodoTask("read book");
        task.setDone(true);

        assertEquals("[T][X] read book", task.toUserString());
    }
}

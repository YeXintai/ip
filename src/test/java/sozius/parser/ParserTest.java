package sozius.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sozius.task.DeadlineTask;
import sozius.task.EventTask;
import sozius.task.TodoTask;
import sozius.tasklist.TaskList;
import sozius.ui.Ui;

class ParserTest {

    private TaskList tasks;
    private Parser parser;
    private ByteArrayOutputStream output;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        Ui ui = new Ui();
        parser = new Parser(ui, tasks);

        // Capture System.out so we can test printed messages
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test
    void parseTodo_shouldAddTodoTask() {
        parser.parse("todo read a book");

        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof TodoTask);
        assertEquals("read a book", tasks.get(0).getDescription());
    }

    @Test
    void parseDeadline_shouldAddDeadlineTask() {
        parser.parse("deadline submit assignment /by 2026-12-25");

        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof DeadlineTask);
        assertEquals("submit assignment", tasks.get(0).getDescription());
    }

    @Test
    void parseEvent_shouldAddEventTask() {
        parser.parse("event meeting /from 2026-12-25 /to 2026-12-26");

        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof EventTask);
        assertEquals("meeting", tasks.get(0).getDescription());
    }

    @Test
    void parseMark_shouldMarkTaskAsDone() {
        parser.parse("todo finish homework");

        parser.parse("mark 1");

        assertTrue(tasks.get(0).isDone());
    }

    @Test
    void parseUnmark_shouldMarkTaskAsNotDone() {
        parser.parse("todo finish homework");
        parser.parse("mark 1");

        parser.parse("unmark 1");

        assertFalse(tasks.get(0).isDone());
    }

    @Test
    void parseDelete_shouldRemoveTask() {
        parser.parse("todo finish homework");

        parser.parse("delete 1");

        assertEquals(0, tasks.size());
    }

    @Test
    void parseList_shouldPrintAllTasks() {
        parser.parse("todo first task");
        parser.parse("todo second task");

        output.reset();

        parser.parse("list");

        String result = output.toString();

        assertTrue(result.contains("1."));
        assertTrue(result.contains("2."));
        assertTrue(result.contains("first task"));
        assertTrue(result.contains("second task"));
    }

    @Test
    void parseUnknownCommand_shouldPrintError() {
        parser.parse("hello");

        assertTrue(output.toString().contains("Error: unknown command"));
    }

    @Test
    void parseMarkWithInvalidIndex_shouldPrintError() {
        parser.parse("todo test");

        output.reset();

        parser.parse("mark 5");

        assertTrue(output.toString().contains("Invalid command: Invalid index"));
        assertFalse(tasks.get(0).isDone());
    }

    @Test
    void parseMarkWithNonInteger_shouldPrintError() {
        parser.parse("todo test");

        output.reset();

        parser.parse("mark abc");

        assertTrue(output.toString().contains(
                "Invalid command: Index must be integer"
        ));
    }

    @Test
    void parseDeleteWithInvalidIndex_shouldNotDeleteTask() {
        parser.parse("todo test");

        output.reset();

        parser.parse("delete 5");

        assertEquals(1, tasks.size());
        assertTrue(output.toString().contains(
                "Invalid command: Invalid index"
        ));
    }

    @Test
    void parseTodoWithEmptyDescription_shouldNotAddTask() {
        parser.parse("todo ");

        assertEquals(0, tasks.size());
        assertTrue(output.toString().contains(
                "Invalid command: incorrect number of arguments for todo"
        ));
    }
}

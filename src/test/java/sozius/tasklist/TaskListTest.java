package sozius.tasklist;

import sozius.task.Task;
import sozius.task.TodoTask;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskListTest {

    @Test
    void constructor_createsEmptyTaskList() {
        TaskList taskList = new TaskList();

        assertEquals(0, taskList.size());
    }
    @Test
    void constructor_withExistingList_containsTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task = new TodoTask("read book");
        tasks.add(task);

        TaskList taskList = new TaskList(tasks);

        assertEquals(1, taskList.size());
        assertSame(task, taskList.get(0));
    }
    @Test
    void add_addsTaskToList() {
        TaskList taskList = new TaskList();
        Task task = new TodoTask("read book");

        taskList.add(task);

        assertEquals(1, taskList.size());
        assertSame(task, taskList.get(0));
    }
    @Test
    void get_returnsTaskAtIndex() {
        TaskList taskList = new TaskList();
        Task task1 = new TodoTask("read book");
        Task task2 = new TodoTask("buy milk");

        taskList.add(task1);
        taskList.add(task2);

        assertSame(task1, taskList.get(0));
        assertSame(task2, taskList.get(1));
    }
    @Test
    void remove_removesAndReturnsTask() {
        TaskList taskList = new TaskList();
        Task task1 = new TodoTask("read book");
        Task task2 = new TodoTask("buy milk");

        taskList.add(task1);
        taskList.add(task2);

        Task removed = taskList.remove(0);

        assertSame(task1, removed);
        assertEquals(1, taskList.size());
        assertSame(task2, taskList.get(0));
    }
    @Test
    void size_returnsNumberOfTasks() {
        TaskList taskList = new TaskList();

        assertEquals(0, taskList.size());

        taskList.add(new TodoTask("read book"));
        assertEquals(1, taskList.size());

        taskList.add(new TodoTask("buy milk"));
        assertEquals(2, taskList.size());
    }
    @Test
    void markTask_marksTaskAsDone() {
        TaskList taskList = new TaskList();
        Task task = new TodoTask("read book");
        taskList.add(task);

        taskList.markTask(0);

        assertTrue(task.getDone());
    }
    @Test
    void unmarkTask_marksTaskAsNotDone() {
        TaskList taskList = new TaskList();
        Task task = new TodoTask("read book");
        task.setDone(true);
        taskList.add(task);

        taskList.unmarkTask(0);

        assertFalse(task.getDone());
    }
    @Test
    void getLast_returnsLastTask() {
        TaskList taskList = new TaskList();
        Task task1 = new TodoTask("read book");
        Task task2 = new TodoTask("buy milk");

        taskList.add(task1);
        taskList.add(task2);

        assertSame(task2, taskList.getLast());
    }
    @Test
    void iterator_iteratesThroughAllTasks() {
        TaskList taskList = new TaskList();
        Task task1 = new TodoTask("read book");
        Task task2 = new TodoTask("buy milk");

        taskList.add(task1);
        taskList.add(task2);

        ArrayList<Task> result = new ArrayList<>();

        for (Task task : taskList) {
            result.add(task);
        }

        assertEquals(2, result.size());
        assertSame(task1, result.get(0));
        assertSame(task2, result.get(1));
    }
    @Test
    void get_invalidIndex_throwsException() {
        TaskList taskList = new TaskList();

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> taskList.get(0)
        );
    }
    @Test
    void remove_invalidIndex_throwsException() {
        TaskList taskList = new TaskList();

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> taskList.remove(0)
        );
    }
}

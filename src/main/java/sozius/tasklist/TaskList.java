package sozius.tasklist;

import sozius.task.Task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a collection of tasks that can be managed, modified,
 * and iterated over.
 */
public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList(){
        tasks = new ArrayList<>();
    }
    /**
     * Constructs a TaskList initialized with the specified list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task the task to be added
     */
    public void add(Task task){
        tasks.add(task);
    }
    /**
     * Removes and returns the task at the specified index.
     *
     * @param index the index of the task to remove
     * @return the task that was removed
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }
    /**
     * Returns the task at the specified index without removing it.
     *
     * @param index the index of the task to return
     * @return the task at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task get(int index){
        return tasks.get(index);
    }
    /**
     * Returns the number of tasks currently in the list.
     *
     * @return the number of tasks
     */
    public int size(){
        return tasks.size();
    }
    /**
     * Marks the task at the specified index as completed.
     *
     * @param index the index of the task to mark as done
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void markTask(int index){
        tasks.get(index).setDone(true);
    }
    /**
     * Marks the task at the specified index as incomplete.
     *
     * @param index the index of the task to mark as undone
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void unmarkTask(int index){
        tasks.get(index).setDone(false);
    }
    /**
     * Returns the last task in the list.
     *
     * @return the last task
     * @throws java.util.NoSuchElementException if the task list is empty
     */
    public Task getLast(){
        return tasks.getLast();
    }

    /**
     * Returns an iterator over the elements in this task list.
     *
     * @return an Iterator over the tasks
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}

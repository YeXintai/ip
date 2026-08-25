import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public void add(Task task){
        tasks.add(task);
    }
    public Task remove(int index) {
        return tasks.remove(index);
    }
    public Task get(int index){
        return tasks.get(index);
    }
    public int size(){
        return tasks.size();
    }
    public void markTask(int index){
        tasks.get(index).setDone(true);
    }
    public void unmarkTask(int index){
        tasks.get(index).setDone(false);
    }
    public Task getLast(){
        return tasks.getLast();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}

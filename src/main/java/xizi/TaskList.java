package xizi;

import java.util.ArrayList;
import java.util.List;
// ArrayList implementation, auto-adjust the indexing after deletion
public class TaskList {

    private List<Task> items;
    private int size;

    TaskList(){
        this.items = new ArrayList<>();
        this.size = 0;
    }

    TaskList(List<Task> tasks){
        this.items = tasks;
        this.size = tasks.size();
    }

    public void addTask(Task newTask) {
        this.items.add(newTask);
        this.size += 1;
    }

    public int getSize(){
        return this.size;
    }

    public List<Task> getItems(){
        return this.items;
    }

    public Task deleteTask(int index) {
        Task deleted = this.items.get(index);
        this.items.remove(index);
        this.size = this.size - 1;
        return deleted;
    }


    public String markTask(int index){
        Task task = this.items.get(index);
        task.markDone();
        return task.toString();
    }

    public String unmarkTask(int index){
        Task task = this.items.get(index);
        task.unmark();
        return task.toString();
    }



    public void printActions() {
        for (int i = 1; i <= this.size; i++){
            System.out.printf("%,d. %s%n", i ,this.items.get(i-1).toString());
        }
        if (this.size == 0) {
            System.out.println("There are no tasks in the list now");
        }
    }




}

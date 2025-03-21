package org.alepaucar.tasktracker.repositories;

import org.alepaucar.tasktracker.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task){
        tasks.add(task);
    }

    public List<Task> getAllTasks(){
        return  tasks;
    }

    public Task getTaskById(long id){
        return tasks.stream().filter(task -> task.getId() == id).findFirst().orElse(null);
    }



    public void removeTask(long id){
        tasks.removeIf(task -> task.getId()==id);
    }
}

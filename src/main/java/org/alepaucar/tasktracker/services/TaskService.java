package org.alepaucar.tasktracker.services;

import org.alepaucar.tasktracker.models.Status;
import org.alepaucar.tasktracker.models.Task;
import org.alepaucar.tasktracker.repositories.TaskRepository;

import java.util.List;

public class TaskService {
    private TaskRepository repository ;
    private long nextId;

    public TaskService() {
        this.repository = new TaskRepository();
        this.nextId = repository.getAllTasks().stream() // Convertimos la lista de tareas en un flujo (stream)
                .mapToLong(Task::getId)  // Extraemos los valores de ID como tipo `long`
                .max()                   // Buscamos el ID más alto
                .orElse(-1) + 1;
    }

    public Task addTask (String description){
        Task newTask = new Task(nextId++,description, Status.TODO);
        repository.addTask(newTask);
        return newTask;
    }

    public List<Task> getAllTasks(){
        return  repository.getAllTasks();
    }

    public Task getTaskById(long id){
        return repository.getTaskById(id);
    }

    public void removeTask(long id){
        repository.removeTask(id);
    }

    public void editTask(long id, String newDescription) {
        Task task = repository.getTaskById(id);
        if (task != null) {
            task.setDescription(newDescription);
        } else {
            throw new IllegalArgumentException("Task not found");
        }
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}

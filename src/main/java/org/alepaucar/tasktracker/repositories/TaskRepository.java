package org.alepaucar.tasktracker.repositories;

import org.alepaucar.tasktracker.models.Task;
import org.alepaucar.tasktracker.utils.JsonMapperUtil;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private final TaskFileRepository jsonRepo;
    private List<Task> tasks;

    public TaskRepository() {
        this.jsonRepo = new TaskFileRepository();
        loadTasksFromFile();
    }

    private void loadTasksFromFile() {
        try {
            String fileContent = jsonRepo.readFile();
            this.tasks = (fileContent.isEmpty() || fileContent.equals("[]"))
                    ? new ArrayList<>()
                    : JsonMapperUtil.jsonToTaskList(fileContent);
        } catch (Exception e) {
            System.out.println("Error reading file, initializing empty task list");
            e.printStackTrace();
            this.tasks = new ArrayList<>();
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        syncWithFile();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(long id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean removeTask(long id) {
        boolean removed = tasks.removeIf(task -> task.getId() == id);
        if (removed) {
            syncWithFile();
        }
        return removed;
    }

    private void syncWithFile() {
        try {
            jsonRepo.editFile(JsonMapperUtil.taskListToJson(tasks));
        } catch (Exception e) {
            System.out.println("Error updating repository from file: " + e.getMessage());
        }
    }

    public void refreshFromFile() {
        loadTasksFromFile();
    }

    public void deleteAll() {
        jsonRepo.deleteFile();
        loadTasksFromFile();
        syncWithFile();
    }
}

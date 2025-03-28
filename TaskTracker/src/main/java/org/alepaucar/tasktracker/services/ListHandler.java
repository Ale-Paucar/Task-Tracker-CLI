package org.alepaucar.tasktracker.services;

import org.alepaucar.tasktracker.models.Status;
import org.alepaucar.tasktracker.models.Task;

import java.util.List;

public class ListHandler {
    private final TaskService service;

    public ListHandler(TaskService service) {
        this.service = service;
    }

    public void handleListCommand(String[] args) {
        if (args.length == 0) {
            printAllTasks();
        } else {
            Status status = parseStatus(args[0]);
            if (status == null) {
                System.out.println("Invalid status. Use: todo, in-progress, or done.");
            } else {
                printTasksByStatus(status);
            }
        }
    }

    private Status parseStatus(String inputStatus) {
        return switch (inputStatus.trim().toLowerCase()) {
            case "done" -> Status.DONE;
            case "in-progress" -> Status.IN_PROGRESS;
            case "todo" -> Status.TODO;
            default -> null;
        };
    }

    private void printTasksByStatus(Status status) {
        List<Task> filtered = service.getAllTasks().stream()
                .filter(task -> task.getStatus() == status)
                .toList();

        if (filtered.isEmpty()) {
            System.out.println("No tasks with status: " + status.name().toLowerCase());
        } else {
            printTasks(filtered);
        }
    }

    private void printTasks(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println(task.getId() + ": " + task.getDescription() + " - " + task.getStatus());
        }
    }

    private void printAllTasks() {
        List<Task> tasks = service.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            printTasks(tasks);
        }
    }
}

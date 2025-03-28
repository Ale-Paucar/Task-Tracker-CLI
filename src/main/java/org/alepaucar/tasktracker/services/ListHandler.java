package org.alepaucar.tasktracker.services;

import org.alepaucar.tasktracker.models.Status;
import org.alepaucar.tasktracker.models.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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
        if (tasks.isEmpty()) {
            System.out.println("📭 There is no tasks to show.");
            return;
        }

        System.out.println("📋 Lista de Tareas:");
        System.out.println("=".repeat(80));
        System.out.printf("%-5s | %-40s | %-14s | %-19s%n", "ID", "Description", "Status", "Last update");
        System.out.println("-".repeat(80));

        for (Task task : tasks) {

            System.out.printf(
                    "%-5d | %-40s | %-10s | %-19s%n",
                    task.getId(),
                    task.getDescription(),
                    formatStatus(task.getStatus()),
                    dateFormatted(task.getUpdatedAt())
            );
        }

        System.out.println("=".repeat(80));
    }


    private String dateFormatted(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy hh:mma", Locale.ENGLISH);
        return dateTime.format(formatter).toLowerCase();
    }

    private String formatStatus(Status status) {
        return switch (status) {
            case TODO -> "⏳ TO DO   ";
            case IN_PROGRESS -> "🚧 IN PROGRESS";
            case DONE -> "✅ DONE   ";
        };
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

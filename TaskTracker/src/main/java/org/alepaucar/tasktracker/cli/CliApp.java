package org.alepaucar.tasktracker.cli;


import org.alepaucar.tasktracker.models.Task;
import org.alepaucar.tasktracker.services.ListHandler;
import org.alepaucar.tasktracker.services.TaskHandler;
import org.alepaucar.tasktracker.services.TaskService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CliApp {
    private final TaskService service;
    private final Scanner scanner;

    public CliApp() {
        this.service = new TaskService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("------------------------");
        System.out.println("------TASK TRACKER------");

        while (true) {
            printMainMenu();
            String userInput = scanner.nextLine().trim();
            String[] inputArgs = userInput.split("\\s+", 2);

            switch (inputArgs[0].toLowerCase()) {
                case "exit":
                    System.out.println("Thanks for visiting the task tracker CLI project.");
                    scanner.close();
                    return;
                case "add":
                    handleAdd(inputArgs);
                    break;
                case "list":
                    handleList();
                    break;
                default:
                    System.out.println("Unknown command: '" + inputArgs[0] + "'.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n> Please enter a command (add/list/exit):");
        System.out.println("  add <new task>           - Create a new task");
        System.out.println("  list                     - Show all tasks");
        System.out.println("  exit                     - Terminate the program");
        System.out.print("\n> ");
    }

    private void handleAdd(String[] inputArgs) {
        if (inputArgs.length > 1 && !inputArgs[1].isBlank()) {
            Task newTask = service.addTask(inputArgs[1].trim());
            System.out.println("Task created successfully! (ID: " + newTask.getId() + ")");
        } else {
            System.out.println("A description is missing.");
        }
    }

    private void handleList() {
        List<Task> allTasks = service.getAllTasks();
        if (allTasks.isEmpty()) {
            System.out.println("There are no tasks in progress.");
        } else {
            ListHandler listHandler1 = new ListHandler(service);
            listHandler1.handleListCommand(new String[0]);
            handleSubmenu();
        }
    }

    private void handleSubmenu() {
        while (true) {
            printSubmenu();
            String subMenuInput = scanner.nextLine().trim();
            String[] submenuArgs = subMenuInput.split("\\s+", 3);

            String command = submenuArgs[0].toLowerCase();

            switch (command) {
                case "back":
                    return;
                case "edit":
                    if (submenuArgs.length < 3) {
                        System.out.println("Please enter both a valid ID and a new task description.");
                    } else {
                        new TaskHandler(submenuArgs[1], service).handleEditTask(submenuArgs[2]);
                    }
                    break;
                case "delete":
                    if (submenuArgs.length == 2) {
                        new TaskHandler(submenuArgs[1], service).handleRemoveTask();
                    } else {
                        System.out.println("Please enter a valid ID.");
                    }
                    break;
                case "mark-done":
                case "mark-in-progress":
                    if (submenuArgs.length == 2) {
                        new TaskHandler(submenuArgs[1], service).handleEditStatus(command);
                    } else {
                        System.out.println("Please enter a valid ID.");
                    }
                    break;
                case "list":
                    new ListHandler(service).handleListCommand(Arrays.copyOfRange(submenuArgs, 1, submenuArgs.length));
                    break;
                default:
                    System.out.println("Unknown command: '" + command + "'.");
            }
        }
    }

    private void printSubmenu() {
        System.out.println("\n> What do you want to do?");
        System.out.println("  edit <id> <new task>     - Edit an existing task");
        System.out.println("  delete <id>              - Delete a task");
        System.out.println("  mark-done <id>           - Mark a task as done");
        System.out.println("  mark-in-progress <id>    - Mark a task as in progress");
        System.out.println("  list                     - Show all tasks");
        System.out.println("  list <status>            - Show tasks by status (todo, done, in-progress)");
        System.out.println("  back                     - Return to the main menu");
        System.out.print("\n> ");
    }
}

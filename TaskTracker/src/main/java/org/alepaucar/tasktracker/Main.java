package org.alepaucar.tasktracker;

import org.alepaucar.tasktracker.models.Task;
import org.alepaucar.tasktracker.services.TaskService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskService service = new TaskService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------");
        System.out.println("------TASK TRACKER------");
        while(true){
            System.out.println("Please enter a comand (add/list/exit):");
            String command = scanner.nextLine().trim();
            if(command.equalsIgnoreCase("exit")){
                System.out.println("Thank for your visit to the task tracker cli project");
                break;
            }else if(command.equalsIgnoreCase("add")){
                System.out.println("Please enter the task descrption:");
                String description = scanner.nextLine().trim();
                Task newTask = service.addTask(description);
                System.out.println("Task created successfully! (ID: " + newTask.getId() + ")");
            }else if(command.equalsIgnoreCase("list")){
                List<Task> allTasks = service.getAllTasks();
                if(allTasks.size()==0){
                    System.out.println("There is no tasks in progress");
                }else{
                    for(Task task : allTasks){
                        System.out.println(task.getId()+": "+ task.getDescription() + " - " + task.getStatus());
                    }

                    //aca iterar de nuevo
                    while (true){
                        System.out.println("\n> What do you want to do?");
                        System.out.println("  edit <id> <new task>     - Edit an existing task");
                        System.out.println("  delete <id>              - Delete a task");
                        System.out.println("  mark-done <id>           - Mark a task as done");
                        System.out.println("  mark-in-progress <id>    - Mark a task as in progress");
                        System.out.println("  list                     - Show all tasks");
                        System.out.println("  list <status>            - Show tasks by status (todo, done, in-progress)");
                        System.out.println("  back                     - Return to the main menu");
                        System.out.print("\n> "); // Cursor esperando input

                        String newCommand = scanner.nextLine().trim();
                        String[] comandLine = newCommand.split("\\s+");
                        if(comandLine[0].equalsIgnoreCase("back")){
                            break;
                        }else if(comandLine[0].equalsIgnoreCase("edit")){
                            System.out.println("we edit");
                        } else if (comandLine[0].equalsIgnoreCase("delete")) {
                            System.out.println("we delete");
                        } else if (comandLine[0].equalsIgnoreCase("mark-done")) {
                            System.out.println("we mark done");
                        } else if (comandLine[0].equalsIgnoreCase("mark-in-progress")) {
                            System.out.println("we mark porgress");
                        } else if (comandLine[0].equalsIgnoreCase("list") && comandLine.length==1) {
                            System.out.println("we show again all de tasks");
                        } else if (comandLine[0].equalsIgnoreCase("list")) {
                            System.out.println("we filter by status");
                        }else {
                            System.out.println("Unknown task: '"+ comandLine[0] +"' .");
                        }

                    }
                }

            }else{
                System.out.println("Unknown task: '"+ command +"' .");
            }
        }
        scanner.close();
    }
}
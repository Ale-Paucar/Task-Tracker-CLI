package org.alepaucar.tasktracker;

import org.alepaucar.tasktracker.models.Task;
import org.alepaucar.tasktracker.services.ListHandler;
import org.alepaucar.tasktracker.services.TaskHandler;
import org.alepaucar.tasktracker.services.TaskService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskService service = new TaskService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------");
        System.out.println("------TASK TRACKER------");
        while(true){
            System.out.println("\n> Please enter a comand (add/list/exit):");
            System.out.println("  add <new task>           - Edit an existing task");
            System.out.println("  list                     - Show all tasks");
            System.out.println("  exit                     - Terminate the program");
            System.out.print("\n> "); // Cursor esperando input
            String userInput = scanner.nextLine().trim();
            String[] inputArgs  = userInput.split("\\s+",2);
            if(inputArgs[0].equalsIgnoreCase("exit")){
                System.out.println("Thank for your visit to the task tracker cli project");
                break;
            }else if(inputArgs[0].equalsIgnoreCase("add")){
                if (inputArgs.length>1 && !inputArgs[1].isBlank()){
                    //añadimos la descrpcion y con un trim
                    Task newTask = service.addTask(inputArgs[1].trim());
                    System.out.println("Task created successfully! (ID: " + newTask.getId() + ")");
                }else{
                    System.out.println("A description is missing");
                }

            } else if(inputArgs[0].equalsIgnoreCase("list")){
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
// !!!!!!!!!!!!!FALTAN VALIDACIONES DE EXISTENCIA DE ID
                        String subMenuInput = scanner.nextLine().trim();
                        String[] submenuArgs = subMenuInput.split("\\s+",3);

                        if(submenuArgs[0].equalsIgnoreCase("back")){
                            break;
                        }else if(submenuArgs[0].equalsIgnoreCase("edit")){
                            if (submenuArgs.length < 3) {
                                System.out.println("Please enter both a valid ID and a new task description.");
                            } else {
                                TaskHandler taskHandler = new TaskHandler(submenuArgs[1], service);
                                taskHandler.handleEditTask(submenuArgs[2]);
                            }

                        } else if (submenuArgs[0].equalsIgnoreCase("delete")) {
                            if(submenuArgs.length==2){
                                TaskHandler taskHandler = new TaskHandler(submenuArgs[1], service);
                                taskHandler.handleRemoveTask();
                            }else{
                                String valid = submenuArgs.length== 3 ? "valid": "";
                                System.out.println("Please enter an"+valid+" ID.");
                            }
                        } else if (submenuArgs[0].equalsIgnoreCase("mark-done") || submenuArgs[0].equalsIgnoreCase("mark-in-progress")) {
                            if(submenuArgs.length==2){
                                TaskHandler taskHandler = new TaskHandler(submenuArgs[1], service);
                                taskHandler.handleEditStatus(submenuArgs[0]);
                            }else{
                                String just = submenuArgs.length == 3 ? "just ": "";
                                System.out.println("Please enter "+just+"an ID.");
                            }


                        } else if (submenuArgs[0].equalsIgnoreCase("list")) {
                            ListHandler listHandler = new ListHandler(service);
                            listHandler.handleListCommand(Arrays.copyOfRange(submenuArgs, 1, submenuArgs.length));
                        }else {
                            System.out.println("Unknown task: '"+ submenuArgs[0] +"' .");
                        }

                    }
                }

            }else{
                System.out.println("Unknown task: '"+ inputArgs[0] +"' .");
            }
        }
        scanner.close();
    }
}
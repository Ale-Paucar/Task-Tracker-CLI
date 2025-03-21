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
                for(Task task : allTasks){
                    System.out.println(task.getId()+" : "+ task.getDescription());
                }
            }else{
                System.out.println("Unknown task: '"+ command +"' .");
            }
        }
        scanner.close();
    }
}
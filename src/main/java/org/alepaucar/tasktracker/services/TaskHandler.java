package org.alepaucar.tasktracker.services;

import org.alepaucar.tasktracker.models.Status;
import org.alepaucar.tasktracker.models.Task;
import org.alepaucar.tasktracker.utils.NumberValidator;


public class TaskHandler {
    private boolean valid;
    private long id;
    private Task task = null;
    private final TaskService service;

    public TaskHandler(String id, TaskService service) {
        this.service = service;
        NumberValidator validator = new NumberValidator(id);
        if(validator.isValid()){

            this.task = service.getTaskById(validator.getNumericValue());
            this.valid = (task != null);
        }else {
            this.valid = false;
        }
    }

    public boolean isValid(){
        return valid;
    }

    public void handleEditTask(String newDescription) {
        if(!valid){
            System.out.println("Task not found. Please enter a valid ID");
            return;
        }
        if (newDescription == null || newDescription.isBlank()) {
            System.out.println("Please enter a valid task description.");
            return;
        }
        task.setDescription(newDescription.trim());
        System.out.println("Task description updated successfully: " + task.getDescription());
    }

    public void handleRemoveTask() {
        if(!valid){
            System.out.println("Task not found. Please enter a valid ID");
            return;
        }
        long taskId = task.getId();;
        service.removeTask(taskId);

        if (service.getTaskById(taskId) == null) {
            System.out.println("Task successfully deleted.");
        } else {
            System.out.println("Error: Task could not be deleted.");
        }
    }

    public void handleEditStatus(String inputStatus) {
        if(!valid){
            System.out.println("Task not found. Please enter a valid ID");
            return;
        }
        Status newStatus = switch (inputStatus.toLowerCase()) {
            case "mark-done" -> Status.DONE;
            case "mark-in-progress" -> Status.IN_PROGRESS;
            default -> null;
        };

        if(newStatus!=null){
            task.setStatus(newStatus);
            System.out.println("Task status updated successfully: " + task.getStatus());
        }else {
            System.out.println("Error: Unknown status command. Use 'mark-done' or 'mark-in-progress'.");
        }

    }
}

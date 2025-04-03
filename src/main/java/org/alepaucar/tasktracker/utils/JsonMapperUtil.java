package org.alepaucar.tasktracker.utils;

import org.alepaucar.tasktracker.models.Task;
import org.alepaucar.tasktracker.models.Status;

import java.time.LocalDateTime;
import java.util.*;

public class JsonMapperUtil {

    private JsonMapperUtil() {} // Evitar instancias


    public static String taskToJson(Task task) {
        return "{" +
                "\"id\":" + task.getId() + "," +
                "\"description\":\"" + task.getDescription() + "\"," +
                "\"status\":\"" + task.getStatus() + "\"," +
                "\"createdAt\":\"" + task.getCreatedAt() + "\"," +
                "\"updatedAt\":\"" + task.getUpdatedAt() + "\"" +
                "}";
    }


    public static Task jsonToTask(String json) {
        json = json.replaceAll("[{}]", "");
        String[] pairs = json.split(",");

        Map<String, String> map = new HashMap<>();
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (keyValue.length == 2) {
                map.put(keyValue[0].replace("\"", "").trim(), keyValue[1].replace("\"", "").trim());
            }
        }

        long id = Long.parseLong(map.get("id"));
        String description = map.get("description");
        Status status = Status.valueOf(map.get("status"));
        LocalDateTime createdAt = LocalDateTime.parse(map.get("createdAt"));
        LocalDateTime updatedAt = LocalDateTime.parse(map.get("updatedAt"));

        return new Task(id, description, status);
    }


    public static String taskListToJson(List<Task> tasks) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < tasks.size(); i++) {
            json.append(taskToJson(tasks.get(i)));
            if (i < tasks.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }


    public static List<Task> jsonToTaskList(String json) {
        List<Task> tasks = new ArrayList<>();
        if (json == null || json.isEmpty() || json.equals("[]")) {
            return tasks;
        }

        json = json.substring(1, json.length() - 1); // Quitar corchetes []
        String[] taskJsonArray = json.split("},\\{"); // Separar objetos JSON

        for (String taskJson : taskJsonArray) {
            taskJson = taskJson.startsWith("{") ? taskJson : "{" + taskJson;
            taskJson = taskJson.endsWith("}") ? taskJson : taskJson + "}";
            tasks.add(jsonToTask(taskJson));
        }

        return tasks;
    }
}

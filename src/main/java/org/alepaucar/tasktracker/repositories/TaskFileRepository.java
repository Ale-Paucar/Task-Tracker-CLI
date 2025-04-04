package org.alepaucar.tasktracker.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TaskFileRepository {
    private final Path dataPath;

    public TaskFileRepository() {
        this.dataPath = Paths.get("data/tasks.json");
        createFile();
    }


    public void createFile() {
        try {

            Path directory = dataPath.getParent();
            if (directory != null && !Files.exists(directory)) {
                Files.createDirectories(directory);
                System.out.println("Directory created successfully: " + directory.toString());
            }


            if (!Files.exists(dataPath)) {
                Files.writeString(dataPath, "[]", StandardOpenOption.CREATE_NEW);
                System.out.println("Data file created successfully: " + dataPath.toString());
            } else {
                System.out.println("Data file already exists");
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }



    public String readFile() {
        try {
            return Files.exists(dataPath) ? Files.readString(dataPath) : "[]";
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return "[]";
        }
    }


    public void editFile(String json) {
        try {
            Files.writeString(dataPath, json, StandardOpenOption.TRUNCATE_EXISTING);
            //System.out.println("File successfully overwritten.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }


    public void deleteFile() {
        if (Files.exists(dataPath)) {
            try {
                Files.delete(dataPath);
                System.out.println("File deleted successfully.");


                createFile(); // Llamamos a createFile() para asegurarnos de que se cree uno nuevo
            } catch (IOException e) {
                System.err.println("Error deleting file: " + e.getMessage());
            }
        } else {
            System.out.println("File does not exist.");
            // Crear un nuevo archivo vacío si no existía
            createFile();
        }
    }
}

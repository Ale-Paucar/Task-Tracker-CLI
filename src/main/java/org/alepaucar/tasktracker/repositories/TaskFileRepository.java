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

    // Crear archivo solo si no existe
    public void createFile() {
        try {
            if (!Files.exists(dataPath)) {
                // Si el archivo no existe, lo creamos con el contenido inicial (vacío)
                Files.writeString(dataPath, "[]", StandardOpenOption.CREATE_NEW);
                System.out.println("Date File created successfully: " + dataPath.toString());
            } else {
                System.out.println("Data file already exists");
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }

    // Leer el archivo (si no existe, devolver una lista vacía)
    public String readFile() {
        try {
            return Files.exists(dataPath) ? Files.readString(dataPath) : "[]";
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return "[]";
        }
    }

    // Sobrescribir el archivo con el nuevo contenido
    public void editFile(String json) {
        try {
            Files.writeString(dataPath, json, StandardOpenOption.TRUNCATE_EXISTING);
            //System.out.println("File successfully overwritten.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Eliminar el archivo y crear uno nuevo vacío
    public void deleteFile() {
        if (Files.exists(dataPath)) {
            try {
                Files.delete(dataPath);
                System.out.println("File deleted successfully.");

                // Crear un nuevo archivo vacío después de eliminar el anterior
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

package org.alepaucar.tasktracker.repositories;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TaskFileRepository {
    private Path dataPath;
    public TaskFileRepository() {
        this.dataPath=Paths.get("data/tasks.json");
    }

    public void createFile(){
        try{
            if(Files.exists(dataPath)){
                System.out.println("File already exist");
            }else {
                Path donePath = Files.createFile(dataPath);
                System.out.println("File is created :"+donePath.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void editFile(){
        try {
            if(Files.exists(dataPath)){

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package org.alepaucar.tasktracker.repositories;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TaskFileRepository {

    public TaskFileRepository() {
    }

    public void createFile(){
        try{
            Path dataPath = Paths.get("data/tasks.json");

            System.out.println(System.getProperty("user.dir"));
            /*
            if(Files.exists(dataPath)){
                System.out.println("File already exist");
            }else {
                Path donePath = Files.createFile(dataPath);
                System.out.println("File is created :"+donePath.toString());
            }*/
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

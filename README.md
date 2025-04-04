# ✅ Task Tracker CLI  
## https://roadmap.sh/projects/task-tracker
A command-line application to manage tasks quickly and easily.

## 📌 Features  
- Add tasks with a description.  
- List all pending and completed tasks.  
- Mark tasks as completed.  
- Easily delete tasks. 

## 🛠 Requirements 
- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)  

## 🚀 Installation and Usage  

1. **Clone the repository:**

   ```bash
   git clone https://github.com/Ale-Paucar/Task-Tracker-CLI.git
   cd Task-Tracker-CLI

2. **Compile the source code:**
    ```bash
   javac -encoding UTF-8 -d out "src\main\java\org\alepaucar\tasktracker\*.java" "src\main\java\org\alepaucar\tasktracker\cli\*.java" "src\main\java\org\alepaucar\tasktracker\models\*.java" "src\main\java\org\alepaucar\tasktracker\repositories\*.java" "src\main\java\org\alepaucar\tasktracker\services\*.java" "src\main\java\org\alepaucar\tasktracker\utils\*.java"
3. **Run the application:**
    ```bash
   java -cp out org.alepaucar.tasktracker.Main
   ```
## 📝 Usage
 ```bash
# Add a new task
add "Buy groceries"

# List all tasks
list

# Delete all tasks
delete-all

# Edit an existing task
edit <id> "<new task description>"

# Delete a specific task
delete <id>

# Mark a task as in progress
mark-in-progress <id>

# Mark a task as done
mark-done <id>

# List tasks by status
list todo
list in-progress
list done
```

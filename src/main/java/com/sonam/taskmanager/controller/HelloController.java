package com.sonam.taskmanager.controller;

import com.sonam.taskmanager.DBConnection;
import com.sonam.taskmanager.dao.TaskDAO;
import com.sonam.taskmanager.model.Task;
import org.springframework.web.bind.annotation.*;
import java.sql.Connection;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class HelloController {

    TaskDAO dao = new TaskDAO();

    // ✅ Test API
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Sonam!";
    }

    // ✅ CREATE
    @PostMapping("/task")
    public String addTask(@RequestBody Task task) {
        dao.addTask(task);
        return "Task Added Successfully";
    }

    // ✅ READ ALL
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return dao.getAllTasks();
    }

    // ✅ READ ONE
    @GetMapping("/task/{id}")
    public Task getTaskById(@PathVariable int id) {
        return dao.getTaskById(id);
    }

    // ✅ UPDATE
    @PutMapping("/task/{id}")
    public String updateTask(@PathVariable int id, @RequestBody Task task) {
        dao.updateTask(id, task);
        return "Task Updated Successfully";
    }

    // ✅ DELETE
    @DeleteMapping("/task/{id}")
    public String deleteTask(@PathVariable int id) {
        dao.deleteTask(id);
        return "Task Deleted Successfully";
    }

    @GetMapping("/test-db")
    public String testDB() {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn != null) {
                return "✅ DB Connected! Catalog: " + conn.getCatalog();
            } else {
                return "❌ conn is null";
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

}
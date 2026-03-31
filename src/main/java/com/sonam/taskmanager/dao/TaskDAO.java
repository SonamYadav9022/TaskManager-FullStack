package com.sonam.taskmanager.dao;

import com.sonam.taskmanager.DBConnection;
import com.sonam.taskmanager.model.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    // ✅ CREATE - Add new task
    public void addTask(Task task) {
        try {
            Connection conn = DBConnection.getConnection();
            conn.setAutoCommit(true);
            String query = "INSERT INTO tasks (title, status, priority) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getStatus());
            ps.setString(3, task.getPriority());
            int rows = ps.executeUpdate();
            System.out.println(">>> Rows inserted: " + rows);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ READ ALL - Get all tasks
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT id, title, status, priority FROM tasks";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setStatus(rs.getString("status"));
                task.setPriority(rs.getString("priority"));
                tasks.add(task);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // ✅ READ ONE - Get task by id
    public Task getTaskById(int id) {
        Task task = null;
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT id, title, status, priority FROM tasks WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setStatus(rs.getString("status"));
                task.setPriority(rs.getString("priority"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }

    // ✅ UPDATE - Update task by id
    public void updateTask(int id, Task task) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "UPDATE tasks SET title = ?, status = ?, priority = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getStatus());
            ps.setString(3, task.getPriority());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ DELETE - Delete task by id
    public void deleteTask(int id) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "DELETE FROM tasks WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
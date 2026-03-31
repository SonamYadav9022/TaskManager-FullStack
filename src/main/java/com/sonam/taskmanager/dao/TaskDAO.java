package com.sonam.taskmanager.dao;

import com.sonam.taskmanager.DBConnection;
import com.sonam.taskmanager.model.Task;
import java.sql.*;
import java.util.*;

public class TaskDAO {

    public void addTask(Task task) {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(true);
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO tasks (title, status, priority) VALUES (?, ?, ?)");
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getStatus());
            ps.setString(3, task.getPriority());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT id, title, status, priority FROM tasks");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Task t = new Task();
                t.setId(rs.getInt("id"));
                t.setTitle(rs.getString("title"));
                t.setStatus(rs.getString("status"));
                t.setPriority(rs.getString("priority"));
                tasks.add(t);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return tasks;
    }

    public Task getTaskById(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT id, title, status, priority FROM tasks WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Task t = new Task();
                t.setId(rs.getInt("id"));
                t.setTitle(rs.getString("title"));
                t.setStatus(rs.getString("status"));
                t.setPriority(rs.getString("priority"));
                return t;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public void updateTask(int id, Task task) {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(true);
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE tasks SET title=?, status=?, priority=? WHERE id=?");
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getStatus());
            ps.setString(3, task.getPriority());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void deleteTask(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(true);
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM tasks WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
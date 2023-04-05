package kz.bitlab.taskmanager.dao;

import kz.bitlab.taskmanager.model.Task;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    public static boolean create(Task task) {
        if (task == null || task.getId() != null) {
            return false;
        }

        int rows = 0;
        try {
            PreparedStatement stmt = Application.INSTANCE.connection().prepareStatement(
                    "insert into task (name, description, due_date, complete) values (?, ?, ?, ?)");
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, Date.valueOf(task.getDueDate()));
            stmt.setBoolean(4, task.isComplete());

            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rows > 0;
    }

    public static boolean edit(Task task) {
        if (task == null || task.getId() == null) {
            return false;
        }

        int rows = 0;
        try {
            PreparedStatement stmt = Application.INSTANCE.connection().prepareStatement(
                    "update task set name = ?, description = ?, due_date = ?, complete = ? where id = ?");
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, Date.valueOf(task.getDueDate()));
            stmt.setBoolean(4, task.isComplete());
            stmt.setLong(5, task.getId());

            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rows > 0;
    }

    public static boolean delete(Task task) {
        if (task == null || task.getId() == null) {
            return false;
        }

        int rows = 0;
        try {
            PreparedStatement stmt = Application.INSTANCE.connection().prepareStatement(
                    "delete from task where id = ?");
            stmt.setLong(1, task.getId());

            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rows > 0;
    }

    public static Task findById(Long id) {
        if (id == null) {
            return null;
        }

        Task task = null;
        try {
            PreparedStatement stmt = Application.INSTANCE.connection().prepareStatement(
                    "select * from task where id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                task = new Task(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDate("due_date").toLocalDate(),
                        rs.getBoolean("complete"));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return task;
    }

    public static List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement stmt = Application.INSTANCE.connection().prepareStatement(
                    "select * from task order by due_date desc");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Task task = new Task(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDate("due_date").toLocalDate(),
                        rs.getBoolean("complete"));
                tasks.add(task);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }
}

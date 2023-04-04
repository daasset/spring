package kz.bitlab.sprintone.dao;

import kz.bitlab.sprintone.model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public static boolean create(Student student) {
        int row = 0;

        try {
            PreparedStatement stmt = Application.INSTANCE.connection().prepareStatement(
                    "insert into student (name, surname, score) values (?, ?, ?)");
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getSurname());
            stmt.setInt(3, student.getScore());

            row = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return row > 0;
    }

    public static List<Student> findAll() {
        List<Student> students = new ArrayList<>();

        try {
            PreparedStatement stmt = Application.INSTANCE.connection().prepareStatement(
                    "select * from student order by id desc");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("score")
                );
                students.add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }
}

package com.stefanpascu.pao.service;

import com.DatabaseConnection;
import com.stefanpascu.pao.model.Student;
import com.stefanpascu.pao.model.enums.Gender;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.stefanpascu.pao.service.contants.Constants.*;

public class StudentService {
    Connection conn = DatabaseConnection.getDataBaseConnection();

    private static StudentService instance = null;

    private StudentService() { }

    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }

        return instance;
    }

    public List<Student> getStudents() throws SQLException, ParseException {
        List<Student> records = new ArrayList<>();

        String sql = "SELECT * FROM student;";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        while(result.next()) {
            Student student = new Student(result.getString(1),result.getString(2), result.getString(3), Gender.valueOf(result.getString(4)), DATE_FORMAT.parse(result.getString(5)), result.getString(6));
            records.add(student);
        }

        return records;
    }

    public Student getStudents(String studentId) throws SQLException, ParseException {
        String sql = "SELECT * FROM student;";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        while(result.next()) {
            if(studentId.equals(result.getString(1)))
                return new Student(result.getString(1), result.getString(2), result.getString(3), Gender.valueOf(result.getString(4)), DATE_FORMAT.parse(result.getString(5)), result.getString(6));
        }
        System.out.println("The Student with the given ID does not exist.");
        return null;
    }

    public void saveStudent(Student student) {
        String sql = "INSERT INTO student (first_name, last_name, gender, birthdate, classroom_id)\n" +
                "VALUES (?, ?, ?, ?, ?);";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            assert stmt != null;
            stmt.setString(1, student.getFirstName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            stmt.setString(2, student.getLastName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            stmt.setString(3, String.valueOf(student.getGender()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            stmt.setString(4, dateToString(student.getBirthDate()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            stmt.setString(5, student.getClassroomId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int rows = 0;
        try {
            rows = stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (rows > 0) {
            System.out.println("Student successfully added.");
        }
    }

    public void updateClassroomId(String studentId, String classroomId) throws SQLException {
        String sql = "UPDATE student SET classroom_id=? WHERE id=?;";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, classroomId);
        stmt.setString(2, studentId);

        int rows = 0;
        try {
            rows = stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (rows > 0) {
            System.out.println("Student's classroom ID successfully updated.");
        }
    }

    public void deleteStudent(String studentId) throws SQLException {
        String sql1 = "DELETE FROM grade WHERE student_id=?;";
        PreparedStatement stmt1 = conn.prepareStatement(sql1);
        stmt1.setString(1, studentId);

        try {
            stmt1.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sql2 = "DELETE FROM absence WHERE student_id=?;";
        PreparedStatement stmt2 = conn.prepareStatement(sql2);
        stmt2.setString(1, studentId);

        try {
            stmt2.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sql = "DELETE FROM student WHERE id=?;";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, studentId);

        int rows = 0;
        try {
            rows = stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (rows > 0) {
            System.out.println("Student successfully removed.");
        }
    }


    public boolean studentExists(String studentId) throws SQLException, ParseException {
        Student student = getStudents(studentId);

        if(student == null) {
            return false;
        }
        return true;
    }

    public String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        return strDate;
    }

//    From Etapa_II
//
//    private Student getRecordFromLine(String line) {
//        List<String> values = new ArrayList<>();
//        try (Scanner rowScanner = new Scanner(line)) {
//            rowScanner.useDelimiter(COMMA_DELIMITER);
//            while (rowScanner.hasNext()) {
//                values.add(rowScanner.next());
//            }
//        }
//
//        Date date;
//        try {
//            date = DATE_FORMAT.parse(values.get(4));
//            return new Student(values.get(0), values.get(1), values.get(2), Gender.get(values.get(3)), date, values.get(5));
//        } catch (ParseException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    private String recordToLine(Student student) {
//        return String.format("%s,%s,%s,%s,%s,%s",
//                student.getId(), student.getFirstName(), student.getLastName(), student.getGender(), DATE_FORMAT.format(student.getBirthDate()), student.getClassroomId());
//    }
}
